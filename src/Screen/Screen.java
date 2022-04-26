package Screen;

import Figure.*;
// import jline3.terminal.src.main.java.org.jline.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;

// import org.jline.reader.impl.TerminalReaderTest;

// import jline3.terminal.src.main.java.*;



public class Screen
{
    private Frame curFrame;
    private ArrayList<Figure> aFigures = new ArrayList<>();
    private boolean isSphere = false;
    private int delay = 1000;

    public Screen(int width, int height, boolean isSphere, int delay)
    {
        curFrame = new Frame(width, height, isSphere);
        this.isSphere = isSphere;
        this.delay = delay;
    }

    public void addFigures(ArrayList <Figure> aFigures)
    {
        for (var curFigure : aFigures)
        {
            addFigure(curFigure);
        }
    }

    public void addFigure(Figure aFigure)
    {
        if (!curFrame.addFigure(aFigure) && isSphere)
        {
            aFigure.stabPos(curFrame.getWidth(), curFrame.getHeight());
        }   
        this.aFigures.add(aFigure.clone());
    }

    private void addFiguresToFrame()
    {
        for (var curFigure : aFigures)
        {
            if (!curFrame.addFigure(curFigure) && isSphere)
            {
                curFigure.stabPos(curFrame.getWidth(), curFrame.getHeight());
            }
        }
    }

    private void makeNextFrame()
    {
        curFrame.clear();
        for (var curFigure : aFigures)
        {
            curFigure.move();
            curFigure.rotate();
        }
        
        addFiguresToFrame();
    }

    public void play() throws InterruptedException, IOException
    {

        while(true)
        {
            curFrame.drawFrame();
            makeNextFrame();
            Thread.sleep(delay);
            System.out.println("[H[2J[3J");
        }
    }

}
