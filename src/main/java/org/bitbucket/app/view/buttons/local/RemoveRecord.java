package org.bitbucket.app.view.buttons.local;

import org.bitbucket.app.config.ViewConfig;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;

public class RemoveRecord extends JButton {

    public RemoveRecord(PeopleTablePanel peopleTablePanel) {
        setText("Remove record");
        setSize(140,30);
        addActionListener(ViewConfig.localCommands(peopleTablePanel).delete);
    }
}
