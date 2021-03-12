package org.bitbucket.app.view;

import org.bitbucket.app.config.FDaoPerson;
import org.bitbucket.app.models.PeopleTableModel;
import org.bitbucket.app.repository.ICrud;

public class ViewConfig {

    private static FDaoPerson service;

    private static PeopleTableModel peopleTableModel =
            new PeopleTableModel(service).refresh();
}
