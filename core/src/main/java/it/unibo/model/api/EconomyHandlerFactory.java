package it.unibo.model.api;

/**Factory interface for creating the handler of the economy*/
public interface EconomyHandlerFactory {
    /**
     * 
     * @return EconomyHandler which is responsible to provide all the cost of the building and its revenue
     */
    public EconomyHandler createEconomyHandler();
}
