import javax.swing.JPanel;
import java.awt.*;

public class BuoyPanel extends JPanel {
    private int xCoordinate;
    private int yCoordinate;

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.ORANGE); // Set the color to orange
        g.fillOval(0, 0, 30, 30); // Fill the oval with the set color
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        // Providing a preferred size for the Buoy
        return new java.awt.Dimension(40, 40); // Slightly larger to accommodate the circle
    }
}
