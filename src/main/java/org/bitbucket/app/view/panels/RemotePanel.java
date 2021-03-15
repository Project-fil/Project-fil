package org.bitbucket.app.view.panels;

import org.bitbucket.app.view.buttons.local.CreateNewRecord;
import org.bitbucket.app.view.buttons.local.ReadButton;
import org.bitbucket.app.view.buttons.local.RemoveRecord;
import org.bitbucket.app.view.buttons.local.UpdateButton;

import javax.swing.*;

public class RemotePanel extends JPanel {

    public final JButton readData;
    public final JButton updateRecord ;
    public final JButton createNewRecord;
    public final JButton removeRecord;

    public RemotePanel(PeopleTablePanel peopleTablePanel) {

        readData = new ReadButton(peopleTablePanel);
        updateRecord = new UpdateButton();
        createNewRecord = new CreateNewRecord();
        removeRecord = new RemoveRecord(peopleTablePanel);

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
