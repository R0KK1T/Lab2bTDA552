package View;

import Cardesigns.ICar;
import Model.*;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel implements AnimateListener {

    // Just a single image, TODO: Generalize
    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;

    private CarModel model;

    private HashMap<ICar, BufferedImage> carToImage = new HashMap<>();

    // TODO: Make this general for all cars
    public void addToHashMap(ICar car){
        if (car.getModelName().equals("Volvo240")) {
            carToImage.put(car, volvoImage);
        }
        else if (car.getModelName().equals("Saab95")) {
            carToImage.put(car, saabImage);
        }
        else if (car.getModelName().equals("Scania")) {
            carToImage.put(car, scaniaImage);
        }
    }

    public void removeFromHashMap(ICar car){
        carToImage.remove(car);
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, CarModel model) {
        this.model = model;

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < model.getCars().size(); i++) {
            g.drawImage(carToImage.get(model.getCars().get(i)), (int) model.getCars().get(i).getX(),
                    (int)model.getCars().get(i).getY() + 60*i, null);
        }

    }

    @Override
    public void actOnUpdate() {
        repaint();
    }
}
