package org.bitbucket.app.view.panels;

import org.bitbucket.app.models.PeopleTableModel;

import javax.swing.*;

public class PeopleTablePanel extends JPanel {

    private final JTable peopleTable;

    private PeopleTableModel peopleTableModel;

    public JTable peopleTable() {
        return peopleTable;
    }

    public PeopleTableModel peopleTableModel() {
        return peopleTableModel;
    }

    public PeopleTablePanel(PeopleTableModel peopleTableModel){

        this.setLayout(null);
        this.setBounds(210, 15, 480, 500);

        this.peopleTableModel = peopleTableModel;
        this.peopleTable = new JTable(this.peopleTableModel);
        this.peopleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.peopleTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        this.peopleTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.peopleTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.peopleTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        this.peopleTable.getColumnModel().getColumn(4).setPreferredWidth(90);

        JScrollPane scrollPane = new JScrollPane(this.peopleTable);
        scrollPane.setBounds(5, 5, 480, 500);

        this.add(scrollPane);
        this.setVisible(true);
    }

}
