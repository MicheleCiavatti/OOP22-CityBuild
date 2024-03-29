package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;
import org.junit.jupiter.api.Test;

import it.unibo.model.api.Fire;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.CityImpl;
import it.unibo.model.impl.PlayerImpl;



import it.unibo.model.impl.FireImpl;

public class TestFireImpl {
   

    Player player = new PlayerImpl();

    @Test
    public void testSetCost() {
        CityImpl city = new CityImpl( player);
        city.addCitizens(50);
        player.addResources(Map.of(Resource.WATER, 10));
        Resource gold = Resource.GOLD;
        player.addResources(Map.of(gold, 5000));
        FireImpl fire = new FireImpl(city);
        fire.performFireAction();
        assertEquals(fire.getCost(), 50);
    }

    @Test
    public void testSpendGold() {
        CityImpl city = new CityImpl( player);
        city.addCitizens(50);
        player.addResources(Map.of(Resource.WATER, 10));
        Fire fire = new FireImpl(city);
        fire.setCost();
        assertEquals(50, fire.getCost());
        fire.performFireAction();
    }





}



