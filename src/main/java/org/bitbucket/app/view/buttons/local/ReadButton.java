package org.bitbucket.app.view.buttons.local;

import org.bitbucket.app.commands.LocalCommands;

import javax.swing.*;

public class ReadButton extends JButton {

    public ReadButton(LocalCommands commands) {
        setText("Read");
        setSize(140,30);
        addActionListener(commands.read());
    }
}
