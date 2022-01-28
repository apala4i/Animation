package Figure.ReadyFigure;

import Point.*;
import Point.Pixel.Pixel;
import Figure.Figure;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public final class ReadyFigure extends Figure
{

    public ReadyFigure(String filename) throws IOException
    {
        try
        {
            Scanner figureScanner = new Scanner(Path.of(filename), StandardCharsets.UTF_8);
            setPosition(new Point(figureScanner.nextInt(), figureScanner.nextInt()));
            setDirection(new Point(figureScanner.nextInt(), figureScanner.nextInt()));
            fillFigurePixels(figureScanner);

        }
        catch (IOException e)
        {
            throw new IOException();
        }
    }

    private void fillFigurePixels(Scanner figureScanner)
    {
        int i = 0;
        while (figureScanner.hasNext())
        {
            String line = figureScanner.nextLine();
            for (int j = 0; j < line.length(); ++j)
            {
                if (line.charAt(j) != ' ')
                {
                    figurePixels.add(new Pixel(j + position.getX(), i + position.getY(), line.charAt(j)));
                }
            }
            i++;
        }
    }

}
