package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.LocalCommands;
import org.bitbucket.app.view.buttons.local.CreateNewRecord;
import org.bitbucket.app.view.buttons.local.ReadButton;
import org.bitbucket.app.view.buttons.local.RemoveRecord;
import org.bitbucket.app.view.buttons.local.UpdateButton;

import javax.swing.*;

public class LocalPanel extends JPanel {

    public final JButton readData;
    public final JButton updateRecord ;
    public final JButton createNewRecord;
    public final JButton removeRecord;

    public LocalPanel(LocalCommands commands) {

        readData = new ReadButton(commands);
        updateRecord = new UpdateButton(commands);
        createNewRecord = new CreateNewRecord(commands);
        removeRecord = new RemoveRecord(commands);

        this.setLayout(null);
        this.setVisible(true);

        readData.setLocation(5, 5);
        updateRecord.setLocation(5, 45);
        createNewRecord.setLocation(5, 85);
        removeRecord.setLocation(5, 125);

        this.add(readData);
        this.add(updateRecord);
        this.add(createNewRecord);
        this.add(removeRecord);

    }
}
