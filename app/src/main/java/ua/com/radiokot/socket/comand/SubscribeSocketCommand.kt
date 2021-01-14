package ua.com.radiokot.socket.comand

import ua.com.radiokot.socket.Socket

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
class SubscribeSocketCommand(socket: Socket) : SocketCommand(socket) {
    override val prefix = byteArrayOf(0x00, 0x1e, 0x63, 0x6c)

    override val payload = socket.mac.reversed().toByteArray() + Command.MAC_PADDING
}