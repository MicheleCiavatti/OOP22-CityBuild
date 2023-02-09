package it.unibo.model.api;

import java.util.Map;

/**This interface models the player in the game */
public interface Player {
    
    /**Returns an integer representing the quantity of the specific resource that the player possess.
     * @param r resource requested
     * @return an integer with the quantity associated to the resource
     */
    public int getResource(final Resource r);

    /**Returns all resources available to the player.
     * @return a {@link java.util.Map} containing all resources in possession of the player
     */
    public Map<Resource, Integer> getAllResources();

    /**Removes the specified resources to the player. 
     * The method makes an internal check to verify that the resources we want to spend are available to the player
     * @param toSpend a {@link java.util.Map} describing the resources we want to spend
     * @return true if the player actually spent the resources (the parameter passes the internal check), false otherwise
     */
    public boolean spendResources(final Map<Resource, Integer> toSpend);

    /**Adds the requested resources to the player possessions.
     * @param toAdd a {@link java.util.Map} describing the resources we want to add
     */
    public void addResources(final Map<Resource, Integer> toAdd);
}
