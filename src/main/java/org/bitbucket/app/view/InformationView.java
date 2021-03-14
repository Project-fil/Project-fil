package org.bitbucket.app.view;

import javax.swing.*;
import java.awt.*;

public class InformationView extends JPanel {

    private JTable peopleTable;

    public InformationView() {
        setLayout(null);
        setBounds(215,5,600,600);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.peopleTable = new JTable();
        peopleTable.setBounds(5,15,540,300);

        JScrollPane scrollPane = new JScrollPane(this.peopleTable);
        scrollPane.setBounds(5,5,590,590);
        add(scrollPane);
        setVisible(true);
    }
}
