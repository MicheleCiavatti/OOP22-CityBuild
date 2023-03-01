package it.unibo.controller;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.EconomyHandlerFactoryImpl;

public class TestEconomyHandler {
    private  EconomyHandler economyHandler= new EconomyHandlerFactoryImpl().createEconomyHandler();
    
     public void getSimpleCostTable(){
         Map<Resource,Map<Resource,Integer>> mapSimpleConstruction = new HashMap<>();
         
         mapSimpleConstruction.put(Resource.CITIZEN, economyHandler.getSimpleCostTable(Resource.CITIZEN));
         mapSimpleConstruction.put(Resource.WOOD,economyHandler.getSimpleCostTable(Resource.WOOD));
         mapSimpleConstruction.put(Resource.METAL,economyHandler.getSimpleCostTable(Resource.METAL));
         mapSimpleConstruction.put(Resource.GOLD, economyHandler.getSimpleCostTable(Resource.GOLD));
         mapSimpleConstruction.put(Resource.ENERGY, economyHandler.getSimpleCostTable(Resource.ENERGY));
         mapSimpleConstruction.put(Resource.WATER,economyHandler.getSimpleCostTable(Resource.WATER));
         
         for(Entry<Resource, Map<Resource, Integer>> t: mapSimpleConstruction.entrySet()){
             System.out.println(t.getKey());
             System.out.println(t.getValue());
            }
        }   
        
        public void getSimpleRevenueTable(){
            Map<Resource,Map<Resource,Integer>> mapSimpleRevenue = new HashMap<>();
            
            mapSimpleRevenue.put(Resource.CITIZEN, economyHandler.getSimpleRevenueTable(Resource.CITIZEN));
            mapSimpleRevenue.put(Resource.WOOD, economyHandler.getSimpleRevenueTable(Resource.WOOD));
            mapSimpleRevenue.put(Resource.METAL, economyHandler.getSimpleRevenueTable(Resource.METAL));
            mapSimpleRevenue.put(Resource.GOLD, economyHandler.getSimpleRevenueTable(Resource.GOLD));
            mapSimpleRevenue.put(Resource.ENERGY, economyHandler.getSimpleRevenueTable(Resource.ENERGY));
            mapSimpleRevenue.put(Resource.WATER, economyHandler.getSimpleRevenueTable(Resource.WATER));
            
            for (Entry<Resource,Map<Resource,Integer>> t : mapSimpleRevenue.entrySet()) {
                System.out.println(t.getKey());
                System.out.println(t.getValue());
            }
        }
        
        public void getSimpleUpgradeTable(){
            Map<Resource, Map<Resource,Integer>> mapSimpleUpgrade = new HashMap<>();
            
            mapSimpleUpgrade.put(Resource.CITIZEN, economyHandler.getSimpleUpgradeTable(Resource.CITIZEN));
            mapSimpleUpgrade.put(Resource.WOOD, economyHandler.getSimpleUpgradeTable(Resource.WOOD));
            mapSimpleUpgrade.put(Resource.METAL, economyHandler.getSimpleUpgradeTable(Resource.METAL));
            mapSimpleUpgrade.put(Resource.GOLD, economyHandler.getSimpleUpgradeTable(Resource.GOLD));
            mapSimpleUpgrade.put(Resource.ENERGY, economyHandler.getSimpleUpgradeTable(Resource.ENERGY));
            mapSimpleUpgrade.put(Resource.WATER, economyHandler.getSimpleUpgradeTable(Resource.WATER));
            
            for (Entry<Resource,Map<Resource,Integer>> t : mapSimpleUpgrade.entrySet()) {
                System.out.println(t.getKey());
                System.out.println(t.getValue());
            }
        }
        
