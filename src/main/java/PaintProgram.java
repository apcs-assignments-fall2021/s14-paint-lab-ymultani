import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintProgram implements ActionListener {
    JFrame frame;
    DrawingPanel dPanel;
    JPanel buttonPanel, colorPanel, pickPanel;
    JButton pencilButton, eraserButton, redButton, greenButton, blueButton, blackButton, clearButton, sprayButton, pickButton;
    JTextField red_field, green_field, blue_field;

    // This is the PaintProgram constructor which sets up the JFrame
    // and all other components and containers
    // ** Code to be edited in Part C **
    public PaintProgram() {
        // Set up JFrame using BorderLayout
        frame = new JFrame("Paint Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);

        // Create buttonPanel and buttons
        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        colorPanel = new JPanel();
        frame.add(colorPanel, BorderLayout.EAST);

        pickPanel = new JPanel();
        frame.add(pickPanel, BorderLayout.NORTH);

        pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(this);
        buttonPanel.add(pencilButton);

        eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(this);
        buttonPanel.add(eraserButton);

        redButton = new JButton("Red");
        blueButton = new JButton("Blue");
        greenButton = new JButton("Green");
        blackButton = new JButton("Black");

        redButton.addActionListener(this);
        blueButton.addActionListener(this);
        greenButton.addActionListener(this);
        blackButton.addActionListener(this);

        colorPanel.add(redButton);
        colorPanel.add(greenButton);
        colorPanel.add(blueButton);
        colorPanel.add(blackButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        sprayButton = new JButton("Spray");
        sprayButton.addActionListener(this);
        buttonPanel.add(sprayButton);

        pickButton = new JButton("Pick");
        pickButton.addActionListener(this);
        pickPanel.add(pickButton);




        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // This the code that is called when any button is pressed
    // We should have a separate case for each button
    // ** Code to be edited in Part B **
    public void actionPerformed(ActionEvent ae) {
        // If pencilButton is pressed, set drawingPanel mode to "Pencil"
        if (ae.getActionCommand().equals("Pencil")) {
            dPanel.setMode("Pencil");
        }
        if(ae.getActionCommand().equals("Eraser")){
            dPanel.setMode("Eraser");
        }
        if(ae.getActionCommand().equals("Red")){
            dPanel.setColor(Color.RED);
        }
        if(ae.getActionCommand().equals("Blue")){
            dPanel.setColor(Color.BLUE);
        }
        if(ae.getActionCommand().equals("Green")){
            dPanel.setColor(Color.GREEN);
        }
        if(ae.getActionCommand().equals("Black")){
            dPanel.setColor(Color.BLACK);
        }
        if(ae.getActionCommand().equals("Clear")){
            dPanel.clear();
        }
        if(ae.getActionCommand().equals("Spray")){
            dPanel.setMode("Spray");
        }
    }

    // Main method just creates a PaintProgram object
    public static void main(String[] args) {
        PaintProgram x = new PaintProgram();
    }

    class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
        // DrawingPanel has the following instance variables:

        // A 2D array which stores whether or not
        // each pixel should be painted
        // ** To be used in Part B **
        private boolean[][] isPainted;

        // A 2D array which stores the Java Colors
        // of each pixel
        // ** To be used in Part C **
        private Color[][] colors;

        // The mode is a String that we can use to keep track of
        // what should happen if the user presses the mouse
        // ** To be used in Part B **
        private String mode;

        // This keeps track of the current selected color
        // ** To be used in Part C **
        private Color color;

        // These are constant values
        private static final int WIDTH = 500;
        private static final int HEIGHT = 500;

        // Constructor sets up DrawingPanel
        // ** You should never need to edit this code **
        public DrawingPanel() {
            // Set background color
            setBackground(Color.WHITE);

            // Add mouse listeners
            addMouseListener(this);
            addMouseMotionListener(this);

            // Initialize instance variables
            isPainted = new boolean[WIDTH][HEIGHT];
            colors = new Color[WIDTH][HEIGHT];
            mode = "Pencil";
            color = Color.BLACK;
        }

        // Can be called to change the current mode
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setMode(String mode) {
            this.mode = mode;
        }

        // Can be called to change the current color
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setColor(Color color) {
            this.color = color;
        }

        // Sets the size of the DrawingPanel, so frame.pack() considers
        // its preferred size when sizing the JFrame
        // ** You should never need to edit this code **
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        // This is the method that draws everything
        // ** Code to be edited in Part C **
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Loop through the 2D array and draw a 1x1 rectangle
            // on each pixel that is currently painted
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (isPainted[x][y]) {
                        g.setColor(colors[x][y]);
                        g.drawRect(x, y, 1, 1);
                    }
                }
            }
        }

        // MouseListener methods
        // This is the method that is called when the mouse
        // is pressed. This is where most of your code will go
        // ** Code to be edited in Part B **
        public void mousePressed(MouseEvent e) {
            // Check the current mode
            // * If "pencil" mode, we should mark the current
            //   pixel as painted
            if (mode.equals("Pencil")) {
                // Check that mouse is in bounds of panel
                if (e.getX() >= 0 && e.getX() < WIDTH &&
                    e.getY() >= 0 && e.getY() < HEIGHT) {
                    // Set current pixel as painted
                    isPainted[e.getX()][e.getY()] = true;
                    colors[e.getX()][e.getY()] = color;
                }
            }
            if(mode.equals("Eraser")){
                for(int row = e.getX() -5; row <= e.getX() + 5; row++){
                    for(int col = e.getY() -5; col <= e.getY(); col++){
                        isPainted[row][col] = false;
                    }
                }

            }
            if(mode.equals("Spray")){
                for(int row = e.getX() - 8; row <= e.getX() + 8; row++){
                    for(int col = e.getY() -8; col <= e.getY() + 8; col++){
                        int rand_num = (int) (Math.random() * 9);
                        if(rand_num == 4){
                            isPainted[row][col] = true;
                            colors[row][col] = color;
                        }

                    }
                }
            }



            // We need to manually tell the panel to repaint
            // and call the paintComponent method
            repaint();
        }
        public void clear(){
            for(int row = 0; row < WIDTH; row++){
                for(int col = 0; col < HEIGHT; col++){
                    isPainted[row][col] = false;
                }
            }
            repaint();
        }

        // This is a MouseMotionListener method
        // We have this method so that we don't need to click each
        // pixel that we want to draw
        // ** You should never need to edit this code **
        public void mouseDragged(MouseEvent e) {
            mousePressed(e);
        }

        // The remaining MouseListener and MouseMotionLister
        // methods are left blank
        // ** You should never need to edit this code **
        public void mouseReleased(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseEntered(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseExited(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseClicked(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseMoved(MouseEvent e) {
            // This method is intentionally blank
        }
    }
}
