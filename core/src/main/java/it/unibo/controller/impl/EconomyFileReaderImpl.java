package it.unibo.controller.impl;

import java.util.List;
import java.util.Map;

import it.unibo.controller.api.EconomyFileReader;
import it.unibo.model.api.Resource;

public class EconomyFileReaderImpl implements EconomyFileReader {

    private static final ClassLoader LOADER = EconomyFileReaderImpl.class.getClassLoader();

    @Override
    public List<Map<String, Integer>> getEconomyTables(Resource r) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
