package org.bitbucket.app.config;

import org.bitbucket.app.commands.LocalCommands;
import org.bitbucket.app.commands.PersonDialogCommands;
import org.bitbucket.app.commands.RemoteCommands;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.models.PeopleTableModel;
import org.bitbucket.app.models.ReflectionUtils;
import org.bitbucket.app.services.PersonMockService;
import org.bitbucket.app.view.ApplicationFrame;
import org.bitbucket.app.view.PersonDialog;
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

    public static PersonDialogCommands personDialogCommands(){
        return new PersonDialogCommands();
    }

    public static PersonDialog personDialog(PersonDialogCommands personDialogCommands){
        return new PersonDialog(personDialogCommands, null);
    }

    public static LocalCommands localCommands(PersonDialog personDialog, PeopleTablePanel peopleTablePanel){
        return new LocalCommands(personDialog, peopleTablePanel);
    }

    public static RemoteCommands remoteCommands(){
        return new RemoteCommands();
    }

    public static CRUDPane crudPane(LocalCommands localCommands, RemoteCommands remoteCommands){
        return new CRUDPane(localCommands, remoteCommands);
    }

    public static ApplicationFrame frame(){
        PeopleTablePanel peopleTablePanel = peopleTablePanel();
        PersonDialogCommands personDialogCommands = personDialogCommands();
        PersonDialog personDialog = personDialog(personDialogCommands);
        LocalCommands localCommands = localCommands(personDialog, peopleTablePanel);

        RemoteCommands remoteCommands = remoteCommands();

        CRUDPane crudPane = crudPane(localCommands, remoteCommands);
        return new ApplicationFrame(peopleTablePanel, crudPane);
    }

}
