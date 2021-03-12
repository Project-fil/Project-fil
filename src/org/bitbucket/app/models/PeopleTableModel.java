package org.bitbucket.app.models;

import org.bitbucket.app.config.FDaoPerson;
import org.bitbucket.app.entity.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PeopleTableModel extends AbstractTableModel {

    private static String[] columns = new String[]{
            "ID", "Firstname", "Lastname", "Age", "City"
    };

    private ArrayList<Person> people = new ArrayList<>();

    private static FDaoPerson crudService;

    public PeopleTableModel(FDaoPerson setService) {
        this.crudService = setService;
    }

    public PeopleTableModel refresh() {
        return this;
    }

    public void setPeopleService(FDaoPerson setService) {
        this.crudService = setService;
    }

    @Override
    public int getRowCount() {
        return this.people.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    public Object getValueAt(int row, int col) {
        Object result;
        Person p = this.people.get(row);
        switch(col) {
            case 0:
                result = p.getId();
                break;
            case 1:
                result = p.getFirstName();
                break;
            case 2:
                result = p.getLastName();
                break;
            case 3:
                result = p.getAge();
                break;
            case 4:
                result = p.getCity();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
}
