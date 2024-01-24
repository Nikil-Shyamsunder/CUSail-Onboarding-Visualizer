import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Payload Challenge Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 400);

        CanvasPanel canvasPanel = new CanvasPanel();
        frame.add(canvasPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
