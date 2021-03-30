package org.bitbucket.app.repository.impl;

import org.bitbucket.app.entity.Person;
import org.bitbucket.app.fomats.impl.JsonFormat;
import org.bitbucket.app.repository.IPeopleRepository;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class RedisPeopleRepository implements IPeopleRepository {

    private final JsonFormat jsonFormat = new JsonFormat();

    private Jedis jedis;

    private List<Person> people;

    private String database;

    public RedisPeopleRepository(String host, int port, String database) {
        this.database = database;
        this.jedis = new Jedis(host,port);
        this.jedis.connect();
        this.jedis.sadd(this.database,"");
    }

    @Override
    public Person create(Person p) {
        this.jedis.sadd(this.database,this.jsonFormat.toFormat(p));
        this.people.add(p);
        return new Person(p);
    }

    @Override
    public List<Person> readAll() {
        List<Person> result = new ArrayList<>();
        for(String each : jedis.smembers(this.database)){
            if(each.equals("")) continue;
            result.add(jsonFormat.fromFormat(each).get(0));
        }
        this.people = result;
        return people;
    }

    @Override
    public void update(Person p) {
        for(Person each : people){
            if(each.getId() == p.getId()){
                this.jedis.srem(this.database,this.jsonFormat.toFormat(each));
                this.people.remove(each);
                this.jedis.sadd(this.database,this.jsonFormat.toFormat(p));
                this.people.add(p);
                return;
            }
        }
    }

    @Override
    public void delete(long id) {
        for(Person each : people){
            if(each.getId() == id){
                this.jedis.srem(this.database,this.jsonFormat.toFormat(each));
                this.people.remove(each);
                return;
            }
        }
    }

}
