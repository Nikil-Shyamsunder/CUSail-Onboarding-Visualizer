import javax.swing.JPanel;
import java.awt.Dimension;

public class CanvasPanel extends JPanel implements Runnable {
    public static final int SPEED = 5; // Speed in pixels per frame
    private Thread canvasPanelThread;
    public static String shipSpritePath = "/Users/nikilshyamsunder/sprite.png";
    private ShipSprite shipSprite;
    private BuoyPanel[] buoyPanels;
    private enum State { MOVING_RIGHT, MOVING_UP, MOVING_LEFT }
    private State currentState;
    private WaypointPanel[] waypoints;

    public CanvasPanel() {
        this.shipSprite = new ShipSprite(shipSpritePath);

        buoyPanels = new BuoyPanel[4];
        for (int i = 0; i < buoyPanels.length; i++) {
            buoyPanels[i] = new BuoyPanel();
            add(buoyPanels[i]);
        }
        updateBuoyPanelBounds();

        setLayout(null); // Using null layout manager
        add(shipSprite);

        updateSpriteBounds();
        waypoints = generateWaypoints();

        currentState = State.MOVING_RIGHT;
        canvasPanelThread = new Thread(this);
        canvasPanelThread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < waypoints.length; i++) {
            moveToWaypoint(waypoints[i]);
            if (i == waypoints.length - 1) { // Stop at the last waypoint
                break;
            }
        }
    }


    private void moveToWaypoint(WaypointPanel waypoint) {
        while (true) {
            int deltaX = waypoint.getX() - shipSprite.getX();
            int deltaY = waypoint.getY() - shipSprite.getY();

            if (Math.abs(deltaX) <= SPEED && Math.abs(deltaY) <= SPEED) {
                // Ship has reached the vicinity of the waypoint
                shipSprite.setLocation(waypoint.getX(), waypoint.getY());
                break;
            }

            if (deltaX != 0) {
                shipSprite.setLocation(shipSprite.getX() + Integer.signum(deltaX) * SPEED, shipSprite.getY());
            }

            if (deltaY != 0) {
                shipSprite.setLocation(shipSprite.getX(), shipSprite.getY() + Integer.signum(deltaY) * SPEED);
            }

            repaint();

            try {
                Thread.sleep(100); // Control frame rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void moveRight() {
        shipSprite.setLocation(shipSprite.getX() + SPEED, shipSprite.getY());
    }

    private void moveUp() {
        shipSprite.setLocation(shipSprite.getX(), shipSprite.getY() - SPEED);
    }

    private void moveLeft() {
        shipSprite.setLocation(shipSprite.getX() - SPEED, shipSprite.getY());
    }

    private void updateBuoyPanelBounds() {
        buoyPanels[0].setBounds(75, 50, buoyPanels[0].getPreferredSize().width, buoyPanels[0].getPreferredSize().height);
        buoyPanels[1].setBounds(75, 200, buoyPanels[1].getPreferredSize().width, buoyPanels[1].getPreferredSize().height);
        buoyPanels[2].setBounds(300, 125, buoyPanels[2].getPreferredSize().width, buoyPanels[2].getPreferredSize().height);
    }

    private void updateSpriteBounds() {
        int spriteWidth = shipSprite.getPreferredSize().width;
        int spriteHeight = shipSprite.getPreferredSize().height;
        int x = (buoyPanels[0].getX() + buoyPanels[1].getX()) / 2;
        int y = (buoyPanels[0].getY() + buoyPanels[1].getY()) / 2;
        shipSprite.setBounds(x, y+50, spriteWidth, spriteHeight);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        updateSpriteBounds(); // Update bounds when the layout is done
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    public WaypointPanel[] generateWaypoints() {
        WaypointPanel[] waypoints = new WaypointPanel[4];


        // First Waypoint
        int x1 = buoyPanels[1].getX();
        int y1 = (buoyPanels[1].getY() + buoyPanels[2].getY()) / 2;
        waypoints[0] = new WaypointPanel(x1, y1);
        int width = waypoints[0].getPreferredSize().width; int height = waypoints[0].getPreferredSize().height;
        waypoints[0].setBounds(x1, y1, width, height);
        setComponentZOrder(waypoints[0], 1);
        add(waypoints[0]);

        // Second Waypoint
        int x2 = buoyPanels[2].getX() + shipSprite.getWidth();
        int y2 = buoyPanels[2].getY() + shipSprite.getHeight();
        waypoints[1] = new WaypointPanel(x2, y2);
        waypoints[1].setBounds(x2, y2, width, height);
        setComponentZOrder(waypoints[1], 1);
        add(waypoints[1]);

        // Third Waypoint
        int x3 = buoyPanels[2].getX() + shipSprite.getWidth();
        int y3 = buoyPanels[2].getY() - shipSprite.getHeight();
        waypoints[2] = new WaypointPanel(x3, y3);
        waypoints[2].setBounds(x3, y3, width, height);
        setComponentZOrder(waypoints[2], 1);
        add(waypoints[2]);

        // Fourth Waypoint
        int x4 = buoyPanels[1].getX();
        int y4 = (buoyPanels[0].getY() + buoyPanels[2].getY()) / 2;
        waypoints[3] = new WaypointPanel(x4, y4);
        waypoints[3].setBounds(x4, y4, width, height);
        setComponentZOrder(waypoints[3], 1);
        add(waypoints[3]);

        return waypoints;
    }
}
