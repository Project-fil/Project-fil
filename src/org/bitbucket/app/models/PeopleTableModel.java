package org.bitbucket.app.models;

import org.bitbucket.app.config.FDaoPerson;
import org.bitbucket.app.entity.Person;
import org.bitbucket.app.utils.PersonColumns;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PeopleTableModel extends AbstractTableModel {

    private static String[] columns = new String[]{
            "ID", "Firstname", "Lastname", "Age", "City"
    };

    private ArrayList<Person> people = new ArrayList<>();

    private static FDaoPerson crudService;

    private List<Field> columnField;

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
        this.se
        return this.tableColumns.get(col)
                .getAnnotation(PersonColumns.class).name();
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
