package Screen;

import Figure.*;

import java.util.ArrayList;

// import jline2.src.main.java.jline.*;


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

    public void play() throws InterruptedException
    {
        while(true)
        {
            // curFrame.setWidth(TerminalFactory.get().getWidth());
            // curFrame.setHeight(TerminalFactory.get().getHeight());
            curFrame.drawFrame();
            makeNextFrame();
            Thread.sleep(delay);
        }
    }

}
