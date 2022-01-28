import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Frame.*;
import Figure.*;
import Figure.ReadyFigure.ReadyFigure;
import Figure.BaseFigure.*;
import Figure.BaseFigure.Circle.*;
import Figure.BaseFigure.Square.*;
import Figure.BaseFigure.Triangle.*;
import Point.*;
import Settings.*;

public class MainApp
{
    public static void main(String[] argc) throws IOException, InterruptedException
    {
        var in = new Scanner(System.in);
        System.out.println("Welcome to Dino Game!");
        System.out.println("Made by Selifan, Apala4i, DDBMajor.");
        Settings aSettings;

        while (true)
        {
            System.out.println("Do you want to use saved settings?(y, n)");
            String answer = in.nextLine();

            aSettings = new Settings();
            if (answer.equals("y"))
            {
                int rc = aSettings.readFromFile();
                if (rc != 0)
                {
                    System.out.println("There is no saved settings!");
                    System.out.println("Manual input...");
                    int rcc = aSettings.readFromUser();
                    if (rcc == 0)
                    {
                        System.out.println("Saving your settings.");
                        aSettings.writeToFile();
                        break;
                    }
                }
                break;
            } else if (answer.equals("n"))
            {
                int rc = aSettings.readFromUser();
                if (rc == 0)
                    break;
            } else
            {
                System.out.println("Wrong choice!");
            }
        }
        in.close();

        System.out.println("Game starts in...");
        Thread.sleep(100);
        System.out.println("3...");
        Thread.sleep(100);
        System.out.println("2...");
        Thread.sleep(100);
        System.out.println("1...");
        Thread.sleep(100);

        int width = aSettings.getWidth();
        int height = aSettings.getHeight();

        Frame aFrame = new Frame(width, height);

        ArrayList<Figure> figureArray = new ArrayList<Figure>();

        // figureArray.add(new Triangle(new Point(10, 10), new Point(10,10), new Point(1, 1), 0.0));
        // figureArray.add(new Square(new Point(10, 10), 11, new Point(10, 10), 0.0));
        figureArray.add(new ReadyFigure("test.txt"));

        while (true)
        {
            aFrame = new Frame(width, height);
            for (var curFigure : figureArray)
            {
                curFigure.move();
                // curFigure.rotate();
                aFrame.addFigure(curFigure);
            }
            aFrame.drawFrame();

            Thread.sleep(100);
        }
    }

    public int readSettingsFromFile()
    {

        return 0;
    }
}