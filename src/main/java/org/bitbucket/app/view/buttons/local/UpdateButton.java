package org.bitbucket.app.view.buttons.local;

import org.bitbucket.app.commands.LocalCommands;

import javax.swing.*;

public class UpdateButton extends JButton {

    public UpdateButton(LocalCommands commands) {
        setText("Update record");
        setSize(140,30);
        addActionListener(commands.update());
    }
}
