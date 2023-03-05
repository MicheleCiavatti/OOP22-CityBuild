package it.unibo.model.api;

import java.util.List;
import java.util.Map;

/**This interface models the city of the {@link it.unibo.model.api.Player}.
 * In the MVC, this represents the Model.*/
public interface City {
    
    public boolean build(final ProductionBuilding building);

    public void demolish(final ProductionBuilding building);

    public boolean upgrade(final ProductionBuilding building);

    public Map<Resource, Integer> getPlayerResources();

    public int getCitizens();

    public List<ProductionBuilding> getBuildings();

    public void doCycle();
}
