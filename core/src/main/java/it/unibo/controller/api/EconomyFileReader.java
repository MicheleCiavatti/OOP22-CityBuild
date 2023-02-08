package it.unibo.controller.api;

import java.util.List;
import java.util.Map;
import it.unibo.model.api.Resource;

/**This interface is used to read YAML files containing the economy tables (revenue, construction costs and upgrade costs) of buildings. */
public interface EconomyFileReader {
    
    /**Returns a list containing the tables of a simple building.
     * @return a list with always 3 {@link java.util.Map}: the first is the revenue table, 
     * the second is the construction table and the third is the upgrade table
     * @param r used to specify which simple building
     */
    public List<Map<Resource, Integer>> getSimpleEconomyTables(final Resource r);
}
