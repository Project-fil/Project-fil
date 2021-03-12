package org.bitbucket.app.view;

import org.bitbucket.app.view.panel.ButtonsPanel;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(CreateNewRecordWindow createNewRecordWindow,
                      UpdateWindow updateWindow,
                      RemoveRecord removeRecord,
                      ButtonsPanel buttonsPanel,
                      InformationView informationView) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(0,0,840, 650);
        add(createNewRecordWindow);
        add(updateWindow);
        add(removeRecord);
        add(buttonsPanel);
        add(informationView);
        setVisible(true);
    }
}
