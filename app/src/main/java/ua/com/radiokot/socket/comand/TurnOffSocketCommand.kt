package ua.com.radiokot.socket.comand

import ua.com.radiokot.socket.Socket

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
class TurnOffSocketCommand(socket: Socket) : PowerSocketCommand(socket) {
    override val isOn = false
}