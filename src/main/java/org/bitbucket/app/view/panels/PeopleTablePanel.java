package org.bitbucket.app.view.panels;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.models.PeopleTableModel;

import javax.swing.*;
import java.lang.reflect.Field;

public class PeopleTablePanel extends JPanel {

    private final JTable peopleTable;

    private final PeopleTableModel peopleTableModel;

    public JTable peopleTable() {
        return peopleTable;
    }

    public PeopleTableModel peopleTableModel() {
        return peopleTableModel;
    }

    public Person getSelectedPerson(){
        int row = this.peopleTable().getSelectedRow();
        Person person = new Person();
        Class<? extends Person> clz = person.getClass();
        Field[] fields = clz.getDeclaredFields();
        try {
            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object tmp = this.peopleTableModel().getValueAt(row, i - 1);
                if (long.class.equals(fields[i].getType())) {
                    fields[i].setLong(person, Long.parseLong(String.valueOf(tmp)));
                }
                if (int.class.equals(fields[i].getType())) {
                    fields[i].setInt(person, Integer.parseInt(String.valueOf(tmp)));
                }
                if (String.class.equals(fields[i].getType())) {
                    fields[i].set(person, String.valueOf(tmp));
                }
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return person;
    }

    public PeopleTablePanel(PeopleTableModel peopleTableModel){

        this.setLayout(null);
        this.setBounds(210, 15, 480, 500);

        this.peopleTableModel = peopleTableModel;
        this.peopleTable = new JTable(this.peopleTableModel);
        this.peopleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.peopleTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        this.peopleTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.peopleTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.peopleTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        this.peopleTable.getColumnModel().getColumn(4).setPreferredWidth(90);

        JScrollPane scrollPane = new JScrollPane(this.peopleTable);
        scrollPane.setBounds(5, 5, 480, 500);

        this.add(scrollPane);
        this.setVisible(true);
    }

}
