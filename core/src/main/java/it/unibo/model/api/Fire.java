package it.unibo.model.api;

/** An interface that defines methods for a fire. */
public interface Fire {
    /**
     * Calculates the intensity of the fire.
     *
     * @return The intensity of the fire.
     * 
     * @throws IllegalArgumentException if the calculation fails.
     */
    //public int calculateIntensity() throws IllegalArgumentException;

    /**
     * Sets the cost of the fire based on the number of citizens and water resources
     * in the city.
     */
    public void setCost();

    /**
     * Spends the given amount of gold by the player.
     *
     * @param player The player who is spending the gold.
     * @param cost   The amount of gold to be spent.
     */
    public void spendGold(Player player, int cost);

    /**
     * Performs the fire action.
     */

    public void performFireAction();

    /**
     * Returns the cost of the fire.
     *
     * @return The cost of the fire.
     */
    public int getCost();

    
}