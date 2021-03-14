package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.CsvFormat;
import org.bitbucket.app.services.IPeopleService;
import org.bitbucket.app.services.locale.PersonCsvService;

import java.io.File;

public class FMCsv {

    public static BaseFormat csvFormat(){
        return new CsvFormat();
    }

    public static IPeopleService daoPersonCsv(File file){
        return new PersonCsvService(file);
    }

}
