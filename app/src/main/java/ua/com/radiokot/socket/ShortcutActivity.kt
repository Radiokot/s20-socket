package ua.com.radiokot.socket

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_shortcut.*

class ShortcutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shortcut)

        initActions()
        initButtons()
    }

    private fun initActions() {
        val actions = listOf(Actions.TURN_ON, Actions.TURN_OFF)

        actions.forEachIndexed { i, it ->
            val radioButton = RadioButton(this)
                    .apply {
                        tag = it
                        text = it.name
                        id = it.name.hashCode()
                        isChecked = i == 0
                    }

            actions_radio_group.addView(radioButton)
        }
    }

    private fun initButtons() {
        cancel_button.setOnClickListener {
            finish()
        }

        create_shortcut_button.setOnClickListener {
            val action = findViewById<RadioButton>(actions_radio_group.checkedRadioButtonId)
                    .tag as? Actions.Action

            if (action != null) {
                createShortcut(action)
            }
        }
    }

    private fun createShortcut(action: Actions.Action) {
        val iconResource = Intent.ShortcutIconResource.fromContext(this,
                R.drawable.ic_launcher)

        val intent = Intent()
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, action.intent)
                .putExtra(Intent.EXTRA_SHORTCUT_NAME, action.name)
                .putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
