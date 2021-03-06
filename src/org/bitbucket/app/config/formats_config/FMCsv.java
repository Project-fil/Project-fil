package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.CsvFormat;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.repository.locale.DaoPersonCsv;

public class FMCsv {

    public static BaseFormat csvFormat(){
        return new CsvFormat();
    }

    public static ICrud daoPersonCsv(String fileName){
        return new DaoPersonCsv(fileName);
    }

}
