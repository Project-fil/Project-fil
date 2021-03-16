package org.bitbucket.app.view.buttons.local;

import org.bitbucket.app.commands.LocalCommands;

import javax.swing.*;

public class CreateNewRecord extends JButton {

    public CreateNewRecord(LocalCommands commands) {
        setText("Create new record");
        setSize(140,30);
        addActionListener(commands.create());
    }
}
