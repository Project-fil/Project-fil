package org.bitbucket.app.view.panels;

import org.bitbucket.app.models.PeopleTableModel;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class PeopleTablePanel extends JPanel {

    private JTable peopleTablePanel;

    public PeopleTablePanel(PeopleTableModel peopleTableModel){
        this.setLayout(null);
        this.setBounds(210, 15, 650, 300);
        this.peopleTablePanel = new JTable(peopleTableModel);
        this.peopleTablePanel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.peopleTablePanel.getColumnModel().getColumn(0).setPreferredWidth(170);
        this.peopleTablePanel.getColumnModel().getColumn(1).setPreferredWidth(60);
        this.peopleTablePanel.getColumnModel().getColumn(2).setPreferredWidth(60);
        this.peopleTablePanel.getColumnModel().getColumn(3).setPreferredWidth(20);
        this.peopleTablePanel.getColumnModel().getColumn(4).setPreferredWidth(60);
        JScrollPane scr = new JScrollPane(this.peopleTablePanel);
        scr.setBounds(5, 5, 600, 800);
        this.add(scr);
        this.setVisible(Boolean.TRUE);
    }
}
