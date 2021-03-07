package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.JsonFormat;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.repository.locale.DaoPersonJson;

import java.io.File;

public class FMJson {

    public static BaseFormat jsonFormat(){
        return new JsonFormat();
    }

    public static ICrud daoPersonJson(File file){
        return new DaoPersonJson(file);
    }

}
