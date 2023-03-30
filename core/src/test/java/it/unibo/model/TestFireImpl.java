package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Map;
import org.junit.jupiter.api.Test;
import it.unibo.model.api.Player;
import it.unibo.model.api.Resource;
import it.unibo.model.impl.CityImpl;
import it.unibo.model.impl.PlayerImpl;



import it.unibo.model.impl.FireImpl;

public class TestFireImpl {
   

    Player player = new PlayerImpl();
    

    @Test
    public void testCalculateIntensity() {
        FireImpl fire = new FireImpl();
        int intensity = fire.calculateIntensity();
        assertTrue(intensity >= 0 && intensity <= 10);
        
    }

    @Test
    public void testSetCost() {
        CityImpl city = new CityImpl( player);
        city.addCitizens(50);
        player.addResources(Map.of(Resource.WATER, 10));
        Resource gold = Resource.GOLD;
        player.addResources(Map.of(gold, 5000));
        FireImpl fire = new FireImpl();
        fire.performFireAction();

        fire.setCost();
        assertEquals(fire.getCost(), 50);
    }

    @Test
    public void testSpendGold() {
        CityImpl city = new CityImpl( player);
        city.addCitizens(50);
        player.addResources(Map.of(Resource.WATER, 10));
        FireImpl fire = new FireImpl();
        fire.performFireAction();

        fire.setCost();
        //int expectedCost = (25 / 2) * (FireImpl.ARBITRARY_VALUE - 5) * FireImpl.ARBITRARY_VALUE;
        assertTrue(50 <= fire.getCost());
        fire.spendGold(50);
        assertEquals(100 - fire.getCost(), player.getAllResources().get(Resource.GOLD));
    }
}



