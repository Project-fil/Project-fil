package org.bitbucket.app.config;

import org.bitbucket.app.commands.FileCommands;
import org.bitbucket.app.commands.DatabaseCommands;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.models.PeopleTableModel;
import org.bitbucket.app.models.ReflectionUtils;
import org.bitbucket.app.services.PersonMockService;
import org.bitbucket.app.view.ApplicationFrame;
import org.bitbucket.app.view.panels.CRUDPane;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import java.lang.reflect.Field;
import java.util.List;

public class ViewConfig{

    private static List<Field> personColumn() {
        return ReflectionUtils.fields(Person.class);
    }

    private static final PeopleTableModel startingPeopleTableModel =
            new PeopleTableModel(new PersonMockService(), personColumn()).refresh();

    public static PeopleTablePanel peopleTablePanel(){
        return new PeopleTablePanel(startingPeopleTableModel);
    }

    public static FileCommands localFileCommands(PeopleTablePanel peopleTablePanel){
        return new FileCommands(peopleTablePanel);
    }

    public static DatabaseCommands databaseCommands(PeopleTablePanel peopleTablePanel){
        return new DatabaseCommands(peopleTablePanel);
    }

    public static CRUDPane crudPane(FileCommands fileCommands, DatabaseCommands databaseCommands){
        return new CRUDPane(fileCommands, databaseCommands);
    }

    public static ApplicationFrame frame(){
        PeopleTablePanel peopleTablePanel = peopleTablePanel();
        FileCommands fileCommands = localFileCommands(peopleTablePanel);
        DatabaseCommands databaseCommands = databaseCommands(peopleTablePanel);
        CRUDPane crudPane = crudPane(fileCommands, databaseCommands);
        return new ApplicationFrame(peopleTablePanel, crudPane);
    }

}
