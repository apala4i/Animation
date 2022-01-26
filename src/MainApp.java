import java.io.IOException;
import java.util.Scanner;

import Frame.*;
import Figure.*;
import Figure.ReadyFigure.ReadyFigure;
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
            }
            else if (answer.equals("n"))
            {
                int rc = aSettings.readFromUser();
                if (rc == 0)
                    break;
            }
            else
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
        Figure aCircle = new Figure("Circle", 5, new Point(40, 10), new Point(1, -1));
        Figure bCircle = new Figure("Circle", 8, new Point(10, 10), new Point(0, 2));
        Frame aFrame = new Frame(width, height);
        //aFrame.addFigure(aCircle);
        //aFrame.addFigure(bCircle);

        ReadyFigure cFigure = new ReadyFigure("testFigure.txt", aFrame);

        while (true)
        {
            aFrame.drawFrame();
            // aCircle.setSize((aCircle.getSize() + 1) % 5);
            //aCircle.move();
            aCircle.stabPos(width, height);
            // bCircle.setSize((bCircle.getSize() + 1) % 8);
            //bCircle.move();
            bCircle.stabPos(width, height);
            aFrame = new Frame(width, height);

            cFigure.move(aFrame);

            aFrame.addFigure(cFigure);
            //aFrame.addFigure(aCircle);
            //aFrame.addFigure(bCircle);
            Thread.sleep(100);
        }
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
    }

    public int readSettingsFromFile()
    {

        return 0;
    }
}