package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.FileCommands;
import org.bitbucket.app.commands.DatabaseCommands;

import javax.swing.*;

public class CRUDPane extends JTabbedPane {

    public final JPanel localPanel;

    public final JPanel remotePanel;

    public CRUDPane(FileCommands fileCommands, DatabaseCommands databaseCommands) {

        this.localPanel = new LocalPanel(fileCommands);
        this.remotePanel = new RemotePanel(databaseCommands);

        this.setBounds(5,8,200,582);

        this.localPanel.setVisible(true);
        this.remotePanel.setVisible(true);

        add("Local", localPanel);
        add("Remote DB", remotePanel);
        setVisible(true);
    }
}
