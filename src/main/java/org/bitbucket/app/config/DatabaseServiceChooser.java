package org.bitbucket.app.config;

import org.bitbucket.app.exceptions.NoSuchDatabaseException;
import org.bitbucket.app.services.IPersonService;

public class DatabaseServiceChooser {

    public static IPersonService chooseService(String database){
        switch (database){
            case "cassandra":
                return ServiceConfig.cassandraPersonService();
            case "graphdb":
                return ServiceConfig.graphDBPersonService();
            case "h2":
                return ServiceConfig.h2PersonService();
            case "mongodb":
                return ServiceConfig.mongoDBPersonService();
            case "mysql":
                return ServiceConfig.mySqlPersonService();
            case "postgresql":
                return ServiceConfig.postgreSqlPersonService();
            case "redis":
                return ServiceConfig.redisPersonService();
        }
        throw new NoSuchDatabaseException("There is no such database.");
    }

}
