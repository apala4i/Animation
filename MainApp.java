import java.io.IOException;
import java.util.Scanner;

import Animation.*;

public class MainApp
{
    public static void main(String[] argc) throws IOException, InterruptedException
    {
        final int width = 80;
        final int height = 23;
        Figure aCircle = new Figure("Circle", 5, new Point(40, 10), new Point(1, -1));
        Figure bCircle = new Figure("Circle", 8, new Point(10, 10), new Point(0, 2));
        Frame aFrame = new Frame(width, height);
        aFrame.addFigure(aCircle);
        aFrame.addFigure(bCircle);

        while (true)
        {
            aFrame.drawFrame(aCircle);
            // aCircle.setSize((aCircle.getSize() + 1) % 5);
            aCircle.move();
            aCircle.stabPos(width, height);
            // bCircle.setSize((bCircle.getSize() + 1) % 8);
            bCircle.move();
            bCircle.stabPos(width, height);
            aFrame = new Frame(width, height);
            aFrame.addFigure(aCircle);
            aFrame.addFigure(bCircle);
            Thread.sleep(300);
        }
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
    }
}