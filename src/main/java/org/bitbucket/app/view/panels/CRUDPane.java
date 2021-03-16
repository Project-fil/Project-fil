package org.bitbucket.app.view.panels;

import javax.swing.*;

public class CRUDPane extends JTabbedPane {

    private JPanel localPanel;

    private JPanel remotePanel;

    public JPanel localPanel() {
        return localPanel;
    }

    public JPanel remotePanel() {
        return remotePanel;
    }

    public CRUDPane(PeopleTablePanel peopleTablePanel) {
        this.setBounds(5,5,200,600);

        localPanel = new LocalPanel(peopleTablePanel);
        localPanel.setVisible(true);
        remotePanel = new RemotePanel(peopleTablePanel);
        remotePanel.setVisible(true);

        add("Local", localPanel);
        add("Remote DB", remotePanel);
        setVisible(true);
    }
}
