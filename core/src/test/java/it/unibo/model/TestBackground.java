package it.unibo.model;

import java.util.Map;

import org.junit.jupiter.api.Test;

import it.unibo.controller.impl.BackgroundTaskImpl;
import it.unibo.model.api.Resource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBackground {

    private static final Map<Resource, Integer> MANY_RESOURCES = Map.of(
        Resource.CITIZEN, 1000, Resource.ENERGY, 1000, Resource.GOLD, 1000,
        Resource.METAL, 1000, Resource.WATER, 1000, Resource.WOOD, 1000
    );

    @Test
    public void testGetResources() {
        final BackgroundTaskImpl background = new BackgroundTaskImpl();
        background.getResources();
        assertEquals(background.getResources().size(), background.getResources().size());
    }

}
