package org.bitbucket.app.config;

import org.bitbucket.app.repository.IPeopleRepository;
import org.bitbucket.app.repository.impl.*;
import org.bitbucket.app.utils.JDBCConnectionPool;

public class RepositoryConfig {

    public static IPeopleRepository cassandraPeopleRepository() {
        return new CassandraPeopleRepository();
    }
    public static IPeopleRepository graphDBPeopleRepository() {
        return new GraphDBPeopleRepository();
    }
    public static IPeopleRepository h2PeopleRepository() {
        return new H2PeopleRepository();
    }
    public static IPeopleRepository mongoDBPeopleRepository() {
        return new MongoDBPeopleRepository();
    }
    public static IPeopleRepository mySqlPeopleRepository() {
        return new MySqlPeopleRepository(new JDBCConnectionPool(
                30000,
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/crud",
                "root",
                "12345"));
    }
    public static IPeopleRepository postgreSQLPeopleRepository() {
        return new PostgreSQLPeopleRepository(new JDBCConnectionPool(
                30000,
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/crud",
                "root",
                "12345"));
    }
    public static IPeopleRepository redisPeopleRepository() {
        return new RedisPeopleRepository();
    }

}
