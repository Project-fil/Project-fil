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

    public static IPersonService binPersonService(File file){
        return new BinPersonService(file);
    }

    public static BaseFormat csvFormat(){
        return new CsvFormat();
    }

    public static IPersonService csvPersonService(File file){
        return new CsvPersonService(file);
    }

    public static BaseFormat jsonFormat(){
        return new JsonFormat();
    }

    public static IPersonService jsonPersonService(File file){
        return new JsonPersonService(file);
    }

    public static BaseFormat xmlFormat(){
        return new XmlFormat();
    }

    public static IPersonService xmlPersonService(File file){
        return new XmlPersonService(file);
    }

    public static BaseFormat ymlFormat(){
        return new YmlFormat();
    }

    public static IPersonService ymlPersonService(File file){
        return new YmlPersonService(file);
    }
}