        public void getAdvancedCostTable(){
            Map<Resource, Map<Resource,Integer>> mapAdvancedCost = new HashMap<>();
            
            mapAdvancedCost.put(Resource.WOOD, economyHandler.getAdvancedCostTable(Resource.WOOD));
            mapAdvancedCost.put(Resource.WOOD, economyHandler.getAdvancedCostTable(Resource.WOOD));
            mapAdvancedCost.put(Resource.METAL, economyHandler.getAdvancedCostTable(Resource.METAL));
            mapAdvancedCost.put(Resource.GOLD, economyHandler.getAdvancedCostTable(Resource.GOLD));
            mapAdvancedCost.put(Resource.ENERGY, economyHandler.getAdvancedCostTable(Resource.ENERGY));
            mapAdvancedCost.put(Resource.WATER, economyHandler.getAdvancedCostTable(Resource.WATER));
            
            
            for (Entry<Resource,Map<Resource,Integer>> t : mapAdvancedCost.entrySet()) {
                System.out.println(t.getKey());
                System.out.println(t.getValue());
            }
        }
        
        public void getAdvancedUpgrade(){
            Map<Resource, Map<Resource,Integer>> mapAdvancedUpgrade = new HashMap<>();
            
            mapAdvancedUpgrade.put(Resource.CITIZEN, economyHandler.getAdvancedUpgradeTable(Resource.CITIZEN));
            mapAdvancedUpgrade.put(Resource.WOOD, economyHandler.getAdvancedUpgradeTable(Resource.WOOD));
            mapAdvancedUpgrade.put(Resource.METAL, economyHandler.getAdvancedUpgradeTable(Resource.METAL));
            mapAdvancedUpgrade.put(Resource.GOLD, economyHandler.getAdvancedUpgradeTable(Resource.GOLD));
            mapAdvancedUpgrade.put(Resource.ENERGY, economyHandler.getAdvancedUpgradeTable(Resource.ENERGY));
            mapAdvancedUpgrade.put(Resource.WATER, economyHandler.getAdvancedUpgradeTable(Resource.WATER));
            
        for(Entry<Resource, Map<Resource, Integer>> t: mapAdvancedUpgrade.entrySet()){
            System.out.println(t.getKey());
            System.out.println(t.getValue());
        }
    }

    public void getAdvancedRevenue(){
        Map<Resource, Map<Resource, Integer>> mapAdvancedRevenue  = new HashMap<>();

        mapAdvancedRevenue.put(Resource.CITIZEN, economyHandler.getAdvancedRevenueTable(Resource.CITIZEN));
        mapAdvancedRevenue.put(Resource.WOOD, economyHandler.getAdvancedRevenueTable(Resource.WOOD));
        mapAdvancedRevenue.put(Resource.METAL, economyHandler.getAdvancedRevenueTable(Resource.METAL));
        mapAdvancedRevenue.put(Resource.GOLD, economyHandler.getAdvancedRevenueTable(Resource.GOLD));
        mapAdvancedRevenue.put(Resource.ENERGY, economyHandler.getAdvancedRevenueTable(Resource.ENERGY));
        mapAdvancedRevenue.put(Resource.WATER, economyHandler.getAdvancedRevenueTable(Resource.WATER));

        for(Entry<Resource, Map<Resource,Integer>> t: mapAdvancedRevenue.entrySet()){
            System.out.println(t.getKey());
            System.out.println(t.getValue());
        }
    }

    @Test
    public void testHouse(){
        
         System.out.println("Simple Cost Table");
         getSimpleCostTable();
         System.out.println("----");
         
         System.out.println("Simple Revenue Table");
         getSimpleRevenueTable();
         System.out.println("----");
         
         System.out.println("Simple Upgrade Table");
         getSimpleUpgradeTable();
         System.out.println("----");
         
         System.out.println("Advanced Cost Table");
         getAdvancedCostTable();
         System.out.println("----");

         System.out.println("Advanced Upgrade Table");
         getAdvancedUpgrade();
         System.out.println("----");

         System.out.println("Advanced Revenue Table");
         getAdvancedRevenue();
         System.out.println("----");
    }
}
