package org.bitbucket.app.models;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.services.IPeopleService;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PeopleTableModel extends AbstractTableModel {

    private List<Person> people = new ArrayList<>();

    private final List<Field> tableColumn;

    private IPeopleService peopleService;

    public PeopleTableModel(IPeopleService setService, List<Field> tableColumn) {
        this.peopleService = setService;
        this.tableColumn = tableColumn;
    }

    public PeopleTableModel refresh() {
        this.people = this.peopleService.readAll();
        return this;
    }

    public void setPeopleService(IPeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public int getRowCount() {
        return this.people.size();
    }

    @Override
    public int getColumnCount() {
        return this.tableColumn.size();
    }

    @Override
    public String getColumnName(int col) {
        return this.tableColumn.get(col)
                .getAnnotation(PersonColumn.class).name();
    }

    public Object getValueAt(int row, int col) {
        Person person = this.people.get(row);
        Field field = this.tableColumn.get(col);
        return ReflectionUtils.getValue(field, person);
    }
}
