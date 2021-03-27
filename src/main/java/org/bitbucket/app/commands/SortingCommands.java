package org.bitbucket.app.commands;

import org.bitbucket.app.view.panels.PeopleTablePanel;

import java.awt.event.ActionListener;

public class SortingCommands {

    public static ActionListener sortById(PeopleTablePanel peopleTablePanel){
        return e -> {
            peopleTablePanel.peopleTableModel().sortById();
            peopleTablePanel.repaint();
        };
    }

    public static ActionListener sortByFirstName(PeopleTablePanel peopleTablePanel){
        return e -> {
            peopleTablePanel.peopleTableModel().sortByFirstName();
            peopleTablePanel.repaint();
        };
    }

    public static ActionListener sortByLastName(PeopleTablePanel peopleTablePanel){
        return e -> {
            peopleTablePanel.peopleTableModel().sortByLastName();
            peopleTablePanel.repaint();
        };
    }

    public static ActionListener sortByAge(PeopleTablePanel peopleTablePanel){
        return e -> {
            peopleTablePanel.peopleTableModel().sortByAge();
            peopleTablePanel.repaint();
        };
    }

    public static ActionListener sortByCity(PeopleTablePanel peopleTablePanel){
        return e -> {
            peopleTablePanel.peopleTableModel().sortByCity();
            peopleTablePanel.repaint();
        };
    }

}
