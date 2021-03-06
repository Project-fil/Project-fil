package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.repository.locale.IDaoPerson;
import org.bitbucket.app.repository.locale.DaoPersonCsv;
import org.bitbucket.app.fomats.BaseFormat;
import org.bitbucket.app.fomats.impl.CsvFormat;

public class FMCsv {

    public static BaseFormat csvFormat(){
        return new CsvFormat();
    }

    public static IDaoPerson daoPersonCsv(String fileName){
        return new DaoPersonCsv(fileName);
    }

}
