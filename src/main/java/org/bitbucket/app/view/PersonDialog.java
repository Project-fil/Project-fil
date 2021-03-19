package org.bitbucket.app.view;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.utils.PatternMatcher;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PersonDialog extends JDialog {

    private final JLabel firstNameLabel = new JLabel("First name");
    private final JTextField firstNameField = new JTextField("");

    private final JLabel lastNameLabel = new JLabel("Last name");
    private final JTextField lastNameField = new JTextField("");

    private final JLabel ageLabel = new JLabel("Age");
    private final JTextField ageField = new JTextField("");

    private final JLabel cityLabel = new JLabel("City");
    private final JTextField cityField = new JTextField("");

    public final JButton okButton = new JButton("OK");
    public final JButton cancelButton = new JButton("Cancel");

    private boolean canceled;

    private PersonDialog(Person person) {

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

        this.setPerson(person);

        this.okButton.setBounds(10, 130, 100, 25);
        okButton.addActionListener(this.actionOk());
        this.cancelButton.setBounds(120, 130, 100, 25);
        cancelButton.addActionListener(this.actionCancel());
        this.add(okButton);
        this.add(cancelButton);
    }

    private void setPerson(Person person){
        if(person != null){
            firstNameField.setText(person.getFirstName());
            lastNameField.setText(person.getLastName());
            ageField.setText(String.valueOf(person.getAge()));
            cityField.setText(person.getCity());
        }
    }

    private Person getPerson(Person initialPerson){
        int age;
        try{
            age = Integer.parseInt(this.ageField.getText());
        } catch (NumberFormatException ex){
            return null;
        }
        if(initialPerson == null) {
            return new Person(
                this.firstNameField.getText(),
                this.lastNameField.getText(),
                age,
                this.cityField.getText()
            );
        }
        else {
            return new Person(
                initialPerson.getId(),
                this.firstNameField.getText(),
                this.lastNameField.getText(),
                age,
                this.cityField.getText()
            );
        }
    }

    public static Person showDialog(Person p){
        PersonDialog dialog = new PersonDialog(p);
        dialog.setVisible(true);
        if(dialog.canceled){
            return null;
        }
        return dialog.getPerson(p);
    }

    public static Person showDialog(){
        return showDialog(null);
    }

    private boolean isValidInput(){
        boolean isValidFirstName = PatternMatcher.isValidName(this.firstNameField.getText());
        boolean isValidLastName = PatternMatcher.isValidName(this.lastNameField.getText());
        boolean isValidAge = PatternMatcher.isNumeric(this.ageField.getText());
        boolean isValidCity = PatternMatcher.isValidName(this.cityField.getText());
        boolean isValidInput = true;
        StringBuilder stringBuilder = new StringBuilder("These fields are not valid: ");
        if(!isValidFirstName){
            stringBuilder.append("first name");
            isValidInput = false;
        }
        if(!isValidLastName){
            if(!isValidInput){
                stringBuilder.append(", ");
            }
            stringBuilder.append("last name");
            isValidInput = false;
        }
        if(!isValidAge){
            if(!isValidInput){
                stringBuilder.append(", ");
            }
            stringBuilder.append("age");
            isValidInput = false;
        }
        if(!isValidCity){
            if(!isValidInput){
                stringBuilder.append(", ");
            }
            stringBuilder.append("city");
            isValidInput = false;
        }
        stringBuilder.append(". Please enter a valid data.");
        if(!isValidInput){
            JOptionPane.showMessageDialog(new JFrame(), stringBuilder.toString(),
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return isValidInput;
    }

    private ActionListener actionOk(){
        return e -> {
            if(isValidInput()) {
                this.canceled = false;
                this.setVisible(false);
            }
        };
    }

    private ActionListener actionCancel(){
        return e -> {
            this.canceled = true;
            this.setVisible(false);
        };
    }

}
