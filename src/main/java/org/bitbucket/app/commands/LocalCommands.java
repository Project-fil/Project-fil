package org.bitbucket.app.commands;

import org.bitbucket.app.config.FPersonService;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Field;

public class LocalCommands {

    private PeopleTablePanel peopleTablePanel;

    public LocalCommands(PeopleTablePanel peopleTablePanel) {
        this.peopleTablePanel = peopleTablePanel;
    }

    public final ActionListener create = e -> {

    };

    public final ActionListener read = e -> {
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

    public final ActionListener update = e -> {

    };

    public final ActionListener delete = e -> {
        int row = this.peopleTablePanel.peopleTable().getSelectedRow();
        Person person = new Person();
        Class<? extends Person> clz = person.getClass();
        Field[] fields = clz.getDeclaredFields();
        try{
            for( int i = 1; i < fields.length; i++){
                fields[i].setAccessible(true);
                Object tmp = this.peopleTablePanel.peopleTableModel().getValueAt(row, i - 1);
                if(long.class.equals(fields[i].getType())){
                    fields[i].setLong(person, Long.parseLong(String.valueOf(tmp)));
                }
                if(int.class.equals(fields[i].getType())){
                    fields[i].setInt(person, Integer.parseInt(String.valueOf(tmp)));
                }
                if(String.class.equals(fields[i].getType())){
                    fields[i].set(person, String.valueOf(tmp));
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        this.peopleTablePanel.peopleTableModel().delete(person);
        this.peopleTablePanel.peopleTableModel().refresh();
        this.peopleTablePanel.repaint();
    };



}
