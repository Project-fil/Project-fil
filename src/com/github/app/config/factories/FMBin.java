package com.github.app.config.factories;

import com.github.app.dao.IDaoPerson;
import com.github.app.dao.impl.DaoPersonBin;
import com.github.app.fomats.BinFormat;

public class FMBin {

    public static BinFormat binFormat(){
        return new BinFormat();
    }

    public static IDaoPerson daoPersonBin(String fileName){
        return new DaoPersonBin(fileName);
    }


}
