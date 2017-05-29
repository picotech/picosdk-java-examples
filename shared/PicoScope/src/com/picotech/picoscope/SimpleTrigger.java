/*******************************************************************************
 *
 * Filename:    SimpleTrigger.java
 *
 * Author:      HSM
 *
 * Description:
 *      This class contains information for a simple trigger.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 * This class contains information for a simple trigger.
 * 
 */
public class SimpleTrigger 
{
    private Enum channel;
    float threshold;
    short direction;
    float delay;

    /**
     * Constructor to create a new Simple Trigger object
     * 
     * @param st_channel - The channel on which to trigger
     * @param st_threshold - The threshold voltage (mV)
     * @param st_direction - The direction
     * @param st_delay - The delay
     */
    public SimpleTrigger(Enum st_channel, float st_threshold, 
            short st_direction, float st_delay) 
    {
        
        channel = st_channel;
        threshold = st_threshold;
        direction = st_direction;
        delay = st_delay;
    }
    
    // Getters and Setters

    /**
     * 
     * @return 
     */
    public Enum getChannel() 
    {
        return channel;
    }

    /**
     * 
     * @param st_channel 
     */
    public void setChannel(Enum st_channel) 
    {
        channel = st_channel;
    }

    /**
     * 
     * @return 
     */
    public float getDelay() 
    {
        return delay;
    }

    /**
     * 
     * @param st_delay 
     */
    public void setDelay(float st_delay) 
    {
        delay = st_delay;
    }

    /**
     * 
     * @return 
     */
    public short getDirection() 
    {
        return direction;
    }

    /**
     * 
     * @param st_direction 
     */
    public void setDirection(short st_direction) 
    {
        direction = st_direction;
    }

    /**
     * 
     * @return 
     */
    public float getThreshold() 
    {
        return threshold;
    }
    
    /**
     * 
     * @param st_threshold 
     */
    public void setThreshold(float st_threshold) 
    {
        this.threshold = st_threshold;
    }

}
