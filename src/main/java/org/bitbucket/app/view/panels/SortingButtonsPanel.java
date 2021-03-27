package org.bitbucket.app.view.panels;

import org.bitbucket.app.commands.SortingCommands;

import javax.swing.*;

public class SortingButtonsPanel extends JPanel{

    private PeopleTablePanel peopleTablePanel;

    private final JButton sortByIdButton = new JButton("Id");

    private final JButton sortByFirstNameButton = new JButton("First name");

    private final JButton sortByLastNameButton = new JButton("Last name");

    private final JButton sortByAgeButton = new JButton("Age");

    private final JButton sortByCityButton = new JButton("City");

    public SortingButtonsPanel(PeopleTablePanel peopleTablePanel) {

        this.peopleTablePanel = peopleTablePanel;

        this.sortByIdButton.setBounds(5, 5, 110, 30);
        this.sortByFirstNameButton.setBounds(125, 5, 110, 30);
        this.sortByLastNameButton.setBounds(245, 5, 110, 30);
        this.sortByAgeButton.setBounds(365, 5, 110, 30);
        this.sortByCityButton.setBounds(485, 5, 110, 30);

        this.sortByIdButton.addActionListener(SortingCommands.sortById(this.peopleTablePanel));
        this.sortByFirstNameButton.addActionListener(SortingCommands.sortByFirstName(this.peopleTablePanel));
        this.sortByLastNameButton.addActionListener(SortingCommands.sortByLastName(this.peopleTablePanel));
        this.sortByAgeButton.addActionListener(SortingCommands.sortByAge(this.peopleTablePanel));
        this.sortByCityButton.addActionListener(SortingCommands.sortByCity(this.peopleTablePanel));

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