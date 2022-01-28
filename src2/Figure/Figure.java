package Figure;

import Point.*;
import Point.Pixel.*;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Figure
{
    protected Point position;
    protected Point direction;
    protected double rotation = 0.0;
    protected ArrayList<Pixel> figurePixels = new ArrayList<Pixel>();

    public Figure(Point position, Point direction, double rotation)
    {
        this.position = position.clone();
        this.direction = direction.clone();
        this.rotation = rotation;
    }

    public Figure()
    {
        position = new Point(0, 0);
        direction = new Point(0, 0);
        rotation = 0.0;
    }

    public void rotate()
    {
        for (var curPoint : figurePixels)
        {
            curPoint.rotate(position, rotation);
        }
        Collections.sort(figurePixels, new PointComporator());
        figurePixels = Pixel.makeUnique(figurePixels);
    }

    public void rotate(double rotation)
    {
        for (var curPoint : figurePixels)
        {
            curPoint.rotate(position, rotation);
        }
        Collections.sort(figurePixels, new PointComporator());
        figurePixels = Pixel.makeUnique(figurePixels);
    }

    public void rotate(Point position, double rotation)
    {
        for (var curPoint : figurePixels)
        {
            curPoint.rotate(position, rotation);
        }
        Collections.sort(figurePixels, new PointComporator());
        figurePixels = Pixel.makeUnique(figurePixels);
    }

    public void move()
    {
        for (var pixel : figurePixels)
        {
            pixel.setX(pixel.getX() + direction.getX());
            pixel.setY(pixel.getY() + direction.getY());
        }
        position.setX(position.getX() + direction.getX());
        position.setY(position.getY() + direction.getY());
        Collections.sort(figurePixels, new PointComporator());
    }

    public void move(Point direction)
    {
        for (var pixel : figurePixels)
        {
            pixel.setX(pixel.getX() + direction.getX());
            pixel.setY(pixel.getY() + direction.getY());
        }
        Collections.sort(figurePixels, new PointComporator());
    }

    public Point getPosition()
    {
        return position.clone();
    }

    public Point getDirection()
    {
        return direction.clone();
    }

    public double getRotationtion()
    {
        return rotation;
    }

    public ArrayList<Pixel> getPixels()
    {
        return figurePixels;
    }

    public void setPosition(Point position)
    {
        this.position = position.clone();
    }

    public void setDirection(Point direction)
    {
        this.direction = direction.clone();
    }

    public void setRotation(double rotation)
    {
        this.rotation = rotation;
    }
}
