package org.bitbucket.app.view;

import org.bitbucket.app.view.panels.CRUDPane;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;

public class ApplicationFrame extends JFrame {

    private JPanel peopleTable;

    private JTabbedPane crudPane;

    public JPanel peopleTable() {
        return peopleTable;
    }

    public JTabbedPane crudPane() {
        return crudPane;
    }

    public ApplicationFrame(PeopleTablePanel peopleTablePanel,
                            CRUDPane crudPane){
        this.peopleTable = peopleTablePanel;
        this.crudPane = crudPane;
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(840, 650);
        this.add(peopleTablePanel);
        this.add(crudPane);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
