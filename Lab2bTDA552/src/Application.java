import Control.CarController;
import Model.CarModel;
import View.CarView;

public class Application {

    public static void main(String[] args) {
        // Instance of this class
        CarModel model = new CarModel();

        CarView view = new CarView("CarSim 1.0", model.getWidth(), model.getHeight(), model);
        CarController cc = new CarController(view, model);
        model.addListener(view.getDrawPanel());
        model.addListener(view.getVelocityPanel());
        // Start the timer
        model.startTimer();
    }
}
