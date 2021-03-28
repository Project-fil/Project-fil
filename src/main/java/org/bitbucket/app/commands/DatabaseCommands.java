package org.bitbucket.app.commands;

import org.bitbucket.app.config.DatabaseServiceChooser;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.DialogCanceledException;
import org.bitbucket.app.view.PersonDialog;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DatabaseCommands {

    private PeopleTablePanel peopleTablePanel;

    public DatabaseCommands(PeopleTablePanel peopleTablePanel) {
        this.peopleTablePanel = peopleTablePanel;
    }

    public ActionListener create() {
        return e -> {
            try {
                Person person = PersonDialog.showDialog();
                this.peopleTablePanel.peopleTableModel().create(person);
                this.peopleTablePanel.peopleTable().revalidate();
                this.peopleTablePanel.repaint();
            } catch (DialogCanceledException ignore) {
            }
        };
    }

    public ActionListener read() {
        return e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            String selectedDatabase = (String) comboBox.getSelectedItem();
            assert selectedDatabase != null;
            peopleTablePanel.peopleTableModel().setPeopleService(DatabaseServiceChooser.chooseService(selectedDatabase));
            peopleTablePanel.peopleTableModel().refresh();
            this.peopleTablePanel.peopleTable().revalidate();
            peopleTablePanel.repaint();
        };
    }

    public ActionListener update() {
        return e -> {
            try {
                Person person = this.peopleTablePanel.getSelectedPerson();
                Person updatedPerson = PersonDialog.showDialog(person);
                this.peopleTablePanel.peopleTableModel().update(updatedPerson);
                this.peopleTablePanel.peopleTable().revalidate();
                this.peopleTablePanel.repaint();
            } catch (IndexOutOfBoundsException | DialogCanceledException ignore) {
            }
        };
    }

    public ActionListener delete() {
        return e -> {
            try {
                Person person = this.peopleTablePanel.getSelectedPerson();
                this.peopleTablePanel.peopleTableModel().delete(person);
                this.peopleTablePanel.peopleTableModel().refresh();
                this.peopleTablePanel.peopleTable().revalidate();
                this.peopleTablePanel.repaint();
            } catch (IndexOutOfBoundsException ignore) {
            }
        };
    }
}
