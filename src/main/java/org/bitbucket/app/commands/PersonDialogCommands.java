package org.bitbucket.app.commands;

import org.bitbucket.app.view.PersonDialog;

import java.awt.event.ActionListener;

public class PersonDialogCommands {

    private boolean isDialog  = false;

    private PersonDialog personDialog;

    public void setPersonDialog(PersonDialog personDialog){
        this.personDialog = personDialog;
    }

    public ActionListener actionOk(){
        return e -> {
            this.isDialog = true;
            this.personDialog.setVisible(false);
        };
    }

    public ActionListener actionCancel(){
        return e -> {
            this.isDialog = false;
            this.personDialog.setVisible(false);
        };
    }

    public boolean isDialog(){
        return this.isDialog;
    }
}
