import java.io.IOException;
import java.util.Scanner;

import Animation.*;

public class MainApp
{
    public static void main(String[] argc) throws IOException, InterruptedException
    {
        final int width = 10;
        final int height = 10;
        Figure aCircle = new Figure("Circle", 5, new Point(10, 10), new Point(1, 1));
        Frame aFrame = new Frame(aCircle, width, height);

        while (true)
        {
            aFrame.drawFrame();
            aCircle.setSize((aCircle.getSize() + 1) % 5);
            aCircle.move();
            aCircle.stabPos(width, height);
            aFrame = new Frame(aCircle, 23, 80);
            Thread.sleep(100);
        }
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
    }
}