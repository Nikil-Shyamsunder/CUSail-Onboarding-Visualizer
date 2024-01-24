import javax.swing.*;
import java.awt.*;

public class WaypointPanel extends JPanel {
    private int xCoordinate;
    private int yCoordinate;

    public WaypointPanel(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        setOpaque(false); // Make panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.RED);
//        int size = 20; // Adjust size if needed
//        // Debugging: Print coordinates to console
//        System.out.println("Drawing 'X' at: " + xCoordinate + ", " + yCoordinate);
//
//        // Draw 'X' centered at (xCoordinate, yCoordinate)
//        g.drawLine(xCoordinate - size, yCoordinate - size, xCoordinate + size, yCoordinate + size);
//        g.drawLine(xCoordinate + size, yCoordinate - size, xCoordinate - size, yCoordinate + size);
        super.paintComponent(g);
        g.setColor(Color.RED); // Set the color to red
        g.fillRect(0, 0, 10, 10); // Fill the oval with the set color
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(40, 40); // Ensure the panel is large enough
    }
}
