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

/**An implementation of {@link it.unibo.controller.api.EconomyFileReader}.*/
public class EconomyFileReaderImpl implements EconomyFileReader {

    private static final String REVENUE_IN_FILE = "revenue";
    private static final String CONSTRUCTION_IN_FILE = "construction";
    private static final String UPGRADE_IN_FILE = "upgrade";
    private static final String SIMPLE_BUILDING_DIR = "simple_buildings";
    private static final String ADVANCED_BUILDING_DIR = "advanced_buildings";
    private static final String FILE_EXTENSION = ".yml";
    public static final String PATH_RES = System.getProperty("user.dir").replace("core", "")
        + "assets" + File.separator + "buildings" + File.separator;

    private EconomyTables data;

    /**{@inheritDoc} */
    @Override
    public List<Map<Resource, Integer>> getSimpleEconomyTables(final Resource r) {
        return this.computeTables(this.getPath(true, r));
    }

    /**{@inheritDoc} */
    @Override
    public List<Map<Resource, Integer>> getAdvancedEconomyTables(Resource r) {
        return this.computeTables(this.getPath(false, r));
    }

    private List<Map<Resource, Integer>> computeTables(final String path) {
        try (InputStream input = new FileInputStream(path)) {
            Yaml yaml = new Yaml(new Constructor(EconomyTables.class));
            data = yaml.load(input);
         } catch (IOException e) {
             e.printStackTrace();
         }
         return List.of(this.getTable(REVENUE_IN_FILE), this.getTable(CONSTRUCTION_IN_FILE), this.getTable(UPGRADE_IN_FILE));
    }

    private String getPath(final boolean isSimpleBuilding, final Resource r) {
        return isSimpleBuilding 
            ? PATH_RES + SIMPLE_BUILDING_DIR 
                + File.separator + r.getSimpleBuilding().toLowerCase() + FILE_EXTENSION
            : PATH_RES + ADVANCED_BUILDING_DIR
                + File.separator + r.getAdvancedBuilding().toLowerCase() + FILE_EXTENSION;
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
