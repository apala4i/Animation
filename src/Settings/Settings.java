package Settings;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Settings {
    private int width;
    private int height;
    private String playerName;
    private int record;
    
    public Settings()
    {
        width = 0;
        height = 0;
        playerName = "";
        record = 0;
    }

    public int readFromFile()
    {
        Scanner savedSettingsScaner;
        try
        {
            savedSettingsScaner = new Scanner(Path.of("settings.txt"), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            return -1;
        }
        try
        {
            width = savedSettingsScaner.nextInt();
            height = savedSettingsScaner.nextInt();
            playerName = savedSettingsScaner.next();
            record = savedSettingsScaner.nextInt();
        }
        catch (NoSuchElementException e)
        {
            savedSettingsScaner.close();
            return -2;
        }
        savedSettingsScaner.close();
        return 0;
    }

    public int readFromUser()
    {
        Scanner in = new Scanner(System.in);
        try
        {
            System.out.println("Hello! Please, say your name!");
            playerName = in.nextLine();
            System.out.println("Please, set your current terminal sizes(width, height).");
            width = in.nextInt();
            height = in.nextInt();
        }
        catch (InputMismatchException e)
        {
            return -1;
        }
        record = 0;
        in.close();
        return 0;
    }

    public void writeToFile() throws IOException
    {
        PrintWriter out = new PrintWriter("settings.txt", StandardCharsets.UTF_8);
        out.println(width);
        out.println(height);
        out.println(playerName);
        out.println(record);
        out.close();
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public void setRecord(int record)
    {
        this.record = record;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public int getRecord()
    {
        return record;
    }
}
