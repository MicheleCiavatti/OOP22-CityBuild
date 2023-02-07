package it.unibo.controller.api;

import java.util.List;
import java.util.Map;

import it.unibo.model.api.Resource;

public interface EconomyFileReader {
    
    public List<Map<Resource, Integer>> getSimpleEconomyTables(final Resource r);
}
