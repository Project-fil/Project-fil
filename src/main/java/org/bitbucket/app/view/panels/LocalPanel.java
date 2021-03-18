package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.LocalCommands;

import javax.swing.*;

public class LocalPanel extends JPanel {

    private final JButton readButton;
    private final JButton createButton;
    private final JButton updateButton;
    private final JButton deleteButton;

    public JButton getReadButton() {
        return readButton;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

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
