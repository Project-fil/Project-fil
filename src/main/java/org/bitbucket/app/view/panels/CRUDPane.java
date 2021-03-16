package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.LocalCommands;
import org.bitbucket.app.commands.RemoteCommands;

import javax.swing.*;

public class CRUDPane extends JTabbedPane {

    public CRUDPane(LocalCommands localCommands, RemoteCommands remoteCommands) {

        this.setBounds(5,5,200,600);

        JPanel localPanel = new LocalPanel(localCommands);
        localPanel.setVisible(true);
        JPanel remotePanel = new RemotePanel(remoteCommands);
        remotePanel.setVisible(true);

        add("Local", localPanel);
        add("Remote DB", remotePanel);
        setVisible(true);
    }
}
