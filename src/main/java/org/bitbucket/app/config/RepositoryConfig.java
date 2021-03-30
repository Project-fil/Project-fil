package org.bitbucket.app.config;

import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.repository.impl.*;
import org.bitbucket.app.utils.JDBCConnectionPool;

public class RepositoryConfig {

    public static IPeopleRepository cassandraPeopleRepository() {
        return new CassandraPeopleRepository(
                "127.0.0.1",
                "cassandra",
                "cassandra",
                9042,
                "people"
        );
    }
    public static IPeopleRepository graphDBPeopleRepository() {
        return new GraphDBPeopleRepository(new JDBCConnectionPool(
                30000,
                "org.graphdb.driver",
                "jdbc:orient://localhost:7200/people",
                "root",
                "password"));
    }

    public static IPeopleRepository h2PeopleRepository() {
        return new H2PeopleRepository(new JDBCConnectionPool(
                300000,
                "org.h2.Driver",
                "jdbc:h2:~/test",
                "",
                ""));
    }
    public static IPeopleRepository mongoDBPeopleRepository() {
        return new MongoDBPeopleRepository(
                "localhost",
                27017,
                "mongo",
                "password",
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
        return new RedisPeopleRepository(
                "localhost",
                6379,
                "people"
        );
    }

}
