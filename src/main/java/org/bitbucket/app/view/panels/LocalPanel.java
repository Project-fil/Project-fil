package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.LocalFileCommands;

import javax.swing.*;

public class LocalPanel extends JPanel {

    public final JButton readButton;
    public final JButton createButton;
    public final JButton updateButton;
    public final JButton deleteButton;

    public LocalPanel(LocalFileCommands commands) {

        readButton = new JButton("Read");
        readButton.setSize(185, 30);
        readButton.setLocation(5, 5);
        readButton.addActionListener(commands.read());

        createButton = new JButton("Create new record");
        createButton.setSize(185, 30);
        createButton.setLocation(5, 45);
        createButton.addActionListener(commands.create());

        updateButton = new JButton("Update record");
        updateButton.setSize(185, 30);
        updateButton.setLocation(5, 85);
        updateButton.addActionListener(commands.update());

        deleteButton = new JButton("Remove record");
        deleteButton.setSize(185, 30);
        deleteButton.setLocation(5, 125);
        deleteButton.addActionListener(commands.delete());

        this.setLayout(null);
        this.setVisible(true);

        this.add(readButton);
        this.add(createButton);
        this.add(updateButton);
        this.add(deleteButton);

    }
}
