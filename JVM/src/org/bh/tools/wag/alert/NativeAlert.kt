package org.bh.tools.wag.alert

import org.bh.tools.base.disambiguation.OS
import org.bh.tools.io.logging.LogLevel
import org.bh.tools.io.logging.log
import org.bh.tools.ui.alert.*
import org.bh.tools.ui.swing.CommonLookAndFeel
import org.bh.tools.ui.swing.LookAndFeelController
import java.io.Console

/**
 * @author Ben Leggiero
 * @since 2017-02-23
 */
object NativeAlert {
    init {
        if (OS.current.isDesktop) {
            LookAndFeelController.currentApplicationLookAndFeel = CommonLookAndFeel.native
        }
    }



    /**
     * Shows an alert to the user in a native manner, without giving them any options but to continue. This is useful
     * for informing the user of a state that has already occurred. If this is a desktop OS (has a point-and-click UI),
     * a [HuskyOptionPane] is shown. Else, a message is logged to the console.
     *
     * TODO: Android support
     *
     * @param title              The text at the top of the confirmation
     * @param detail             The text that will appear beneath the title
     * @param onUserConfirmation The block that will be called when the user confirms they have read the message
     */
    fun showOptionlessConfirmation(title: String, detail: String, onUserConfirmation: () -> Unit) {
        return when {
            OS.current.isDesktop -> HuskyOptionPane.showOptionDialog(
                    title = title,
                    message = detail,
                    messageType = JOptionPaneMessageType.info,
                    optionType = JOptionPaneOptionType.ok,
                    allOptions = listOf(AlertOption.ok),
                    defaultOption = AlertOption.ok,
                    onUserSelection = { onUserConfirmation() })

            else -> {
                log.info("$title\r\n\t$detail")
                onUserConfirmation()
            }
        }
    }
}

