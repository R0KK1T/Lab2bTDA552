package Control;

import Cardesigns.ICar;
import Cardesigns.Saab95;
import Cardesigns.Scania;
import Model.CarModel;
import View.CarView;


/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    private CarView view;
    private CarModel model;

    public CarController(CarView view, CarModel model){
        this.view = view;
        this.model = model;
        initializeButtons();
    }

    public void initializeButtons(){
        view.getGasButton().addActionListener(e -> gas(view.getGasAmount()));

        view.getBrakeButton().addActionListener(e -> brake(view.getGasAmount()));

        view.getTurboOnButton().addActionListener(e -> turboOn());

        view.getTurboOffButton().addActionListener(e -> turboOff());

        view.getLiftBedButton().addActionListener(e -> rampUp());

        view.getLowerBedButton().addActionListener(e -> rampDown());

        view.getStartButton().addActionListener(e -> startEngine());

        view.getStopButton().addActionListener(e -> stopEngine());

        view.getAddButton().addActionListener(e -> addCar());

        view.getRemoveButton().addActionListener(e -> removeCar());
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (ICar car : model.getCars()) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (ICar car : model.getCars()) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (ICar car : model.getCars()) {
            if(car.getClass() == Saab95.class){
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void turboOff() {
        for (ICar car : model.getCars()) {
            if(car.getClass() == Saab95.class){
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void rampUp() {
        for (ICar car : model.getCars()) {
            if(car.getClass() == Scania.class){
                ((Scania) car).increaseAngle(1000);
            }
        }
    }

    void rampDown() {
        for (ICar car : model.getCars()) {
            if(car.getClass() == Scania.class){
                ((Scania) car).decreaseAngle(1000);
            }
        }
    }

    void startEngine() {
        for (ICar car : model.getCars()) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (ICar car : model.getCars()) {
            car.stopEngine();
        }
    }

    void addCar(){
        if (model.getCars().size() < 10){
            ICar car = model.addCar();
            view.getDrawPanel().addToHashMap(car);
            model.notifyListeners();
        }
    }

    void removeCar(){
        if (model.getCars().size() > 0){
            ICar car = model.removeCar();
            view.getDrawPanel().removeFromHashMap(car);
            model.notifyListeners();
        }
    }


}
