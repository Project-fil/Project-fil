package org.bitbucket.app;

import org.bitbucket.app.view.*;
import org.bitbucket.app.view.panel.ButtonsPanel;

public class Main {

    public static void main(String[] args){

        new MainWindow(new CreateNewRecordWindow(),
                new UpdateWindow(),
                new RemoveRecord(),
                new ButtonsPanel(),
                new InformationView());
    }

}
