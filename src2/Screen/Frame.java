package Screen;

import java.util.ArrayList;
import java.util.Collections;

import Figure.*;
import Point.*;
import Point.Pixel.*;
// import Figure.ReadyFigure.*;
import Figure.BaseFigure.*;

class Frame
{
    private ArrayList<Pixel> framePixels = new ArrayList<Pixel>();

    private int width = 0;
    private int height = 0;
    private int squeezeX = 2;
    private int squeezeY = 1;
    private boolean isSphere = false;

    public Frame(int width, int height, boolean isSphere)
    {
        this.width = width;
        this.height = height;
        this.isSphere = isSphere;
    }

    public void clear()
    {
        framePixels = new ArrayList<Pixel>();
    }

    public boolean addFigure(Figure aFigure)
    {
        ArrayList<Pixel> figurePixels = Pixel.copyArrayList(aFigure.getPixels());
        if (aFigure instanceof BaseFigure)
        {
            squeezeAllPixels(figurePixels);
        }

        return addFigureToFramePixels(figurePixels);
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

    private boolean addFigureToFramePixels(ArrayList<Pixel> figurePixels)
    {
        boolean fullOnFrame = true;
        int curFrameIndex = 0;
        int curFigureIndex = 0;

        ArrayList<Pixel> resPoints = new ArrayList<Pixel>();

        while (curFrameIndex != framePixels.size() && curFigureIndex != figurePixels.size())
        {
            int cmpRes = new PointComporator().compare(framePixels.get(curFrameIndex),
                    figurePixels.get(curFigureIndex));
            if (cmpRes > 0)
            {
                fullOnFrame &= addPixelToFrame(resPoints, figurePixels.get(curFigureIndex++));
            } else if (cmpRes < 0)
            {
                fullOnFrame &= addPixelToFrame(resPoints, framePixels.get(curFrameIndex++));
            } else
            {
                fullOnFrame &= addPixelToFrame(resPoints, framePixels.get(curFrameIndex++));
                curFigureIndex++;
            }
        }

        while (curFrameIndex != framePixels.size())
        {
            fullOnFrame &= addPixelToFrame(resPoints, framePixels.get(curFrameIndex++));
        }

        while (curFigureIndex != figurePixels.size())
        {
            fullOnFrame &= addPixelToFrame(resPoints, figurePixels.get(curFigureIndex++));
        }
        framePixels = resPoints;
        Collections.sort(framePixels, new PointComporator());
        framePixels = Pixel.makeUnique(framePixels);
        return fullOnFrame;
    }

    private boolean addPixelToFrame(ArrayList<Pixel> resPoints, Pixel aPixel)
    {
        if (isOnFrame(aPixel))
        {
            resPoints.add(aPixel);
            return true;
        }
        else if (isSphere)
        {
            if (aPixel.getX() < 0)
            {
                aPixel.setX(aPixel.getX() + width);
            }
            else
            {
                aPixel.setX(aPixel.getX() % width);
            }
            if (aPixel.getY() < 0)
            {
                aPixel.setY(aPixel.getY() + height);
            }   
            else
            {
                aPixel.setY(aPixel.getY() % height);
            }
            resPoints.add(aPixel);
            return false;
        }
        return false;
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
