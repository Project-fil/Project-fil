package org.bitbucket.app.config;

import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.repository.impl.*;
import org.bitbucket.app.utils.JDBCConnectionPool;

public class RepositoryConfig {

    public static IPeopleRepository cassandraPeopleRepository() {
        return new CassandraPeopleRepository(new JDBCConnectionPool(
                30000,
                "cdata.jdbc.cassandra.CassandraDriver",
                "jdbc:cassandra:Database=MyCassandraDB://localhost:7000/people",
                "root",
                "password"));
    }
    public static IPeopleRepository graphDBPeopleRepository() {
        return new GraphDBPeopleRepository();
    }

    public static IPeopleRepository h2PeopleRepository() {
        return new H2PeopleRepository(new JDBCConnectionPool(
                30000,
                "org.h2.Driver",
                "jdbc:h2://localhost:8082/people",
                "root",
                "password"));
    }
    public static IPeopleRepository mongoDBPeopleRepository() {
        return new MongoDBPeopleRepository(
                "localhost",
                27017,
                "mongo",
                new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'},
                "people"
        );
    }
    public static IPeopleRepository mySqlPeopleRepository() {
        return new MySqlPeopleRepository(new JDBCConnectionPool(
                30000,
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3305/people",
                "root",
                "password"));
    }
    public static IPeopleRepository postgreSQLPeopleRepository() {
        return new PostgreSQLPeopleRepository(new JDBCConnectionPool(
                30000,
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5431/people",
                "postgres",
                "password"));
    }
    public static IPeopleRepository redisPeopleRepository() {
        return new RedisPeopleRepository(new JDBCConnectionPool(
                30000,
                "cdata.jdbc.redis.RedisDriver",
                "jdbc:redis:Server=127.0.0.1//localhost:6379/crud",
                "root",
                "password"));
    }

}
