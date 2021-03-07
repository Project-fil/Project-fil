package org.bitbucket.app.config;

import org.bitbucket.app.config.formats_config.*;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.utils.FileUtils;
import org.bitbucket.app.utils.exceptions.WrongPathException;

import java.io.File;

public class FDaoPerson {

    public static ICrud chooseDao(File file){
        switch (FileUtils.getExtension(file)){
            case "bin":
                return FMBin.daoPersonBin(file);
            case "csv":
                return FMCsv.daoPersonCsv(file);
            case "json":
                return FMJson.daoPersonJson(file);
            case "xml":
                return FMXml.daoPersonXml(file);
            case "yml":
                return FMYml.daoPersonYml(file);
        }
        throw new WrongPathException("Extension of a file is not supported.");
    }
}
