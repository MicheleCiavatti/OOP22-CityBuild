package it.unibo.model.impl;

import java.util.Map;

import it.unibo.controller.api.Controller;
import it.unibo.controller.impl.ControllerImpl;
import it.unibo.model.api.City;
import it.unibo.model.api.Fire;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;

/**
 * 
 * A class representing a Fire object that implements the Fire interface.
 * 
 */
public class FireImpl implements Fire {

    public static final int ARBITRARY_VALUE = 5;
    private static final int MIN_COST = 50;
    public static final int MIN_INTENSITY = 99;
    private City city;
    Player player;
    private int citizen;
    private int cost;
    Controller controller = new ControllerImpl(city);


    public FireImpl(City city) {
        this.city = city;
        this.player = city.getPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCost() {
        this.citizen = city.getCitizens();
        int water = this.getNumResource(Resource.WATER);
        int cost = calculateCost(citizen, water);
        this.cost = cost < MIN_COST ? MIN_COST : cost;
    }

    private int calculateCost(int citizen, int water) {
        return (citizen / 2) * (ARBITRARY_VALUE - water / 2) * ARBITRARY_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    public int getCost() {
        return cost;
    }

    /**
     * {@inheritDoc}
     */
    public void spendGold() {
        System.out.println("COSTO" + cost);
        System.out.println("BEFORE" + player.getResource(Resource.GOLD));
        player.spendResources(Map.of(Resource.GOLD, cost));
        System.out.println("AFTER" + player.getResource(Resource.GOLD));
    }

    private int getNumResource(Resource resource) {
        return city.getPlayerResources().getOrDefault(resource, 0);
    }

    private void destroyBuildings() {

        city.getBuildings().stream()
                .filter(building -> !building.isUpgradable())
                .forEach(building -> city.demolish(building));
    }


    /**
     * {@inheritDoc}
     */
    public void performFireAction() {
        this.setCost();
        this.spendGold();
        this.destroyBuildings();
    }

}