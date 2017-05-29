/*******************************************************************************
 *
 * Filename:    StreamingValuesParameters.java
 *
 * Author:      HSM
 *
 * Description:
 *      This is a wrapper class for use when sending parameters to a 
 *      get_streaming_last_values function.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 * This is a wrapper class for use when sending parameters to a 
 * get_streaming_last_values function.
 * 
 * @author hitesh
 */
public class StreamingValuesParameters 
{
    /** The time in nanoseconds of the first data sample required. */
    private double startTime;
    /** The number of the sample at the trigger reference point. */
    private int triggerAt;
    /** Represents a Boolean indicating that a trigger has occurred and triggerAt is valid. */
    private short trigger;

    /**
     * Constructor to create a new StreamingValuesParameters object - 
     * initialises all values to 0.
     */
    public StreamingValuesParameters() 
    {
       startTime = 0.0d;
       triggerAt = 0;
       trigger = 0;
    }

    public double getStartTime() 
    {
        return startTime;
    }

    public void setStartTime(double sp_start_time) 
    {
        startTime = sp_start_time;
    }

    public int getTriggerAt() 
    {
        return triggerAt;
    }

    public void setTriggerAt(int sp_trigger_at) 
    {
        triggerAt = sp_trigger_at;
    }

    public short getTrigger() 
    {
        return trigger;
    }

    public void setTrigger(short sp_trigger) 
    {
        trigger = sp_trigger;
    }
    
    
    
    
}
