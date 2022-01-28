import java.io.IOException;
import java.util.ArrayList;
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
        Frame aFrame = new Frame(width, height);


        // ArrayList <ReadyFigure> picuresArray = new ArrayList<ReadyFigure>();
        // ReadyFigure xuiFigure = new ReadyFigure("xui.txt", aFrame);
        // ReadyFigure pizdaFigure = new ReadyFigure("pizda.txt", aFrame);

        ReadyFigure Polina = new ReadyFigure("testFigure.txt", aFrame);
        
        // picuresArray.add(xuiFigure);
        // picuresArray.add(pizdaFigure);
        
 
        ArrayList <Figure> figuresArray = new ArrayList<Figure>();

        Figure aCircle = new Figure("Circle", 5, new Point(10, 10), new Point(1, 0));
        // Figure bCircle = new Figure("Circle", 8, new Point(10, 10), new Point(0, 2));
        figuresArray.add(aCircle);
        // figuresArray.add(bCircle);
        

        while (true)
        {
            
            aFrame = new Frame(width, height);
            aCircle.setSize((aCircle.getSize() + 1) % 10);
            aCircle.move();
            aCircle.stabPos(width, height);

            Polina.move(aFrame);
            aFrame.addFigure(Polina);
            aFrame.addFigure(aCircle);
            aFrame.drawFrame();

            // for (var i: figuresArray)
            // {
            //     i.move();
            //     i.setSize((i.getSize() + 1) % i.getSize() + 1);
            //     i.stabPos(width, height);
            //     aFrame.addFigure(i);
            // }

            // for (var i: picuresArray)
            // {
            //     i.move(aFrame);
            //     aFrame.addFigure(i);
            // }

            Thread.sleep(100);
        }
    }

    public int readSettingsFromFile()
    {

        return 0;
    }
}