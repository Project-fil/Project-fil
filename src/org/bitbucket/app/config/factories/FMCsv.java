package org.bitbucket.app.config.factories;

import org.bitbucket.app.dao.IDaoPerson;
import org.bitbucket.app.dao.impl.DaoPersonCsv;
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
