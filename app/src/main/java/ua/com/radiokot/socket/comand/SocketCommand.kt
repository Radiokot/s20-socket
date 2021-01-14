package ua.com.radiokot.socket.comand

import ua.com.radiokot.socket.Socket

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
abstract class SocketCommand(
        protected val socket: Socket
) : Command {
    override val data: ByteArray
        get() = Command.KEY +
                prefix +
                socket.mac +
                Command.MAC_PADDING +
                payload

    abstract val prefix: ByteArray

    abstract val payload: ByteArray
}