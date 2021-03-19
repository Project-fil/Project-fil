package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.LocalCommands;

import javax.swing.*;

public class LocalPanel extends JPanel {

    public final JButton readButton;
    public final JButton createButton;
    public final JButton updateButton;
    public final JButton deleteButton;

    public LocalPanel(LocalCommands commands) {

        readButton = new JButton("Read");
        readButton.setSize(140,30);
        readButton.addActionListener(commands.read());

        createButton = new JButton("Create new record");
        createButton.setSize(140,30);
        createButton.addActionListener(commands.create());

        updateButton = new JButton("Update record");
        updateButton.setSize(140,30);
        updateButton.addActionListener(commands.update());

        deleteButton = new JButton("Remove record");
        deleteButton.setSize(140,30);
        deleteButton.addActionListener(commands.delete());

        this.setLayout(null);
        this.setVisible(true);

        readButton.setLocation(5, 5);
        createButton.setLocation(5, 45);
        updateButton.setLocation(5, 85);
        deleteButton.setLocation(5, 125);

        this.add(readButton);
        this.add(createButton);
        this.add(updateButton);
        this.add(deleteButton);

    }
}
