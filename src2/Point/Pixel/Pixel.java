package Point.Pixel;

import Point.Point;
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

    public Pixel clone()
    {
        return new Pixel(super.getX(), super.getY());
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
