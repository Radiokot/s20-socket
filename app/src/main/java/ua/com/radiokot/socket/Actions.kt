package ua.com.radiokot.socket

import android.content.Intent

/**
 * Created by Oleg Koretsky on 15.09.2018.
 */
object Actions {
    class Action(
            val name: String,
            private val full: String
    ) {
        val intent = Intent().apply { action = full }
    }

    val TURN_ON = Action("Turn On", BuildConfig.APPLICATION_ID + ".TURN_ON")
    val TURN_OFF = Action("Turn Off", BuildConfig.APPLICATION_ID + ".TURN_OFF")
}