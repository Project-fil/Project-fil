package org.bitbucket.app.view;

import org.bitbucket.app.view.panels.ChooseRepositoryPane;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;

public class ApplicationFrame extends JFrame {

    public ApplicationFrame(PeopleTablePanel peopleTablePanel,
                            ChooseRepositoryPane crudPane){
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(840, 650);
        this.add(peopleTablePanel);
        this.add(crudPane);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
