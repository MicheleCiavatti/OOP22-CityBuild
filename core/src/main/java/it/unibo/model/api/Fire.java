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
    public int calculateIntensity() throws IllegalArgumentException;

    /**
     * Sets the cost of the fire based on the number of citizens and water resources
     * in the city.
     */
    public void setCost();
}