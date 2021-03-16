package org.bitbucket.app.view.buttons.local;

import org.bitbucket.app.commands.LocalCommands;
import org.bitbucket.app.config.ViewConfig;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;

public class ReadButton extends JButton {

    public ReadButton(PeopleTablePanel peopleTablePanel) {
        setText("Read");
        setSize(140,30);
        addActionListener(ViewConfig.localCommands(peopleTablePanel).read);
    }
}
