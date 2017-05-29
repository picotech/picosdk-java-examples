/*******************************************************************************
 *
 * Filename:    PicoScope.java
 *
 * Author:      HSM
 *
 * Description:
 *      Abstract class defining a PicoScope oscilloscope.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

import com.picotech.picoscope.util.AbortButtonJFrameRunnable;
import com.picotech.picoscope.util.DataFileWriter;
import java.util.Scanner;

/**
 *
 */
public abstract class PicoScope 
{   
    private boolean continueStreaming;
    private boolean continueReadingBlock;
    
    protected boolean abortButtonPressed;
    protected boolean scaleToMv;
    protected boolean unitOpened;
    
    protected int timebase;
    
    protected AbortButtonJFrameRunnable abortButtonJFrameRunnable;
    protected DataFileWriter dataFileWriter;
    protected OpenUnitParameters openUnitParameters;
    protected Scanner consoleScanner;
    protected UnitModel openedUnit;
    
    /**
     * 
     */
    public PicoScope() 
    {
        continueStreaming = true;
        continueReadingBlock = true;

        consoleScanner = new Scanner(System.in);

    }

    /**
     * 
     * @return 
     */
    public synchronized boolean isContinueStreaming() 
    {
        return continueStreaming;
    }

    /**
     * 
     * @param continue_streaming 
     */
    public synchronized void setContinueStreaming(boolean continue_streaming) 
    {
        continueStreaming = continue_streaming;
    }
    
    /**
     * 
     * @return 
     */
    public synchronized Scanner getConsoleScanner()
    {
        return consoleScanner;
    }

    /**
     * 
     * @return 
     */
    public boolean isContinueReadingBlock() 
    {
        return continueReadingBlock;
    }

    /**
     * 
     * @param continue_reading_block 
     */
    public void setContinueReadingBlock(boolean continue_reading_block) 
    {
        continueReadingBlock = continue_reading_block;
    }

    /**
     * 
     * @return 
     */
    public synchronized boolean isAbortButtonPressed() 
    {
        return abortButtonPressed;
    }

    /**
     * 
     * @param abort_button_pressed 
     */
    public synchronized void setAbortButtonPressed(boolean abort_button_pressed) 
    {
        abortButtonPressed = abort_button_pressed;
    }

    /**
     * 
     * @return 
     */
    public synchronized AbortButtonJFrameRunnable getAbortButtonJFrameRunnable() 
    {
        return abortButtonJFrameRunnable;
    }

    /**
     * 
     * @param abort_button_jframe_runnable 
     */
    public synchronized void setAbortButtonJFrameRunnable(AbortButtonJFrameRunnable abort_button_jframe_runnable) 
    {
        abortButtonJFrameRunnable = abort_button_jframe_runnable;
    }

    
}
