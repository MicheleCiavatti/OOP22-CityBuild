package it.unibo.model.impl;

import java.util.Map;
import java.util.Random;

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
    public static final int MIN_INTENSITY = 2;
    private CityImpl city = new CityImpl(new PlayerImpl());
    Player player = new PlayerImpl();
    private int citizen;
    private int cost;

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateIntensity() throws IllegalArgumentException {
        return MIN_INTENSITY + new Random().nextInt(5);
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
     * Returns the cost of the fire.
     *
     * @return The cost of the fire.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Spends the given amount of gold by the player.
     *
     * @param player The player who is spending the gold.
     * @param cost   The amount of gold to be spent.
     */

    public void spendGold(Player player, int cost) {
        player.spendResources(Map.of(Resource.GOLD, cost));
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
     * Performs the action of starting a fire.
     */
    public void performFireAction() {
        this.calculateIntensity();
        this.setCost();
        this.destroyBuildings();
        this.spendGold(player, cost);
    }

}