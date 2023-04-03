package it.unibo.model.api;

/* An interface that defines methods for a fire. */
public interface Fire {
    /* Calculates the intensity of the fire.
    @return The intensity of the fire. */
    public int calculateIntensity();
    
    /* Sets the cost of the fire. */
    public void setCost();
}