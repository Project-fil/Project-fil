package org.bitbucket.app.commands;

import org.bitbucket.app.models.PeopleTableModel;

import java.awt.event.ActionListener;

public class SortingCommands {

    public static ActionListener sortById(PeopleTableModel peopleTableModel){
        return e -> peopleTableModel.sortById();
    }

    public static ActionListener sortByFirstName(PeopleTableModel peopleTableModel){
        return e -> peopleTableModel.sortByFirstName();
    }

    public static ActionListener sortByLastName(PeopleTableModel peopleTableModel){
        return e -> peopleTableModel.sortByLastName();
    }

    public static ActionListener sortByAge(PeopleTableModel peopleTableModel){
        return e -> peopleTableModel.sortByAge();
    }

    public static ActionListener sortByCity(PeopleTableModel peopleTableModel){
        return e -> peopleTableModel.sortByCity();
    }

}
