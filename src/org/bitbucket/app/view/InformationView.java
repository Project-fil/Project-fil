package org.bitbucket.app.view;

import org.bitbucket.app.models.PeopleTableModel;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class InformationView extends JPanel {

    private JTable peopleTable;

    public InformationView() {
        setLayout(null);
        setBounds(215,5,600,600);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.peopleTable = new JTable();
        peopleTable.setBounds(5,15,540,300);
        this.peopleTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane scrollPane = new JScrollPane(this.peopleTable);
        scrollPane.setBounds(5,5,590,590);
        add(scrollPane);
        setVisible(true);
    }
}
