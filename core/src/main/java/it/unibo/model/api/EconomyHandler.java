package it.unibo.model.api;
import java.util.Map;

public interface EconomyHandler{

    public Map<Resource,Integer> getSimpleCostTable(final Resource resource);
    public Map<Resource,Integer> getSimpleRevenueTable(final Resource resource);
    public Map<Resource,Integer> getSimpleUpgradeTable(final Resource resource);

}
