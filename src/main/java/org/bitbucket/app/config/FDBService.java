package org.bitbucket.app.config;

import org.bitbucket.app.exceptions.WrongDBException;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.services.database.*;

public class FDBService {

    public static IPersonService chooseService(String data){
        switch (data) {
            case "mysql":
                return new MySqlPersonService();
            case "postgres":
                return new PostgreSqlPersonService();
            case "cassandra":
                return new CassandraPersonService();
            case "graphdb":
                return new GraphDBPersonService();
            case "mongodb":
                return new MongoDBPersonService();
            case "h2":
                return new H2PersonService();
            case "redis":
                return new RedisPersonService();
        }
        throw new WrongDBException("Not supported database");
    }
}
