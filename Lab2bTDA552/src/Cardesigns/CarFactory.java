package Cardesigns;

import java.awt.*;

public class CarFactory {
    public static ICar createVolvo240(Color color){
        ICar returnCar = new Volvo240(color);
        return returnCar;
    }
    public static ICar createSaab95(Color color){
        ICar returnCar = new Saab95(color);
        return returnCar;
    }
    public static ICar createScania(Color color){
        ICar returnCar = new Scania(color);
        return returnCar;
    }
    public static ICar createTowTruck(Color color, int maxCars){
        ICar returnCar = new TowTruck(color, maxCars);
        return returnCar;
    }
}
