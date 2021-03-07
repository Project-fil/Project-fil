package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BinFormat;
import org.bitbucket.app.repository.ICrud;
import org.bitbucket.app.repository.locale.DaoPersonBin;

import java.io.File;

public class FMBin {

    public static BinFormat binFormat(){
        return new BinFormat();
    }

    public static ICrud daoPersonBin(File file){
        return new DaoPersonBin(file);
    }


}
