package com.github.app.view;

import com.github.app.ControlsView;
import com.github.app.InformationView;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(CreateNewRecordWindow createNewRecordWindow,
                      UpdateWindow updateWindow,
                      RemoveRecord removeRecord,
                      ReadWindow readWindow,
                      ControlsView controlsView,
                      InformationView informationView) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(0,0,840, 650);
        add(createNewRecordWindow);
        add(updateWindow);
        add(readWindow);
        add(removeRecord);
        add(controlsView);
        add(informationView);
        setVisible(true);
    }
}
