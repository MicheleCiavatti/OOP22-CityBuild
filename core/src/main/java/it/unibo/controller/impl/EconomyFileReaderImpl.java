package it.unibo.controller.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

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
    public List<Map<String, Integer>> getSimpleEconomyTables(Resource r) {
        var path = PATH_RES + File.separator + "simple_buildings" 
            + File.separator + r.getSimpleBuilding().toLowerCase();
        Yaml y = new Yaml();

        return null;
    }
    
}
