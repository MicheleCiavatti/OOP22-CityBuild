package it.unibo.controller;

import it.unibo.model.api.Resource;

public interface EconomyFileReader {
    
    public List<Map<String, Integer>> getEconomyTables(final Resource r);
}
