package org.bitbucket.app.view;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.models.PeopleTableModel;
import org.bitbucket.app.models.ReflectionUtils;
import org.bitbucket.app.services.PersonMockService;
import org.bitbucket.app.view.panels.ChooseRepositoryPane;
import org.bitbucket.app.view.panels.PeopleTablePanel;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.List;

public class ViewConfig {

    private static List<Field> personColumn =
            ReflectionUtils.fields(Person.class);

    private static PeopleTableModel peopleTableModel =
            new PeopleTableModel(new PersonMockService(), personColumn).
                    refresh();

    public static PeopleTablePanel peopleTablePanel(){
        return new PeopleTablePanel(peopleTableModel);
    }

    public static ChooseRepositoryPane chooseRepositoryPanel(){
        return new ChooseRepositoryPane();
    }

    public static ApplicationFrame frame(){
        PeopleTablePanel peopleTablePanel = peopleTablePanel();
        ChooseRepositoryPane chooseRepositoryPanel = chooseRepositoryPanel();
        return new ApplicationFrame(peopleTablePanel, chooseRepositoryPanel);
    }
}
