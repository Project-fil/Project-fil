package org.bitbucket.app.view;

import javax.swing.*;
import java.awt.*;

public class InformationView extends JPanel {

    public InformationView() {
        setLayout(null);
        setBounds(215,5,600,600);
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        String[] columnNames = {"id", "firstname", "lastname", "age", "city"};
        String[][] data = {{"1", "kuk", "skuk", "23", "Megakuk"},
                {"2", "look", "chesnok", "23", "Balalayka"}};
        JTable informationTable = new JTable(data, columnNames);
        informationTable.setBounds(5,15,540,300);

        JScrollPane scrollPane = new JScrollPane(informationTable);
        scrollPane.setBounds(5,5,590,590);
        add(scrollPane);
    }
}
