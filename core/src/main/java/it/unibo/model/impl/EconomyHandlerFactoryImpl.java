package it.unibo.model.impl;

import it.unibo.model.api.EconomyHandler;
import it.unibo.model.api.EconomyHandlerFactory;

/**This class is the implementation of the class {@link it.unibo.model.api.EconomyHandlerFactory}
 * 
 */
public class EconomyHandlerFactoryImpl implements EconomyHandlerFactory {

    @Override
    public EconomyHandler createEconomyHandler() {
        return new EconomyHandlerImpl();
    }
    
}
