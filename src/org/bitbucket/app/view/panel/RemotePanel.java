package org.bitbucket.app.view.panel;

import org.bitbucket.app.view.buttons.local.CreateNewRecord;
import org.bitbucket.app.view.buttons.local.ReadButton;
import org.bitbucket.app.view.buttons.local.RemoveRecord;
import org.bitbucket.app.view.buttons.local.UpdateButton;

import javax.swing.*;

public class RemotePanel extends JPanel {

    public final JButton readData = new ReadButton();
    public final JButton updateRecord = new UpdateButton();
    public final JButton createNewRecord = new CreateNewRecord();
    public final JButton removeRecord = new RemoveRecord();

    public RemotePanel() {

        setLayout(null);
        setVisible(true);

        readData.setLocation(5, 5);
        updateRecord.setLocation(5, 45);
        createNewRecord.setLocation(5, 85);
        removeRecord.setLocation(5, 125);

        add(readData);
        add(updateRecord);
        add(createNewRecord);
        add(removeRecord);
    }
}
