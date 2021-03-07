package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.CsvFormat;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.repository.locale.DaoPersonCsv;

import java.io.File;

public class FMCsv {

    public static BaseFormat csvFormat(){
        return new CsvFormat();
    }

    public static ICrud daoPersonCsv(File file){
        return new DaoPersonCsv(file);
    }

}
