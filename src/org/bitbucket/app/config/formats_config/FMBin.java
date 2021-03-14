package org.bitbucket.app.config.formats_config;

import org.bitbucket.app.fomats.BinFormat;
import org.bitbucket.app.services.IPeopleService;
import org.bitbucket.app.services.locale.PersonBinService;

import java.io.File;

public class FMBin {

    public static BinFormat binFormat(){
        return new BinFormat();
    }

    public static IPeopleService daoPersonBin(File file){
        return new PersonBinService(file);
    }


}
