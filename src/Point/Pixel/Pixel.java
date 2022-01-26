package Point.Pixel;

import Point.Point;

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

    public char getFiller()
    {
        return filler;
    }

    public void setFiller(char filler)
    {
        this.filler = filler;
    }
}
