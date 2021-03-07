package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.XmlFormat;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.repository.locale.DaoPersonXml;

import java.io.File;

public class FMXml {

    public static BaseFormat xmlFormat(){
        return new XmlFormat();
    }

    public static ICrud daoPersonXml(File file){
        return new DaoPersonXml(file);
    }

}
