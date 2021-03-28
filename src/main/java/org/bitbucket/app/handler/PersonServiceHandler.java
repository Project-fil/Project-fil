package org.bitbucket.app.handler;

import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.utils.PersonNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

public class PersonServiceHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceHandler.class);

    private final IPersonService service;

    public PersonServiceHandler(IPersonService service) {
        this.service = service;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Before call to method: {}, with args: {}", method.getName(), args);
        Parameter[] params = method.getParameters();
        for (int i = 0; i < params.length; i++) {
            if(params[i].isAnnotationPresent(PersonNotNull.class)){
                if(Objects.isNull(args[i])){
                    throw new IllegalArgumentException("Person can not be null!");
                }
            }
        }
        Object result = method.invoke(service, args);
        logger.info("After call to method: {}", result);
        return result;
    }
}
