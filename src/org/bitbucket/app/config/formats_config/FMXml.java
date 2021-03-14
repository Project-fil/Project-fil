package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.XmlFormat;
import org.bitbucket.app.services.IPeopleService;
import org.bitbucket.app.services.locale.PersonXmlService;

import java.io.File;

public class FMXml {

    public static BaseFormat xmlFormat(){
        return new XmlFormat();
    }

    public static IPeopleService daoPersonXml(File file){
        return new PersonXmlService(file);
    }

}
