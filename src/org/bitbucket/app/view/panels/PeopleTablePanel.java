package org.bitbucket.app.view.panels;

import org.bitbucket.app.models.PeopleTableModel;

import javax.swing.*;

public class PeopleTablePanel extends JPanel {

    private JTable peopleTablePanel;

    public PeopleTablePanel(PeopleTableModel peopleTableModel){
        this.setLayout(null);
        this.setBounds(210, 15, 540, 300);
        this.peopleTablePanel = new JTable(peopleTableModel);
        JScrollPane scr = new JScrollPane(this.peopleTablePanel);
        scr.setBounds(5, 5, 590, 800);
        this.add(scr);
        this.setVisible(Boolean.TRUE);
    }

}
