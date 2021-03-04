package com.github.app.config.factories;

import com.github.app.dao.IDaoPerson;
import com.github.app.dao.impl.DaoPersonCsv;
import com.github.app.fomats.BaseFormat;
import com.github.app.fomats.impl.CsvFormat;

public class FMCsv {

    public static BaseFormat csvFormat(){
        return new CsvFormat();
    }

    public static IDaoPerson daoPersonCsv(String fileName){
        return new DaoPersonCsv(fileName);
    }

}
