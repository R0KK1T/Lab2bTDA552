package Cardesigns;

public interface ICar extends Movable {
    void gas(double amount);
    void brake(double amount);
    void startEngine();
    void stopEngine();
    double getX();
    double getY();
    double getDirection();
    String getModelName();
    double getCurrentSpeed();
}
