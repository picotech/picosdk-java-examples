/*******************************************************************************
 *
 * Filename:    AdvancedTrigger.java
 *
 * Author:      HSM
 *
 * Description:
 *      AdvancedTrigger defines an object to store advanced trigger 
 *      information for a PicoScope oscilloscope.
 *
 * Copyright (C) 2012 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *
 */
public class AdvancedTrigger 
{
    private short hysteresis;
    
    // DIRECTIONS directions;
    
    private short nProperties;
    
    // PS2000_TRIGGER_CONDITIONS * conditions;
    // PS2000_TRIGGER_CHANNEL_PROPERTIES * channelProperties;
    // PULSE_WIDTH_QUALIFIER pwq;
    
    private int totalSamples;
    private short autoStop;
    private short triggered;
    
    /**
     * 
     */
    public AdvancedTrigger() 
    {
        //TBD
    }

    /**
     * 
     * @return 
     */
    public short getAutoStop() 
    {
        return autoStop;
    }

    /**
     * 
     * @param at_autostop 
     */
    public void setAutoStop(short at_autostop) 
    {
        autoStop = at_autostop;
    }

    /**
     * 
     * @return 
     */
    public short getHysteresis() 
    {
        return hysteresis;
    }

    /**
     * 
     * @param at_hysteresis 
     */
    public void setHysteresis(short at_hysteresis) 
    {
        hysteresis = at_hysteresis;
    }

    /**
     * 
     * @return 
     */
    public short getnProperties() 
    {
        return nProperties;
    }

    /**
     * 
     * @param at_n_properties 
     */
    public void setnProperties(short at_n_properties) 
    {
        nProperties = at_n_properties;
    }

    /**
     * 
     * @return 
     */
    public int getTotalSamples() 
    {
        return totalSamples;
    }

    /**
     * 
     * @param at_total_samples 
     */
    public void setTotalSamples(int at_total_samples) 
    {
        totalSamples = at_total_samples;
    }

    /**
     * 
     * @return 
     */
    public short getTriggered() 
    {
        return triggered;
    }

    /**
     * 
     * @param at_triggered 
     */
    public void setTriggered(short at_triggered) 
    {
        triggered = at_triggered;
    }

}
