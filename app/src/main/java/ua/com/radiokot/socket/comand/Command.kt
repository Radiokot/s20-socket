package ua.com.radiokot.socket.comand

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
interface Command {
    val data: ByteArray

    companion object {
        val KEY = byteArrayOf(0x68, 0x64)
        val MAC_PADDING = byteArrayOf(0x20, 0x20, 0x20, 0x20, 0x20, 0x20)
    }
}