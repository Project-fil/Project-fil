package org.bitbucket.app.commands;

import org.bitbucket.app.config.FPersonService;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.view.PersonDialog;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;

public class LocalCommands {

    private final PeopleTablePanel peopleTablePanel;

    public LocalCommands(PeopleTablePanel peopleTablePanel) {
        this.peopleTablePanel = peopleTablePanel;
    }

    public ActionListener create() {
        return e -> {
            Person person = PersonDialog.showDialog();
            if(person != null) {
                this.peopleTablePanel.peopleTableModel().create(person);
                this.peopleTablePanel.peopleTable().revalidate();
                this.peopleTablePanel.repaint();
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
                this.peopleTablePanel.peopleTable().revalidate();
                peopleTablePanel.repaint();
            }
        };
    }

    public ActionListener update() {
        return e -> {
            try {
                Person person = this.peopleTablePanel.getSelectedPerson();
                Person updatedPerson = PersonDialog.showDialog(person);
                if(updatedPerson != null) {
                    this.peopleTablePanel.peopleTableModel().update(updatedPerson);
                    this.peopleTablePanel.peopleTable().revalidate();
                    this.peopleTablePanel.repaint();
                }
            } catch (IndexOutOfBoundsException ignore){ }
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
            } catch (IndexOutOfBoundsException ignore){ }
        };
    }

}
