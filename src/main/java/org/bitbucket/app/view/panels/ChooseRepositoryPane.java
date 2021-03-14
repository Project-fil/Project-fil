package org.bitbucket.app.view.panels;

import javax.swing.*;

public class ChooseRepositoryPane extends JTabbedPane {

    String selectedRepository = "";

    public ChooseRepositoryPane() {
        this.setBounds(5,5,200,600);

        JPanel local = new LocalPanel();
        local.setVisible(true);
        JPanel remote = new RemotePanel();
        remote.setVisible(true);

        add("Local", local);
        add("Remote DB", remote);
        setVisible(true);
    }
}
