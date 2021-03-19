package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.SortingCommands;
import org.bitbucket.app.models.PeopleTableModel;

import javax.swing.*;
import java.awt.*;

public class SortingButtonsPanel extends JPanel{

    private PeopleTableModel peopleTableModel;

    private final JButton sortByIdButton = new JButton("Id");

    private final JButton sortByFirstNameButton = new JButton("First name");

    private final JButton sortByLastNameButton = new JButton("Last name");

    private final JButton sortByAgeButton = new JButton("Age");

    private final JButton sortByCityButton = new JButton("City");

    public SortingButtonsPanel(PeopleTableModel peopleTableModel) {

        this.peopleTableModel = peopleTableModel;

        this.sortByIdButton.setBounds(5, 5, 110, 30);
        this.sortByIdButton.addActionListener(SortingCommands.sortById(this.peopleTableModel));
        this.sortByFirstNameButton.setBounds(125, 5, 110, 30);
        this.sortByFirstNameButton.addActionListener(SortingCommands.sortByFirstName(this.peopleTableModel));
        this.sortByLastNameButton.setBounds(245, 5, 110, 30);
        this.sortByLastNameButton.addActionListener(SortingCommands.sortByLastName(this.peopleTableModel));
        this.sortByAgeButton.setBounds(365, 5, 110, 30);
        this.sortByAgeButton.addActionListener(SortingCommands.sortByAge(this.peopleTableModel));
        this.sortByCityButton.setBounds(485, 5, 110, 30);
        this.sortByCityButton.addActionListener(SortingCommands.sortByCity(this.peopleTableModel));

        this.add(sortByIdButton);
        this.add(sortByFirstNameButton);
        this.add(sortByLastNameButton);
        this.add(sortByAgeButton);
        this.add(sortByCityButton);

        this.setLayout(null);
        this.setSize(600, 40);
        this.setVisible(true);
    }
}