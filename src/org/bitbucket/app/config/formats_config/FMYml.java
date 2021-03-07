package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.YmlFormat;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.repository.locale.DaoPersonYml;

import java.io.File;

public class FMYml {

    public static BaseFormat ymlFormat(){
        return new YmlFormat();
    }

    public static ICrud daoPersonYml(File file){
        return new DaoPersonYml(file);
    }

}
