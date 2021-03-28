package org.bitbucket.app.config;

import org.bitbucket.app.handler.PersonServiceHandler;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.services.database.*;
import org.bitbucket.app.services.local.file.*;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ServiceConfig {

    public static IPersonService binPersonService(File file){
        IPersonService origin = new BinPersonService(file);
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService csvPersonService(File file){
        IPersonService origin = new CsvPersonService(file);
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService jsonPersonService(File file){
        IPersonService origin = new JsonPersonService(file);
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService xmlPersonService(File file){
        IPersonService origin = new XmlPersonService(file);
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService ymlPersonService(File file){
        IPersonService origin = new YmlPersonService(file);
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }
    public static IPersonService cassandraPersonService(){
        IPersonService origin = new CassandraPersonService();
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService graphDBPersonService(){
        IPersonService origin = new GraphDBPersonService();
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService h2PersonService(){
        IPersonService origin = new H2PersonService(RepositoryConfig.h2PeopleRepository());
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService mongoDBPersonService(){
        IPersonService origin = new MongoDBPersonService(RepositoryConfig.mongoDBPeopleRepository());
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService mySqlPersonService(){
        IPersonService origin = new MySqlPersonService(RepositoryConfig.mySqlPeopleRepository());
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService postgreSqlPersonService(){
        IPersonService origin = new PostgreSqlPersonService();
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }

    public static IPersonService redisPersonService(){
        IPersonService origin = new RedisPersonService();
        InvocationHandler handler = new PersonServiceHandler(origin);
        return (IPersonService) Proxy.newProxyInstance(
                origin.getClass().getClassLoader(),
                new Class[] {IPersonService.class},
                handler
        );
    }
}
