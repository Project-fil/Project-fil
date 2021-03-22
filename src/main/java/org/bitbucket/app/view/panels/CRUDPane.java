package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.LocalFileCommands;
import org.bitbucket.app.commands.DatabaseCommands;

import javax.swing.*;

public class CRUDPane extends JTabbedPane {

    public final JPanel localPanel;

    public final JPanel remotePanel;

    public CRUDPane(LocalFileCommands localFileCommands, DatabaseCommands databaseCommands) {

        this.localPanel = new LocalPanel(localFileCommands);
        this.remotePanel = new RemotePanel(databaseCommands);

        this.setBounds(5,5,200,600);

        this.localPanel.setVisible(true);
        this.remotePanel.setVisible(true);

        add("Local", localPanel);
        add("Remote DB", remotePanel);
        setVisible(true);
    }
}
