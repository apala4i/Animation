import java.io.IOException;
import java.util.Scanner;

import Figure.ReadyFigure.*;
import Figure.BaseFigure.Square.*;
import Figure.BaseFigure.Circle.*;
import Figure.BaseFigure.Triangle.*;
import Screen.*;
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
                {
                    System.out.println("Saving your settings.");
                    aSettings.writeToFile();
                    break;
                }
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

        Screen screen = new Screen(width, height, true, 80);


        screen.addFigure(new Triangle(new Point(30, 10), 5, new Point(-1, 1), 90.0));
        screen.addFigure(new Square(new Point(30, 5), 10, new Point(1, 0), 0.0));
        screen.addFigure(new Circle(new Point(10, 10), 4, new Point(0, 1), 0.0));
        screen.addFigure(new ReadyFigure("test.txt"));
        screen.addFigure(new ReadyFigure("test2.txt"));

        screen.play();
    }
}