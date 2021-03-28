package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.DatabaseCommands;

import javax.swing.*;

public class RemotePanel extends JPanel {

    private final JLabel databaseLabel;
    public final JComboBox<String> databaseSelectBox;
    public final JButton createButtonDb;
    public final JButton updateButtonDb;
    public final JButton deleteButtonDb;

    public RemotePanel(DatabaseCommands commands) {
        String[] databaseArray = {"MySQL", "PostgreSQL", "H2", "MongoDB", "Redis", "Cassandra", "GraphDB"};

        databaseLabel = new JLabel("Select Database");
        databaseLabel.setSize(130, 20);
        databaseLabel.setLocation(5, 10);

        databaseSelectBox = new JComboBox<>();
        for (String s : databaseArray) {
            databaseSelectBox.addItem(s);
        }
        databaseSelectBox.setSize(185, 30);
        databaseSelectBox.setLocation(5, 40);
        databaseSelectBox.addActionListener(commands.read());

        createButtonDb = new JButton("Create new record");
        createButtonDb.setSize(185, 30);
        createButtonDb.setLocation(5, 80);
        createButtonDb.addActionListener(commands.create());

        updateButtonDb = new JButton("Update record");
        updateButtonDb.setSize(185, 30);
        updateButtonDb.setLocation(5, 120);
        updateButtonDb.addActionListener(commands.update());

        deleteButtonDb = new JButton("Remove record");
        deleteButtonDb.setSize(185, 30);
        deleteButtonDb.setLocation(5, 160);
        deleteButtonDb.addActionListener(commands.delete());

        setLayout(null);
        setVisible(true);

        this.add(databaseLabel);
        this.add(databaseSelectBox);
        this.add(createButtonDb);
        this.add(updateButtonDb);
        this.add(deleteButtonDb);
    }
}
