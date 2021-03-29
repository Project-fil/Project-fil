package org.bitbucket.app.config;

import org.bitbucket.app.exceptions.NoSuchDatabaseException;
import org.bitbucket.app.services.IPersonService;

public class DatabaseServiceChooser {

    public static IPersonService chooseService(String database){
        switch (database){
            case "Cassandra":
                return ServiceConfig.cassandraPersonService();
            case "Graphdb":
                return ServiceConfig.graphDBPersonService();
            case "H2":
                return ServiceConfig.h2PersonService();
            case "MongoDB":
                return ServiceConfig.mongoDBPersonService();
            case "MySQL":
                return ServiceConfig.mySqlPersonService();
            case "PostgreSQL":
                return ServiceConfig.postgreSqlPersonService();
            case "Redis":
                return ServiceConfig.redisPersonService();
        }
        throw new NoSuchDatabaseException("There is no such database.");
    }

}
