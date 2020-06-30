package View;

import Controller.Controller;
import Controller.GameController;
import Controller.SubController;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GridView extends JPanel {

    private JScrollPane scrollPane;
    private JPanel grid;
    public GridView(){
        scrollPane = new JScrollPane();
        grid = new JPanel();
        grid.setLayout(new GridLayout());

        scrollPane.add(grid);
        add(scrollPane);
    }

    public void updateGrid(Game[] games, SubController c){
        removeAll();
        int i = 0;
        JButton j;
        for (Game g : games){
            j = new JButton(g.getName());
            BufferedImage pic = g.loadImage();
            if (pic != null){
                Dimension d = View.getScaledDimension(new Dimension(pic.getWidth(), pic.getHeight()), new Dimension(100, 100));
                ImageIcon image = new ImageIcon(pic.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH));
                j.setIcon(image);
            }
            j.addActionListener(c);
            j.setActionCommand(Integer.toString(i));
            grid.add(j);
            i++;
        }
    }
}
