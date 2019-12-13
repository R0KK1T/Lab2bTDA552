package Model;

import Cardesigns.ICar;
import Cardesigns.CarFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CarModel {
    int width;
    int height;

    private ArrayList<ICar> cars = new ArrayList<>();
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());
    private List<AnimateListener> listeners = new ArrayList<>();

    public CarModel(){
        width = 800;
        height = 600;
    }

    public void notifyListeners(){
        for (AnimateListener l: listeners) {
            l.actOnUpdate();
        }
    }

    public void addListener(AnimateListener l){
        listeners.add(l);
    }

    public void startTimer(){
        timer.start();
    }

    public ICar addCar(){
        ICar car = randomCar();
        cars.add(car);
        return car;
    }

    public ICar removeCar(){
        ICar car = cars.get(cars.size()-1);
        cars.remove(car);
        return car;
    }

    public List<ICar> getCars(){
        return new ArrayList<>(cars); //safe copy
    }

    private ICar randomCar(){
        int range = (3 - 1) + 1;
        int a = (int)(Math.random() * range) + 1;
        if (a == 1){
            return CarFactory.createVolvo240(Color.BLACK);
        } else if (a == 2){
            return CarFactory.createSaab95(Color.BLACK);
        } else {
            return CarFactory.createScania(Color.BLACK);
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight(){
        return height;
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (ICar car : cars) {
                car.move();
                if ((car.getX() >= width - 100 && car.getDirection() == 0) ||
                        (car.getX() <= 0 && car.getDirection() == Math.PI)){
                    //double vel = car.getCurrentSpeed();
                    car.stopEngine();
                    car.turnLeft(Math.PI);
                    car.startEngine();
                }
                notifyListeners();
            }
        }
    }
}
