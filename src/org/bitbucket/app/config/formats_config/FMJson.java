package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.JsonFormat;
import org.bitbucket.app.services.IPeopleService;
import org.bitbucket.app.services.locale.PersonJsonService;

import java.io.File;

public class FMJson {

    public static BaseFormat jsonFormat(){
        return new JsonFormat();
    }

    public static IPeopleService daoPersonJson(File file){
        return new PersonJsonService(file);
    }

}
