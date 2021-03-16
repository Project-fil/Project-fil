package org.bitbucket.app.view.buttons.local;

import org.bitbucket.app.commands.LocalCommands;

import javax.swing.*;

public class RemoveRecord extends JButton {

    public RemoveRecord(LocalCommands commands) {
        setText("Remove record");
        setSize(140,30);
        addActionListener(commands.delete());
    }
}
