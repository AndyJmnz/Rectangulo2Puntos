import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Rectangulo_2_Puntos extends JFrame{

    private BufferedImage buffer;
    private Graphics graPixel;
    private ArrayList<Integer> arreglox = new ArrayList<>();
    private ArrayList<Integer> arregloy = new ArrayList<>();
    Scanner s = new Scanner(System.in);
    private Color red = Color.RED;

    public static void main(String[] args) {
        Rectangulo_2_Puntos ventana = new Rectangulo_2_Puntos();
        ventana.MiVentana();
    }

    public void MiVentana() {
        setTitle("Rectangulo");
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = buffer.createGraphics();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x1 = 100, y1 = 100, x2 = 300, y2 = 200;
        rectangulo(x1, x2, y1, y2);
    }

    public void rectangulo(int x1, int x2, int y1, int y2) {
        // Dibujar las líneas horizontales del rectángulo
        for (int aux = x1; aux <= x2; aux++) {
            putPixel(aux, y1, red);  // Línea superior
            putPixel(aux, y2, red);  // Línea inferior
        }
        // Dibujar las líneas verticales del rectángulo
        for (int aux = y1; aux <= y2; aux++) {
            putPixel(x1, aux, red);  // Línea izquierda
            putPixel(x2, aux, red);  // Línea derecha
        }
    }

    public void circulo_puntoMedio(int xc, int yc, int R) {
        int x = 0, y = R;
        double pk;
        pk = 1 - R;
        while (x < y) {
            if (pk < 0) {
                pk = pk + 2 * x + 3;
            } else {
                y--;
                pk = pk + 2 * (x - y) + 5;
            }
            putPixel(xc + x, yc + y, red);
            putPixel(xc - x, yc + y, red);
            putPixel(xc + x, yc - y, red);
            putPixel(xc - x, yc - y, red);
            putPixel(xc + y, yc + x, red);
            putPixel(xc - y, yc + x, red);
            putPixel(xc + y, yc - x, red);
            putPixel(xc - y, yc - x, red);
            x++;
        }
    }

    public void puntosCurvaOlas() {
        int y = 0;
        for (int x = 0; x <= 100; x += (100 / 100)) {
            y = (int) (-100 * Math.sin(Math.toRadians(x)));
            arreglox.add(x + 150);
            arregloy.add(y + 150);
        }
        unirPuntosCurvasOlas();
    }

    public void unirPuntosCurvasOlas() {
        for (int i = 0; i < (arreglox.size() - 1); i++) {
            LineaBresenham(arreglox.get(i), arregloy.get(i), arreglox.get(i + 1), arregloy.get(i + 1), Color.RED);
        }
    }

    public void LineaBresenham(int x0, int y0, int x1, int y1, Color color) {
        int x, y, dx, dy, p, incE, incNE, stepx, stepy;
        dx = (x1 - x0);
        dy = (y1 - y0);

        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        } else {
            stepy = 1;
        }
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        } else {
            stepx = 1;
        }

        x = x0;
        y = y0;

        if (dx > dy) {
            p = 2 * dy - dx;
            incE = 2 * dy;
            incNE = 2 * (dy - dx);
            while (x != x1) {
                x = x + stepx;
                if (p < 0) {
                    p = p + incE;
                } else {
                    y = y + stepy;
                    p = p + incNE;
                }
                putPixel(x, y, color);
            }
        } else {
            p = 2 * dx - dy;
            incE = 2 * dx;
            incNE = 2 * (dx - dy);

            while (y != y1) {
                y = y + stepy;
                if (p < 0) {
                    p = p + incE;
                } else {
                    x = x + stepx;
                    p = p + incNE;
                }
                putPixel(x, y, color);
            }
        }
    }

    public void putPixel(int x, int y, Color c) {
        graPixel.setColor(c);
        graPixel.fillRect(0, 0, 1, 1);
        getGraphics().drawImage(buffer, x, y, this);
    }
}