package org.bh.tools.wag.alert

import org.bh.tools.base.disambiguation.OS
import org.bh.tools.ui.alert.*

/**
 * @author Ben Leggiero
 * @since 2017-02-23
 */
object NativeAlert {
    fun showOptionlessConfirmation(title: String, detail: String, onUserConfirmation: () -> Unit) {
        return when {
            OS.current.isDesktop -> showJOptionPane(
                    title = title,
                    message = "$title\r\n$detail",
                    messageType = JOptionPaneMessageType.info,
                    optionType = JOptionPaneOptionType.ok,
                    allOptions = listOf(AlertOption.ok),
                    defaultOption = AlertOption.ok,
                    onUserSelection = { onUserConfirmation() })


            else -> {

            }
        }
    }
}

