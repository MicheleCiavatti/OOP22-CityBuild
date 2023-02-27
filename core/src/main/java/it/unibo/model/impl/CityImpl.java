package it.unibo.model.impl;

import java.util.Map;

import it.unibo.model.api.City;
import it.unibo.model.api.ProductionBuilding;
import it.unibo.model.api.Resource;

/**A basic implementation of {@link it.unibo.model.api.City}. */
public class CityImpl implements City {

    /*Usa Player per tenere traccia delle risorse in possesso al giocatore. Dai al Player delle risorse
     * iniziali di modo che possa costruire i primi edifici.
    */

    /**{@inheritDoc} */
    @Override
    public boolean attemptBuild(ProductionBuilding building) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attemptBuild'");
    }

    /**{@inheritDoc} */
    @Override
    public void removeBuilding(ProductionBuilding building) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeBuilding'");
    }

    /**{@inheritDoc} */
    @Override
    public boolean upgradeBuilding(ProductionBuilding building) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upgradeBuilding'");
    }

    @Override
    public Map<Resource, Integer> getPlayerResources() {
        //TODO
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerResources'");
    }

    /**{@inheritDoc} */
    @Override
    public int getCitizensInTown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCitizensInTown'");
    }

    /*Il metodo doCycle() viene chiamato nella classe grafica (passando per il controller) ogni n secondi.
     * L'idea è che in questo metodo avvengono le modifiche del gioco in seguito agli edifici presenti e 
     * al numero di cittadini attualmente in città.
     * Ad esempio, se nella città ho un depurator e 10 cittadini, allora dovrò aggiornare le risorse del
     * player aggiungendo la revenue del depurator e togliendo le risorse richieste dai 10 cittadini; le
     * revenue degli edifici sono già implementate, ma puoi scegliere le risorse utilizzate da ogni cittadino
     * ad ogni ciclo in questa classe (magari creando una costante Map<Resource, Integer> RESOURCES_FOR_CITIZENS).
     * Inoltre, ad ogni ciclo il numero dei cittadini dovrebbe incrementare se gli è concesso (aka la città può 
     * ospitare altri cittadini, si vede dalla risorsa CITIZEN del player) o diminuire altrimenti (perchè non bastano 
     * le risorse oppure la città non ha capacità sufficiente per tutti i cittadini): nel caso in cui diminuiscano,
     * non possono mai andare in negativo e se diminuiscono per mancanza di capacità, dovrebbero diminuire di un numero
     * pari alla differenza dei cittadini presenti e la capacità della città.
     */

    /**{@inheritDoc} */
    @Override
    public void doCycle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doCycle'");
    }
    
}
