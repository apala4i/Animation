package Figure.ReadyFigure;

import Point.*;
import Point.Pixel.Pixel;
import Figure.Figure;
import Frame.Frame;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;


public class ReadyFigure extends Figure
{
    private ArrayList<Pixel> figurePixels = new ArrayList<Pixel>();


    public ReadyFigure(String type, int size, Point pos, Point direction, Frame aFrame)
    {
        super(type, size, pos, direction);
        if (type.equals("Circle"))
        {
            makeCircle(aFrame);
        }
    }

    public ReadyFigure(Figure aFigure, Frame aFrame)
    {
        super(aFigure.getType(), aFigure.getSize(), aFigure.getPos(), aFigure.getDirection());
        if (aFigure.getType().equals("Circle"))
        {
            makeCircle(aFrame);
        }
    }

    public ReadyFigure(String filename, Frame aFrame)  throws IOException
    {
        try
        {
            Scanner figureScanner = new Scanner(Path.of(filename), StandardCharsets.UTF_8);
            super.setDirection(new Point(figureScanner.nextInt(), figureScanner.nextInt()));
            fillFigurePixels(figureScanner, aFrame);
            
        }
        catch (IOException e)
        {
            throw new IOException();
        }   
    }

    private void makeCircle(Frame aFrame)
    {
        for (int i = 0; i < aFrame.getHeight(); ++i)
        {
            for (int j = 0; j < aFrame.getWidth(); ++j)
            {
                if (inCircleCheck(new Point(j, i)))
                {
                    Pixel newPixel = new Pixel(j, i);
                    squeezePixel(newPixel, aFrame);
                    figurePixels.add(newPixel);
                }
            }
        }
        Collections.sort(figurePixels, new PointComporator());
    }

    private void squeezePixel(Pixel pixel, Frame aFrame)
    {
        pixel.setX(pixel.getX() * aFrame.getSqueezeX() % aFrame.getWidth());
        pixel.setY(pixel.getY() * aFrame.getSqueezeY() % aFrame.getHeight());
    }

    private boolean inCircleCheck(Point checkDot)
    {
        return (Math.pow((double)(checkDot.getX() - super.getPos().getX()), 2)
        + Math.pow((double)(checkDot.getY() - super.getPos().getY()), 2) <= Math.pow(super.getSize(), 2));
    }

    private void fillFigurePixels(Scanner figureScanner, Frame aFrame)
    {
        int i = 0;
        while (figureScanner.hasNext())
        {
            String line = figureScanner.nextLine();
            for (int j = 0; j < Math.min(line.length(), aFrame.getWidth()); ++j)
            {
                if (line.charAt(j) != ' ')
                {
                    figurePixels.add(new Pixel(j, i, line.charAt(j)));
                }
            }
            i++;
        }
    }

    public ArrayList<Pixel> getFigurePixels()
    {
        return figurePixels;
    }

    //@Override
    public void move(Frame aFrame)
    {
        for (int i = 0; i < figurePixels.size(); ++i)
        {
            Pixel curPixel = figurePixels.get(i);
            Point direction = super.getDirection();
            curPixel.setX((curPixel.getX() + direction.getX()) % aFrame.getWidth());
            curPixel.setY((curPixel.getY() + direction.getY()) % aFrame.getHeight());
        }
    }
}
