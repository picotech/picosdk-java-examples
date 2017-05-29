/*******************************************************************************
 *
 * Filename:    TimebaseInfo.java
 *
 * Author:      HSM
 *
 * Description:
 *      Wrapper object to pass data to wrapper library
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *
 */
public class TimebaseInfo 
{
    private int timeInterval;
    private short timeUnits;
    private int maxSamples;

    /**
     * Wrapper object to pass data to wrapper library
     */
    public TimebaseInfo() 
    {
        // Set arbitrary values
        timeInterval = 0;
        timeUnits = 0;
        maxSamples = 0;
    }
    
    

    public int getMaxSamples() 
    {
        return maxSamples;
    }

    public void setMaxSamples(int max_samples) 
    {
        maxSamples = max_samples;
    }

    public int getTimeInterval() 
    {
        return timeInterval;
    }

    public void setTimeInterval(int time_interval) 
    {
        timeInterval = time_interval;
    }

    public short getTimeUnits() 
    {
        return timeUnits;
    }

    public void setTimeUnits(short time_units) 
    {
        timeUnits = time_units;
    }
    
    
    
    
}
