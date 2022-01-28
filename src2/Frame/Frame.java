package Frame;

import java.util.ArrayList;

import Figure.*;
import Point.*;
import Point.Pixel.*;
// import Figure.ReadyFigure.*;
import Figure.BaseFigure.*;

public final class Frame
{
    private ArrayList<Pixel> framePixels = new ArrayList<Pixel>();

    private int width = 0;
    private int height = 0;
    private int squeezeX = 2;
    private int squeezeY = 1;

    public Frame(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public boolean addFigure(Figure aFigure)
    {
        ArrayList<Pixel> figurePixels = Pixel.copyArrayList(aFigure.getPixels());
        if (aFigure instanceof BaseFigure)
        {
            squeezeAllPixels(figurePixels);
        }

        int framePixelsLen = framePixels.size();
        addFigureToFramePixels(figurePixels);
        return framePixelsLen != framePixels.size();
    }

    private void squeezeAllPixels(ArrayList<Pixel> pixels)
    {
        for (var curPixel : pixels)
        {
            squeezePixel(curPixel);
        }
        // Collections.sort(pixels, new PointComporator());
        pixels.sort(new PointComporator());
    }

    private void squeezePixel(Pixel pixel)
    {
        pixel.setX(pixel.getX() * squeezeX);
        pixel.setY(pixel.getY() * squeezeY);
    }

    private void addFigureToFramePixels(ArrayList<Pixel> figurePixels)
    {
        int curFrameIndex = 0;
        int curFigureIndex = 0;

        ArrayList<Pixel> resPoints = new ArrayList<Pixel>();

        while (curFrameIndex != framePixels.size() && curFigureIndex != figurePixels.size())
        {
            int cmpRes = new PointComporator().compare(framePixels.get(curFrameIndex),
                    figurePixels.get(curFigureIndex));
            if (cmpRes > 0)
            {
                addPixelToFrame(resPoints, figurePixels.get(curFigureIndex++));
            } else if (cmpRes < 0)
            {
                resPoints.add(framePixels.get(curFrameIndex++));
            } else
            {
                resPoints.add(framePixels.get(curFrameIndex++));
                curFigureIndex++;
            }
        }

        while (curFrameIndex != framePixels.size())
        {
            addPixelToFrame(resPoints, framePixels.get(curFrameIndex++));
        }

        while (curFigureIndex != figurePixels.size())
        {
            addPixelToFrame(resPoints, figurePixels.get(curFigureIndex++));
        }
        framePixels = resPoints;
    }

    private void addPixelToFrame(ArrayList<Pixel> resPoints, Pixel aPixel)
    {
        if (isOnFrame(aPixel))
        {
            resPoints.add(aPixel);
        }
    }

    private boolean isOnFrame(Pixel aPixel)
    {
        return (aPixel.getX() < width) && (aPixel.getY() < height) && (aPixel.getX() >= 0) && (aPixel.getY() >= 0);
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
                    if (new Point(j, i).equals(nextPixel))
                    {
                        System.out.printf("%c", nextPixel.getFiller());
                        if (framePixels.size() - 1 != nextIndex)
                        {
                            nextPixel = framePixels.get(++nextIndex);
                        }
                    } else
                    {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
        else
        {
            for (int i = 0; i < height; ++i)
            {
                for (int j = 0; j < width; ++j)
                {
                    System.out.print(" ");
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

    public void setSqueezeX(int squeezeX)
    {
        this.squeezeX = squeezeX;
    }

    public void setSqueezeY(int squeezeY)
    {
        this.squeezeY = squeezeY;
    }
}
