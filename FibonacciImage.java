import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FibonacciImage extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int FIBONACCI_COUNT = 100;


    public FibonacciImage() {
        super("Fibonacci Spiral");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        FibonacciPanel panel = new FibonacciPanel();
        getContentPane().add(panel);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FibonacciImage();
        });
    }

    private static class FibonacciPanel extends JPanel {

        private static final int PANEL_WIDTH = 800;
        private static final int PANEL_HEIGHT = 600;
        private final List<Integer> fibonacciNumbers;
        private final double scaleFactor = 1;

        public FibonacciPanel() {
            fibonacciNumbers = generateFibonacciNumbers(FIBONACCI_COUNT);
        }

            @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int fib1 = 0;
            int fib2 = 1;


            for (int i = 0; i < FIBONACCI_COUNT; i++) {
                int fibSum = fib1 + fib2;

                double angle = i * 1; 

                int x = centerX + (int) (scaleFactor * fibSum * Math.cos(angle));
                int y = centerY - (int) (scaleFactor * fibSum * Math.sin(angle));

                g.fillOval(x, y, 4, 4);
                g.drawString(String.valueOf(fibSum), x, y);

                fib1 = fib2;
                fib2 = fibSum;
            }
        }

        private void drawSquare(Graphics g, int x, int y, int sideLength) {
            g.drawRect(x - sideLength / 2, y - sideLength / 2, sideLength, sideLength);
        }

        private List<Integer> generateFibonacciNumbers(int count) {
            List<Integer> fibonacciNumbers = new ArrayList<>();
            int fib1 = 0;
            int fib2 = 1;

            for (int i = 0; i < count; i++) {
                fibonacciNumbers.add(fib1);
                int fibSum = fib1 + fib2;
                fib1 = fib2;
                fib2 = fibSum;
            }

            return fibonacciNumbers;
        }

        @Override
        public Dimension getPreferredSize() {
            // int maxFibonacci = fibonacciNumbers.get(FIBONACCI_COUNT - 1);
            // int size = (int) (maxFibonacci * scaleFactor * 2);
            return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
        }
    }
}
