package it.unibo.model.impl;

import java.util.Map;
import java.util.Random;

import it.unibo.model.api.Fire;
import it.unibo.model.api.Player;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

public class FireImpl implements Fire {

    private static final int ARBITRARY_VALUE = 5;
    private static final int MIN_COST = 50;
    private static final int MIN_INTENSITY = 2;
    private CityImpl city = new CityImpl(new PlayerImpl());
    Player player =  new PlayerImpl();
    private int citizen;
    private int intensity;
    private int cost;

    @Override
    public int calculateIntensity() throws IllegalArgumentException {
        return MIN_INTENSITY + new Random().nextInt(5);
    }

    private int getIntensity() {
        return intensity;
    }

    @Override
    public void setCost() {
        this.citizen = city.getCitizens();
        int water = this.getNumResource(Resource.WATER);
        int cost = (this.citizen / 2) * (ARBITRARY_VALUE - water / 2) * ARBITRARY_VALUE;
        if (cost < MIN_COST) {
            cost = MIN_COST;
        }
    }

    private int getCost() {
        return cost;
    }

    private void spendGold(int cost) {
        //diminuisce il gold del player. Resource non è mutabile

        
        player.spendResources(Map.of(Resource.GOLD, cost));
        

    }

    private int getNumResource(Resource resource) {
        return city.getPlayerResources().getOrDefault(resource, 0);
    }

    private int numBuildingsDestroyed() {
        int numBuildingsDestroyed = 0;
        for (ProductionBuilding building : city.getBuildings()) {
            if (!building.isUpgradable()) {
                numBuildingsDestroyed++;
            }
        }
        return numBuildingsDestroyed;
    }

    private void destroyBuildings() {
        for (int i = 0; i < this.numBuildingsDestroyed(); i++) {
            city.demolish(city.getBuildings().get(i));
        }
    }

    public void performFireAction() {
        this.calculateIntensity();
        this.setCost();
        this.destroyBuildings();
        this.spendGold(this.getCost());
    }

}
