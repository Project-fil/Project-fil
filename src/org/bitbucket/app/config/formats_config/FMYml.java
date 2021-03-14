package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.YmlFormat;
import org.bitbucket.app.services.IPeopleService;
import org.bitbucket.app.services.locale.PersonYmlService;

import java.io.File;

public class FMYml {

    public static BaseFormat ymlFormat(){
        return new YmlFormat();
    }

    public static IPeopleService daoPersonYml(File file){
        return new PersonYmlService(file);
    }

}
