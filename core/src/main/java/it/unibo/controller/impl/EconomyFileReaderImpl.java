package it.unibo.controller.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.model.api.Resource;

public class EconomyFileReaderImpl implements EconomyFileReader {

    private static final String PATH_RES = System.getProperty("user.dir")
        + File.separator
        + "core"
        + File.separator
        + "src"
        + File.separator
        + "main"
        + File.separator
        + "resources";

    @Override
    public List<Map<String, Integer>> getEconomyTables(Resource r) {
        
    }
    
}
