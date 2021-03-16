package org.bitbucket.app.models;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.view.PersonDialog;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PeopleTableModel extends AbstractTableModel {

    private List<Person> people = new ArrayList<>();

    private final List<Field> tableColumn;

    private IPersonService peopleService;

    public PeopleTableModel(IPersonService setService, List<Field> tableColumn) {
        this.peopleService = setService;
        this.tableColumn = tableColumn;
    }

    public PeopleTableModel refresh() {
        this.people = this.peopleService.readAll();
        return this;
    }

    public PeopleTableModel delete(Person person){
        this.peopleService.delete(person.getId());
        this.refresh();
        return this;
    }

    public PeopleTableModel create(Person person){
        this.peopleService.create(person);
        this.refresh();
        return this;
    }

    public PeopleTableModel update(Person person){
        this.peopleService.update(person);
        this.refresh();
        return this;
    }

    public void setPeopleService(IPersonService peopleService) {
        this.peopleService = peopleService;
    }

    public IPersonService getPeopleService() {
        return peopleService;
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
