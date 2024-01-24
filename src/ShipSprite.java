import javax.swing.JPanel;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ShipSprite extends JPanel {
    private Image spriteImage;

    public ShipSprite(String imagePath) {
        try {
            spriteImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, maybe set a default image or log an error
        }
        setOpaque(false); // To ensure the image background is transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (spriteImage != null) {
            g.drawImage(spriteImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        // Providing a preferred size for the Buoy
        return new java.awt.Dimension(35, 35); // Slightly larger to accommodate the circle
    }


}
