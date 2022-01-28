package Point.Pixel;

import Point.Point;
import Point.PointComporator;

import java.util.ArrayList;

public class Pixel extends Point
{
    char filler = '*';

    public Pixel(int x, int y, char filler)
    {
        super(x, y);
        this.filler = filler;
    }

    public Pixel(int x, int y)
    {
        super(x, y);
    }

    public static ArrayList<Pixel> copyArrayList(ArrayList<Pixel> srcList)
    {
        ArrayList<Pixel> resList = new ArrayList<Pixel>();
        for (var curPixel : srcList)
        {
            resList.add(curPixel.clone());
        }
        return resList;
    }

    public static ArrayList<Pixel> makeUnique(ArrayList<Pixel> srcList)
    {
        ArrayList<Pixel> resList = new ArrayList<Pixel>();
        Pixel cmpPixel = srcList.get(0);
        resList.add(cmpPixel.clone());
        for (var curPixel : srcList)
        {
            if (!curPixel.equals(cmpPixel))
            {
                resList.add(curPixel.clone());
                cmpPixel = curPixel;
            }
        }
        return resList;
    }

    public Pixel clone()
    {
        return new Pixel(super.getX(), super.getY(), filler);
    }

    public char getFiller()
    {
        return filler;
    }

    public void setFiller(char filler)
    {
        this.filler = filler;
    }
}
