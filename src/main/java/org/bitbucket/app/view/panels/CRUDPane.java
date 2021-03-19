package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.LocalCommands;
import org.bitbucket.app.commands.RemoteCommands;

import javax.swing.*;

public class CRUDPane extends JTabbedPane {

    public final JPanel localPanel;

    public final JPanel remotePanel;

    public CRUDPane(LocalCommands localCommands, RemoteCommands remoteCommands) {

        this.localPanel = new LocalPanel(localCommands);
        this.remotePanel = new RemotePanel(remoteCommands);

        this.setBounds(5,5,200,600);

        this.localPanel.setVisible(true);
        this.remotePanel.setVisible(true);

        add("Local", localPanel);
        add("Remote DB", remotePanel);
        setVisible(true);
    }
}
