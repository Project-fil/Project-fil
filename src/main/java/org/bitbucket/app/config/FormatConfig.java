package org.bitbucket.app.config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.BinFormat;
import org.bitbucket.app.fomats.impl.CsvFormat;
import org.bitbucket.app.fomats.impl.JsonFormat;
import org.bitbucket.app.fomats.impl.XmlFormat;
import org.bitbucket.app.fomats.impl.YmlFormat;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.services.local.file.*;

import java.io.File;

public class FormatConfig {

    public static BinFormat binFormat(){
        return new BinFormat();
    }

    public static BaseFormat csvFormat(){
        return new CsvFormat();
    }

    public static BaseFormat jsonFormat(){
        return new JsonFormat();
    }

    public static BaseFormat xmlFormat(){
        return new XmlFormat();
    }

    public static BaseFormat ymlFormat(){
        return new YmlFormat();
    }

}
