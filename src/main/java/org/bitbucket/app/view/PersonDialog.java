package org.bitbucket.app.view;

import org.bitbucket.app.commands.PersonDialogCommands;
import org.bitbucket.app.entity.Person;

import javax.swing.*;

public class PersonDialog extends JDialog {

    private PersonDialogCommands commands;

    private final JLabel firstNameLabel = new JLabel("First name");
    private final JTextField firstNameField = new JTextField("");

    private final JLabel lastNameLabel = new JLabel("First name");
    private final JTextField lastNameField = new JTextField("");

    private final JLabel ageLabel = new JLabel("Age");
    private final JTextField ageField = new JTextField("");

    private final JLabel cityLabel = new JLabel("City");
    private final JTextField cityField = new JTextField("");

    public final JButton okButton = new JButton("OK");
    public final JButton cancelButton = new JButton("Cancel");

    public PersonDialog(PersonDialogCommands commands, Person person) {

        this.commands = commands;

        this.commands.setPersonDialog(this);

        this.setTitle("Person creation...");
        this.setModal(true);
        this.setLayout(null);
        this.setBounds(300,300,240,200);

        this.firstNameLabel.setBounds(10, 10, 70, 20);
        this.firstNameField.setBounds(90, 10, 130, 20);
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.lastNameLabel.setBounds(10, 40, 70, 20);
        this.lastNameField.setBounds(90, 40, 130, 20);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.ageLabel.setBounds(10, 70, 70, 20);
        this.ageField.setBounds(90, 70, 130, 20);
        this.add(ageLabel);
        this.add(ageField);
        this.cityLabel.setBounds(10, 100, 70, 20);
        this.cityField.setBounds(90, 100, 130, 20);
        this.add(cityLabel);
        this.add(cityField);
        if(person != null){
            firstNameField.setText(person.getFirstName());
            lastNameField.setText(person.getLastName());
            ageField.setText(String.valueOf(person.getAge()));
            cityField.setText(person.getCity());
        }

        this.okButton.setBounds(10, 130, 100, 25);
        okButton.addActionListener(commands.actionOk());
        this.cancelButton.setBounds(120, 130, 100, 25);
        cancelButton.addActionListener(commands.actionCancel());
        this.add(okButton);
        this.add(cancelButton);
    }

    public void setPerson(Person person){
        if(person != null){
            firstNameField.setText(person.getFirstName());
            lastNameField.setText(person.getLastName());
            ageField.setText(String.valueOf(person.getAge()));
            cityField.setText(person.getCity());
        }
    }

    public Person getPerson(Person person){
        int age;
        try{
            age = Integer.parseInt(this.ageField.getText());
        } catch (NumberFormatException ex){
            return null;
        }
        if(person == null) {
            return new Person(
                    this.firstNameField.getText(),
                    this.lastNameField.getText(),
                    age,
                    this.cityField.getText()
            );
        }
        else {
            return new Person(
                    person.getId(),
                    this.firstNameField.getText(),
                    this.lastNameField.getText(),
                    Integer.parseInt(this.ageField.getText()),
                    this.cityField.getText()
            );
        }
    }

    public void clear(){
        this.firstNameField.setText("");
        this.lastNameField.setText("");
        this.ageField.setText("");
        this.cityField.setText("");
    }

    public boolean isDialog(){
        return this.commands.isDialog();
    }

}
