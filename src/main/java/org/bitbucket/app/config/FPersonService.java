package org.bitbucket.app.config;

import org.bitbucket.app.services.IPersonService;
import org.bitbucket.app.utils.FileUtils;
import org.bitbucket.app.utils.exceptions.WrongPathException;

import java.io.File;

public class FPersonService {

    public static IPersonService chooseService(File file){
        switch (FileUtils.getExtension(file)){
            case "bin":
                return FormatConfig.binPersonService(file);
            case "csv":
                return FormatConfig.csvPersonService(file);
            case "json":
                return FormatConfig.jsonPersonService(file);
            case "xml":
                return FormatConfig.xmlPersonService(file);
            case "yml":
                return FormatConfig.ymlPersonService(file);
        }
        throw new WrongPathException("Extension of a file is not supported.");
    }
}
