package com.github.app;

import javax.swing.*;
import java.awt.*;

public class ControlsView extends JPanel {

    public ControlsView() {
        setLayout(null);
        setBounds(5,5,200, 600);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setVisible(true);

        JButton readData = new JButton("Read data");
        JButton updateRecord = new JButton("Update record");
        JButton createNewRecord = new JButton("Create new record");
        JButton removeRecord = new JButton("Remove record");
        JButton exitBtn = new JButton("Exit");

        readData.setBounds(10, 10, 140, 30);
        updateRecord.setBounds(10, 50, 140, 30);
        createNewRecord.setBounds(10, 90, 140, 30);
        removeRecord.setBounds(10, 130, 140, 30);
        exitBtn.setBounds(10, 190, 70, 30);

        add(readData);
        add(updateRecord);
        add(createNewRecord);
        add(removeRecord);
        add(exitBtn);
    }
}
