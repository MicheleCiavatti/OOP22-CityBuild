package it.unibo.model.impl;

import java.util.Random;

import it.unibo.model.api.Fire;
import it.unibo.model.api.Resource;


public class FireImpl implements Fire{

    private static final int ARBITRARY_VALUE = 5;
    private static final int MIN_COST = 50;
    private static final int MIN_INTENSITY = 2;
    private CityImpl city;
    private int cityzen;
    private int intensity;

    public FireImpl(CityImpl city) {
        this.city = city;
    }


    @Override
    public int setIntensity() {

        Random random = new Random();
        int randomRiskFactor = random.nextInt(this.getNumOfBuildings());
        intensity = MIN_INTENSITY + randomRiskFactor;
        return intensity;
    }

    private int getIntensity() {
        return intensity;
    }

    @Override
    public int setCost() {
        this.cityzen = city.getCitizens();
        int water = this.getNumResource(Resource.WATER);
        int cost = (this.cityzen/2) * (ARBITRARY_VALUE - water/2) * ARBITRARY_VALUE;
        if(cost < MIN_COST) {
            cost = MIN_COST;
        }
        return cost;
    }

    private int getNumResource(Resource resource){
        return city.getPlayerResources().getOrDefault(resource, 0);
    }

    private int numBuildingsDestroyed(){
        return this.getIntensity() / 2;
    }

    private int getNumOfBuildings(){
        return city.getBuildings().size();
    }
    

}
