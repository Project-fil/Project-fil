package org.bitbucket.app;

import org.bitbucket.app.view.*;

public class Main {

    public static void main(String[] args){

        new MainWindow(new CreateNewRecordWindow(),
                new UpdateWindow(),
                new RemoveRecord(),
                new ReadWindow(),
                new ControlsView(),
                new InformationView());
    }

}
