package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.repository.locale.IDaoPerson;
import org.bitbucket.app.repository.locale.DaoPersonBin;
import org.bitbucket.app.fomats.BinFormat;

public class FMBin {

    public static BinFormat binFormat(){
        return new BinFormat();
    }

    public static IDaoPerson daoPersonBin(String fileName){
        return new DaoPersonBin(fileName);
    }


}
