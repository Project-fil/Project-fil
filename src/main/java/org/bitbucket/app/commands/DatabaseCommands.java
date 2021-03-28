package org.bitbucket.app.commands;

import org.bitbucket.app.config.FDBService;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.DialogCanceledException;
import org.bitbucket.app.view.PersonDialog;
import org.bitbucket.app.view.panels.PeopleTablePanel;

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

    public ActionListener read(String dbSelected) {
        return e -> {
            peopleTablePanel.peopleTableModel().setPeopleService(FDBService.chooseService(dbSelected));
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
