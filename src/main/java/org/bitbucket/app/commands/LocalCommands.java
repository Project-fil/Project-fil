package org.bitbucket.app.commands;

import org.bitbucket.app.config.FPersonService;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.view.PersonDialog;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;

public class LocalCommands {

    private final PeopleTablePanel peopleTablePanel;

    private final PersonDialog personDialog;

    public LocalCommands(PersonDialog dialog, PeopleTablePanel peopleTablePanel) {
        this.personDialog = dialog;
        this.peopleTablePanel = peopleTablePanel;
    }

    public ActionListener create() {
        return e -> {
            this.personDialog.setVisible(Boolean.TRUE);
            if (this.personDialog.isDialog()) {
                Person person = this.personDialog.getPerson(null);
                this.peopleTablePanel.peopleTableModel().create(person);
                this.peopleTablePanel.peopleTable().revalidate();
                this.peopleTablePanel.repaint();
                this.personDialog.clear();
            }
        };
    }

    public ActionListener read(){
        return e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter[] extensionFilters = new FileNameExtensionFilter[5];
            extensionFilters[0] = new FileNameExtensionFilter(".bin", "bin");
            extensionFilters[1] = new FileNameExtensionFilter(".csv", "csv");
            extensionFilters[2] = new FileNameExtensionFilter(".json", "json");
            extensionFilters[3] = new FileNameExtensionFilter(".xml", "xml");
            extensionFilters[4] = new FileNameExtensionFilter(".yml", "yml");
            for(FileNameExtensionFilter filter : extensionFilters){
                fileChooser.addChoosableFileFilter(filter);
            }
            int rVal = fileChooser.showOpenDialog(null);
            if(rVal == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                peopleTablePanel.peopleTableModel().setPeopleService(FPersonService.chooseService(file));
                peopleTablePanel.peopleTableModel().refresh();
                peopleTablePanel.repaint();
            }
        };
    }

    public ActionListener update() {
        return e -> {
            Person person = this.peopleTablePanel.getSelectedPerson();
            this.personDialog.setPerson(person);
            this.personDialog.setVisible(Boolean.TRUE);
            if (this.personDialog.isDialog()) {
                person = this.personDialog.getPerson(person);
                this.peopleTablePanel.peopleTableModel().update(person);
                this.peopleTablePanel.peopleTable().revalidate();
                this.peopleTablePanel.repaint();
            }
            this.personDialog.clear();
        };
    }

    public ActionListener delete() {
        return e -> {
            Person person = this.peopleTablePanel.getSelectedPerson();
            this.peopleTablePanel.peopleTableModel().delete(person);
            this.peopleTablePanel.peopleTableModel().refresh();
            this.peopleTablePanel.repaint();
        };
    }

}
