package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class VelocityPanel extends JPanel implements AnimateListener {
    private List<JLabel> labels = new ArrayList<>();
    private CarModel model;

    public VelocityPanel(CarModel model){
        this.model = model;
        this.setLayout(new GridLayout(10,1));
        for (int i = 0; i < 4; i++) {
            labels.add(new JLabel(""));
            this.add(labels.get(i), i);
        }
        this.setPreferredSize(new Dimension(100, 120));
        update();
    }

    private void update(){
        for (int i = 0; i < model.getCars().size(); i++) {
            labels.get(i).setText(model.getCars().get(i).getModelName() + " : " +
                    model.getCars().get(i).getCurrentSpeed());
        }
    }

    private void addLabel(){
        JLabel label = new JLabel();
        labels.add(label);
        this.add(label);
    }

    private void removeLabel(){
        JLabel label = labels.get(labels.size()-1);
        labels.remove(label);
        this.remove(label);
        repaint();
    }

    @Override
    public void actOnUpdate() {
        if (model.getCars().size() > labels.size()){
            addLabel();
        } else if (model.getCars().size() < labels.size()){
            removeLabel();
        }
        update();
    }
}
