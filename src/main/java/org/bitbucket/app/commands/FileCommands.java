package org.bitbucket.app.commands;

import org.bitbucket.app.config.FileServiceChooser;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.DialogCanceledException;
import org.bitbucket.app.view.PersonDialog;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;

public class FileCommands {

    private final PeopleTablePanel peopleTablePanel;

    private static final JFileChooser fileChooser = new JFileChooser();

    public FileCommands(PeopleTablePanel peopleTablePanel) {
        this.peopleTablePanel = peopleTablePanel;
        configureFileChooser();
    }

    private void configureFileChooser(){
        FileNameExtensionFilter[] extensionFilters = new FileNameExtensionFilter[5];
        extensionFilters[0] = new FileNameExtensionFilter(".bin", "bin");
        extensionFilters[1] = new FileNameExtensionFilter(".csv", "csv");
        extensionFilters[2] = new FileNameExtensionFilter(".json", "json");
        extensionFilters[3] = new FileNameExtensionFilter(".xml", "xml");
        extensionFilters[4] = new FileNameExtensionFilter(".yml", "yml");
        for (FileNameExtensionFilter filter : extensionFilters) {
            fileChooser.addChoosableFileFilter(filter);
        }
    }

    public ActionListener create() {
        return e -> {
            try {
                Person person = PersonDialog.showDialog();
                this.peopleTablePanel.peopleTableModel().create(person);
                this.peopleTablePanel.peopleTable().revalidate();
                this.peopleTablePanel.repaint();
            } catch (DialogCanceledException ignore) { }
        };
    }

    public ActionListener read() {
        return e -> {
            int rVal = fileChooser.showOpenDialog(this.peopleTablePanel);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                peopleTablePanel.peopleTableModel().setPeopleService(FileServiceChooser.chooseService(file));
                peopleTablePanel.peopleTableModel().refresh();
                peopleTablePanel.peopleTable().revalidate();
                peopleTablePanel.repaint();
            }
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
            } catch (IndexOutOfBoundsException | DialogCanceledException ignore) { }
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
            } catch (IndexOutOfBoundsException ignore) { }
        };
    }

}
