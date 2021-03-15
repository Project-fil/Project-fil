package org.bitbucket.app.config;

import org.bitbucket.app.commands.LocalCommands;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.models.PeopleTableModel;
import org.bitbucket.app.models.ReflectionUtils;
import org.bitbucket.app.services.PersonMockService;
import org.bitbucket.app.view.ApplicationFrame;
import org.bitbucket.app.view.panels.CRUDPane;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import java.lang.reflect.Field;
import java.util.List;

public class ViewConfig {

    private static List<Field> personColumn =
            ReflectionUtils.fields(Person.class);

    private static PeopleTableModel peopleTableModel =
            new PeopleTableModel(new PersonMockService(), personColumn).
                    refresh();

    public static PeopleTableModel peopleTableModel() {
        return peopleTableModel;
    }

    public static LocalCommands localCommands(PeopleTablePanel peopleTablePanel){
        return new LocalCommands(peopleTablePanel);
    }

    public static PeopleTablePanel peopleTablePanel(){
        return new PeopleTablePanel(peopleTableModel);
    }

    public static CRUDPane chooseRepositoryPanel(PeopleTablePanel peopleTablePanel){
        return new CRUDPane(peopleTablePanel);
    }

    public static ApplicationFrame frame(){
        PeopleTablePanel peopleTablePanel = peopleTablePanel();
        CRUDPane chooseRepositoryPanel = chooseRepositoryPanel(peopleTablePanel);
        return new ApplicationFrame(peopleTablePanel, chooseRepositoryPanel);
    }
}
