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
import org.yaml.snakeyaml.constructor.Constructor;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.model.api.Resource;

/**An implementation of {@link it.unibo.controller.api.EconomyFileReader}*/
public class EconomyFileReaderImpl implements EconomyFileReader {

    private static final String REVENUE_IN_FILE = "revenue";
    private static final String CONSTRUCTION_IN_FILE = "construction";
    private static final String UPGRADE_IN_FILE = "upgrade";
    private static final String PATH_RES = System.getProperty("user.dir")
        + File.separator
        + "src"
        + File.separator
        + "main"
        + File.separator
        + "resources";

    private EconomyTables data;

    /**{@inheritDoc} */
    @Override
    public List<Map<Resource, Integer>> getSimpleEconomyTables(Resource r) {
        var path = PATH_RES + File.separator + "simple_buildings" 
            + File.separator + r.getSimpleBuilding().toLowerCase() + ".yml";
        try (InputStream input = new FileInputStream(path)) {
           Yaml yaml = new Yaml(new Constructor(EconomyTables.class));
           data = yaml.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of(this.getTable(REVENUE_IN_FILE), this.getTable(CONSTRUCTION_IN_FILE), this.getTable(UPGRADE_IN_FILE));
    }

    private Map<Resource, Integer> getTable(final String key) {
        Map<Resource, Integer> table = new HashMap<>();
        Map<String, Integer> oldTable = switch(key) {
            case REVENUE_IN_FILE -> data.getRevenue();
            case CONSTRUCTION_IN_FILE -> data.getConstruction();
            case UPGRADE_IN_FILE -> data.getUpgrade();
            default -> throw new IllegalStateException();
        };
        oldTable.entrySet().forEach(entry -> table.put(this.fromString(entry.getKey()), entry.getValue()));
        return table;
    }

    private Resource fromString(final String s) {
        return Arrays.stream(Resource.values())
                .filter(val -> val.getName().equalsIgnoreCase(s))
                .findFirst()
                .orElseThrow();
    }
}
