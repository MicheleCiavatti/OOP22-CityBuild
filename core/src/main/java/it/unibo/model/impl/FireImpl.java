package it.unibo.model.impl;

import it.unibo.model.api.Fire;
import it.unibo.model.api.Resource;


public class FireImpl implements Fire{

    private static final int ARBITRARY_VALUE = 5;
    private static final int MIN_COST = 2;
    private CityImpl city;
    private int cityzen;

    public FireImpl(CityImpl city) {
        this.city = city;
    }


    @Override
    public int setIntensity() {
        int minIntensity = 2;
        int randomRiskFactor = (int) (Math.random() * 10);
        return minIntensity + randomRiskFactor;
    }

    @Override
    public int setCost() {
        this.cityzen = city.getCitizens();
        int water = this.getNumResource(Resource.WATER);
        int cost = this.cityzen * ARBITRARY_VALUE - water * ARBITRARY_VALUE + MIN_COST;

        return cost;
    }

    private int getNumResource(Resource resource){
        return city.getPlayerResources().getOrDefault(resource, 0);
    }
    

}
