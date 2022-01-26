package Frame;

import java.util.ArrayList;
import java.util.Collections;

import Figure.*;
import Point.*;
import Point.Pixel.*;
import Figure.ReadyFigure.*;

public class Frame
{
    private ArrayList<Pixel> framePixels = new ArrayList<Pixel>();
    
    private int width = 0;
    private int height = 0; 
    private int squeezeX = 2;
    private int squeezeY = 1;
    
    public Frame(Figure aFigure, int width, int height)
    {
        this.height = height;
        this.width = width;
        if (aFigure.getType().equals("Circle"))
        {
            MakeCircle(aFigure);
        }
    }

    public Frame(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void addFigure(Figure aFigure)
    {
        if (aFigure.getType().equals("Circle"))
        {
            MakeCircle(aFigure);
        }
    }

    public void addFigure(ReadyFigure aReadyFigure)
    {
        addFigureToFramePixels(aReadyFigure);
    }

    private void MakeCircle(Figure aFigure)
    {
        ArrayList<Pixel> figurePixels = new ArrayList<Pixel>();
        for (int i = 0; i < height; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                if (inCircleCheck(aFigure, new Point(j, i)))
                {
                    Pixel newPixel = new Pixel(j, i);
                    squeezePixel(newPixel);
                    figurePixels.add(newPixel);
                }
            }
        }
        Collections.sort(framePixels, new PointComporator());
        addFigureToFramePixels(figurePixels);
    }

    private void squeezePixel(Pixel pixel)
    {
        pixel.setX(pixel.getX() * squeezeX % width);
        pixel.setY(pixel.getY() * squeezeY % height);
    }

    private void addFigureToFramePixels(ArrayList<Pixel> figurePoints)
    {
        int curFrameIndex = 0;
        int curFigureIndex = 0;

        ArrayList<Pixel> resPoints = new ArrayList<Pixel>();

        while (curFrameIndex != framePixels.size() && curFigureIndex != figurePoints.size())
        {   
            int cmpRes = new PointComporator().compare(framePixels.get(curFrameIndex), figurePoints.get(curFigureIndex));
            if (cmpRes > 0)
            {
                resPoints.add(figurePoints.get(curFigureIndex++));
            }
            else if (cmpRes < 0)
            {
                resPoints.add(framePixels.get(curFrameIndex++));
            }
            else
            {
                resPoints.add(framePixels.get(curFrameIndex++));
                curFigureIndex++;
            }
        }

        while (curFrameIndex != framePixels.size())
        {
            resPoints.add(framePixels.get(curFrameIndex++));
        }

        while (curFigureIndex != figurePoints.size())
        {
            resPoints.add(figurePoints.get(curFigureIndex++));
        }
        framePixels = resPoints;
    } 

    private void addFigureToFramePixels(ReadyFigure aReadyFigure)
    {
        int curFrameIndex = 0;
        int curFigureIndex = 0;

        ArrayList<Pixel> resPoints = new ArrayList<Pixel>();
        ArrayList<Pixel> figurePixels = aReadyFigure.getFigurePixels();

        while (curFrameIndex != framePixels.size() && curFigureIndex != figurePixels.size())
        {   
            int cmpRes = new PointComporator().compare(framePixels.get(curFrameIndex), figurePixels.get(curFigureIndex));
            if (cmpRes > 0)
            {
                resPoints.add(figurePixels.get(curFigureIndex++));
            }
            else if (cmpRes < 0)
            {
                resPoints.add(framePixels.get(curFrameIndex++));
            }
            else
            {
                resPoints.add(framePixels.get(curFrameIndex++));
                curFigureIndex++;
            }
        }

        while (curFrameIndex != framePixels.size())
        {
            resPoints.add(framePixels.get(curFrameIndex++));
        }

        while (curFigureIndex != figurePixels.size())
        {
            resPoints.add(figurePixels.get(curFigureIndex++));
        }
        framePixels = resPoints;
    }

    private boolean inCircleCheck(Figure aFigure, Point checkDot)
    {
        return (Math.pow((double)(checkDot.getX() - aFigure.getPos().getX()), 2)
        + Math.pow((double)(checkDot.getY() - aFigure.getPos().getY()), 2) <= Math.pow(aFigure.getSize(), 2));
    }

    public void drawFrame()
    {
        // System.out.print("\033[H\033[2J");
        if (framePixels.size() != 0)
        {
            int nextIndex = 0;
            Pixel nextPixel = framePixels.get(nextIndex);
            for (int i = 0; i < height; ++i)
            {
                for (int j = 0; j < width; ++j)
                {
                    if (new Point(j,i).equals(nextPixel))
                    {
                        System.out.printf("%c", nextPixel.getFiller());
                        if (framePixels.size() - 1 != nextIndex)
                        {
                            nextPixel = framePixels.get(++nextIndex);
                        }
                    }
                    else
                    {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getSqueezeX()
    {
        return squeezeX;
    }

    public int getSqueezeY()
    {
        return squeezeY;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setSqueezeX(int squeezeX )
    {
        this.squeezeX = squeezeX;
    }

    public void setSqueezeY(int squeezeY)
    {
        this.squeezeY = squeezeY;
    }
}


