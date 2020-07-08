package View;

import Controller.SubController;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GridView extends JPanel implements Scrollable {

    private JPanel grid;
    private JLabel label;

    public GridView(){
        setLayout(new GridBagLayout());

        label = new JLabel("Test");
        label.setFont(new Font("SegoeUI", Font.PLAIN, 50));

        grid = new JPanel();
        grid.setLayout(new GridLayout(0, 3));

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 10, 0);
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.NORTH;

        add(label, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;

        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;


        add(grid, constraints);
    }

    public void updateGrid(Game[] games, String title, SubController c){
        label.setText(title);
        grid.removeAll();
        int i = 0;
        JButton j;
        for (Game g : games){
            j = new JButton(g.getName());
            BufferedImage pic = g.loadImage();
            if (pic != null){
                Dimension d = View.getScaledDimension(new Dimension(pic.getWidth(), pic.getHeight()), new Dimension(100, 100));
                ImageIcon image = new ImageIcon(pic.getScaledInstance(-1, 100, Image.SCALE_FAST));
                j.setIcon(image);
            }
            j.setHorizontalAlignment(SwingConstants.LEFT);
            j.addActionListener(c);
            j.setActionCommand(Integer.toString(i));
            grid.add(j);
            i++;
        }
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 50;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
