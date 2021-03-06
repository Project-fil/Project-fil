package org.bitbucket.app.config.factories;

import org.bitbucket.app.dao.IDaoPerson;
import org.bitbucket.app.dao.impl.DaoPersonBin;
import org.bitbucket.app.fomats.BinFormat;

public class FMBin {

    public static BinFormat binFormat(){
        return new BinFormat();
    }

    public static IDaoPerson daoPersonBin(String fileName){
        return new DaoPersonBin(fileName);
    }


}
