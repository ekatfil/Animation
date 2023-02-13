package Animation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class Moon extends Canvas {
    static int width = 500, height=500;

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        try {
            URLConnection openConnection = new
                    URL("https://thumbs.dreamstime.com/b/%D0%B6%D0%B5%D0%BB%D1%82%D1%8B%D0%B9-%D0%BC%D0%B5%D1%81%D1%8F%D1%86-%D0%BB%D1%83%D0%BD%D0%B0-%D0%BD%D0%B0-%D0%B1%D0%B5%D0%BB%D0%BE%D0%BC-%D1%84%D0%BE%D0%BD%D0%B5-%D0%B8%D0%BB%D0%BB%D1%8E%D1%81%D1%82%D1%80%D0%B0%D1%86%D0%B8%D1%8F-%D0%B0%D0%BA%D0%B2%D0%B0%D1%80%D0%B5%D0%BB%D0%B8-%D0%B4%D0%B5%D1%82%D0%B5%D0%B9-173907621.jpg").openConnection();
            openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 " +
                    "(Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            BufferedImage image = ImageIO.read(openConnection.getInputStream());
            int imageWidth = image.getWidth(), imageHeight=image.getHeight();
            int y = 0, x = 0;

            while (true) {
                if (x>width||y>height) {
                    y = 0;
                    x = 0;
                }
                g2.drawImage(image, x, y, this);
                TimeUnit.MILLISECONDS.sleep(100);
                g2.clearRect(x, y, imageWidth, imageHeight);
                x+=3;
                y+=3;
            }
        } catch (IOException | InterruptedException e) {
            g.drawString("Ошибка", 10,10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Moon");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width/2-width/2,dim.height/2-height/2, width,height);
        Moon m=new Moon();
        frame.add(m);
        frame.setVisible(true);
    }
}
