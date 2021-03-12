package org.bitbucket.app.view.panel;

import javax.swing.*;

public class ButtonsPanel extends JTabbedPane {

    String selectedRepository = "";

    public ButtonsPanel() {
        setBounds(5,5,200,600);

        JPanel local = new LocalPanel();
        local.setVisible(true);
        JPanel remote = new RemotePanel();
        remote.setVisible(true);

        add("Local", local);
        add("Remote DB", remote);
        setVisible(true);
    }
}
