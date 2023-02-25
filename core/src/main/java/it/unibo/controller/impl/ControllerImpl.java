package it.unibo.controller.impl;

import it.unibo.controller.api.Controller;
import it.unibo.model.api.City;

/**A basic implementation of {@link it.unibo.controller.api.Controller}. */
public class ControllerImpl implements Controller {
    
    private final City city;

    public ControllerImpl(final City city) {
        this.city = city;
    }
}
