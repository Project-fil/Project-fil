package com.github.app;

import javax.swing.*;
import java.awt.*;

public class InformationView extends JPanel {

    public InformationView() {
        setLayout(null);
        setBounds(215,5,600,600);
        setVisible(true);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5,5,590,590);
        add(scrollPane);
    }
}
