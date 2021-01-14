package ua.com.radiokot.socket

import android.support.v4.os.CancellationSignal
import ua.com.radiokot.socket.comand.Command
import java.net.DatagramPacket
import java.net.DatagramSocket

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
class CommandSender {
    fun send(socket: Socket, commands: Collection<Command>,
             cancellationSignal: CancellationSignal,
             repeatCount: Int = 4, delayMs: Long = 250) {
        DatagramSocket().use { datagramSocket ->
            datagramSocket.broadcast = true

            commands.forEach { command ->
                (0..repeatCount).forEach {
                    if (!cancellationSignal.isCanceled) {
                        DatagramPacket(command.data, command.data.size, socket.ip, socket.port)
                                .also {
                                    datagramSocket.send(it)
                                }
                        Thread.sleep(delayMs)
                    }
                }
            }
        }
    }
}