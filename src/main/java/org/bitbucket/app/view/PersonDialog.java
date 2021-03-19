package org.bitbucket.app.view;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.exceptions.DialogCanceledException;
import org.bitbucket.app.utils.PatternMatcher;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

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

    private boolean isCanceled;

    private PersonDialog(Person person) {

        this.setTitle("Person creation...");
        this.setModal(true);
        this.setLayout(null);
        this.setBounds(300,300,240,200);

        this.firstNameLabel.setBounds(10, 10, 70, 20);
        this.firstNameField.setBounds(90, 10, 130, 20);
        this.lastNameLabel.setBounds(10, 40, 70, 20);
        this.lastNameField.setBounds(90, 40, 130, 20);
        this.ageLabel.setBounds(10, 70, 70, 20);
        this.ageField.setBounds(90, 70, 130, 20);
        this.cityLabel.setBounds(10, 100, 70, 20);
        this.cityField.setBounds(90, 100, 130, 20);
        this.okButton.setBounds(10, 130, 100, 25);
        this.cancelButton.setBounds(120, 130, 100, 25);
        okButton.addActionListener(this.actionOk());
        cancelButton.addActionListener(this.actionCancel());

        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(ageLabel);
        this.add(ageField);
        this.add(cityLabel);
        this.add(cityField);
        this.add(okButton);
        this.add(cancelButton);

        this.addWindowListener(actionClose());

        this.setPerson(person);

    }

    private void setPerson(Person person){
        if(Objects.isNull(person)){
            return;
        }
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        ageField.setText(String.valueOf(person.getAge()));
        cityField.setText(person.getCity());
    }

    private Person getPerson(Person initialPerson){

        if(Objects.isNull(initialPerson)) {
            return new Person(
                this.firstNameField.getText(),
                this.lastNameField.getText(),
                Integer.parseInt(this.ageField.getText()),
                this.cityField.getText()
            );
        }
        else {
            return new Person(
                initialPerson.getId(),
                this.firstNameField.getText(),
                this.lastNameField.getText(),
                Integer.parseInt(this.ageField.getText()),
                this.cityField.getText()
            );
        }

    }


    public static Person showDialog(){
        return showDialog(null);
    }


    private boolean isValidInput(){

        boolean isValidFirstName = PatternMatcher.isValidName(this.firstNameField.getText());
        boolean isValidLastName = PatternMatcher.isValidName(this.lastNameField.getText());
        boolean isValidAge = PatternMatcher.isNumeric(this.ageField.getText());
        boolean isValidCity = PatternMatcher.isValidName(this.cityField.getText());

        boolean isValidInput = isValidFirstName && isValidLastName && isValidAge && isValidCity;

        String errorMessage = buildErrorMessage(isValidFirstName, isValidLastName, isValidAge, isValidCity);
        if(!isValidInput){
            JOptionPane.showMessageDialog(new JFrame(), errorMessage,
                    "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return isValidInput;

    }


    private static String buildErrorMessage(
            boolean isValidFirstName,
            boolean isValidLastName,
            boolean isValidAge,
            boolean isValidCity){

        StringBuilder builder = new StringBuilder("These fields are not valid: ");
        boolean isValidInput = true;
        if(!isValidFirstName){
            builder.append("first name");
            isValidInput = false;
        }
        if(!isValidLastName){
            if(!isValidInput){
                builder.append(", ");
            }
            builder.append("last name");
            isValidInput = false;
        }
        if(!isValidAge){
            if(!isValidInput){
                builder.append(", ");
            }
            builder.append("age");
            isValidInput = false;
        }
        if(!isValidCity){
            if(!isValidInput){
                builder.append(", ");
            }
            builder.append("city");
        }
        builder.append(". Please enter a valid data.");
        return builder.toString();
    }


    private ActionListener actionOk(){
        return e -> {
            if(isValidInput()) {
                this.setVisible(false);
                this.isCanceled = false;
            }
        };
    }

    private ActionListener actionCancel(){
        return e -> {
            this.setVisible(false);
            this.isCanceled = true;
        };
    }

    private WindowListener actionClose(){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isCanceled = true;
            }
        };
    }

    public static Person showDialog(Person p){
        PersonDialog dialog = new PersonDialog(p);
        dialog.setVisible(true);
        if(dialog.isCanceled){
            throw new DialogCanceledException();
        }
        return dialog.getPerson(p);
    }

}
