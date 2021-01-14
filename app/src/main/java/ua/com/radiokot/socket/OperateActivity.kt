package ua.com.radiokot.socket

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v4.os.CancellationSignal
import android.widget.Toast
import ua.com.radiokot.socket.comand.Command
import ua.com.radiokot.socket.comand.SubscribeSocketCommand
import ua.com.radiokot.socket.comand.TurnOffSocketCommand
import ua.com.radiokot.socket.comand.TurnOnSocketCommand
import java.io.IOException
import java.net.InetAddress

class OperateActivity : Activity() {
    class UnknownActionException(action: String) : Exception("Action $action is unknown")

    private val socket = Socket(
            name = "Art",
            ip = InetAddress.getByName("10.0.0.100"),
            mac = byteArrayOf(
                    0xAC.toByte(), 0xCF.toByte(),
                    0x23.toByte(), 0x35.toByte(),
                    0xD9.toByte(), 0x14.toByte()
            )
    )

    private var cancellationSignal: CancellationSignal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operate)

        val action = intent.action
        when (action) {
            Actions.TURN_ON.intent.action -> performTurnOn()
            Actions.TURN_OFF.intent.action -> performTurnOff()
            else -> onError(UnknownActionException(action))
        }
    }

    private fun performTurnOn() {
        sendCommandSequence(
                listOf(
                        SubscribeSocketCommand(socket),
                        TurnOnSocketCommand(socket)
                )
        )
    }

    private fun performTurnOff() {
        sendCommandSequence(
                listOf(
                        SubscribeSocketCommand(socket),
                        TurnOffSocketCommand(socket)
                )
        )
    }

    private fun sendCommandSequence(sequence: Collection<Command>) {
        Thread(Runnable {
            try {
                cancellationSignal?.cancel()
                cancellationSignal = CancellationSignal()
                CommandSender().send(socket, sequence, cancellationSignal!!)
                postResult { onSuccess() }
            } catch (e: Exception) {
                postResult { onError(e) }
            }
        }).start()
    }

    private fun postResult(runnable: () -> Unit) {
        if (!isFinishing) {
            Handler(mainLooper).post(runnable)
        }
    }

    private fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is UnknownActionException ->
                Toast.makeText(this,
                        R.string.error_unknown_action, Toast.LENGTH_SHORT).show()
            is IOException ->
                Toast.makeText(this,
                        R.string.error_network, Toast.LENGTH_SHORT).show()
            else ->
                Toast.makeText(this,
                        R.string.error_unknown_action, Toast.LENGTH_SHORT).show()
        }

        finish()
    }

    private fun onSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onDestroy() {
        cancellationSignal?.cancel()
        super.onDestroy()
    }
}
