package it.unibo.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.yaml.snakeyaml.Yaml;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.model.api.Resource;

public class EconomyFileReaderImpl implements EconomyFileReader {

    private static final String REVENUE_IN_FILE = "revenue";
    private static final String CONSTRUCTION_IN_FILE = "construction";
    private static final String UPGRADE_IN_FILE = "upgrade";
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
    public List<Map<Resource, Integer>> getSimpleEconomyTables(Resource r) {
        var path = PATH_RES + File.separator + "simple_buildings" 
            + File.separator + r.getSimpleBuilding().toLowerCase();
        Map<String, Map<String, Integer>> data;
        try (InputStream input = new FileInputStream(path)) {
           Yaml yaml = new Yaml();
           data = yaml.load(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Resource, Integer> revenue = new HashMap<>();
        data.get(REVENUE_IN_FILE).entrySet().forEach(entry -> {

        });
        return null;
    }

    private Resource fromString(final String s) {
        return Arrays.stream(Resource.values())
                .filter(val -> val.getName().equalsIgnoreCase(s))
                .findFirst()
                .get();
    }
    
}
