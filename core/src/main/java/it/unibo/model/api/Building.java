package it.unibo.model.api;

import java.util.List;

public interface Building {

    public int getResource();

    public List<Resource> getCostUpgrade();
    
    public void upgrade(final List<Resource> resourcesForUpgrade);

}