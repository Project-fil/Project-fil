package org.bitbucket.app.config;

import org.bitbucket.app.exceptions.WrongPathException;
import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.utils.FileUtils;

import java.io.File;

public class FileServiceChooser {

    public static IPersonService chooseService(File file){
        switch (FileUtils.getExtension(file)){
            case "bin":
                return ServiceConfig.binPersonService(file);
            case "csv":
                return ServiceConfig.csvPersonService(file);
            case "json":
                return ServiceConfig.jsonPersonService(file);
            case "xml":
                return ServiceConfig.xmlPersonService(file);
            case "yml":
                return ServiceConfig.ymlPersonService(file);
        }
        throw new WrongPathException("Extension of a file is not supported.");
    }

}
