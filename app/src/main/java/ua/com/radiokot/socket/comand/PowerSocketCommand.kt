package ua.com.radiokot.socket.comand

import ua.com.radiokot.socket.Socket

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
abstract class PowerSocketCommand(socket: Socket) : SocketCommand(socket) {
    override val prefix = byteArrayOf(0x00, 0x17, 0x64, 0x63)

    override val payload: ByteArray
        get() = byteArrayOf(0x00, 0x00, 0x00, 0x00, if (isOn) 0x01 else 0x00)

    abstract val isOn: Boolean
}