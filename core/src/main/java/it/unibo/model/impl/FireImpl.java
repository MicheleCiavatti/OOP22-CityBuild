package it.unibo.model.impl;

import java.util.Random;

import it.unibo.model.api.Fire;
import it.unibo.model.api.Resource;


public class FireImpl implements Fire{

    private static final int ARBITRARY_VALUE = 5;
    private static final int MIN_COST = 50;
    private static final int MIN_INTENSITY = 2;
    private CityImpl city = new CityImpl( new PlayerImpl());
    private int cityzen;
    private int intensity;

    


    @Override
    public int setIntensity() {

        Random random = new Random();
        int randomRiskFactor = random.nextInt(5);
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

    private void decreaseResource(Resource resource, int amount){
        city.getPlayerResources().put(resource, city.getPlayerResources().get(resource) - amount);
    }

    private int getNumResource(Resource resource){
        return city.getPlayerResources().getOrDefault(resource, 0);
    }

    private int numBuildingsDestroyed(){
        //se non ci sono abbastanza risorse per pagare il costo del fuoco, distruggo tutti i building oppure ho meno edifici di quelli che posso distruggere
        if(this.setCost() > this.getNumResource(Resource.GOLD) || city.getBuildings().size() < this.getIntensity() ){
            return city.getBuildings().size();
        }
        //distruggo solo i building che non sono upgradabili
        else{
            int numBuildingsDestroyed = 0;
            for(int i = 0; i < city.getBuildings().size(); i++){
                if(!city.getBuildings().get(i).isUpgradable()){
                    numBuildingsDestroyed++;
                }
            }
            return numBuildingsDestroyed;
        }
    }
    
    private void destroyBuildings(){

        for(int i = 0; i < this.numBuildingsDestroyed(); i++){
            city.demolish(city.getBuildings().get(i));
        }
    }

    public void update(){
        this.setIntensity();
        //this.decreaseResource(Resource.GOLD, this.setCost());
        this.destroyBuildings();
    }



}
