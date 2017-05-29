/*******************************************************************************
 *
 * Filename:    ChannelSettings.java
 *
 * Author:      HSM
 *
 * Description:
 *      ChannelSettings defines an object to store channel settings 
 *      information for a PicoScope oscilloscope.
 *
 * Copyright (C) 2012 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *
 */
public class ChannelSettings 
{
    private short dcCoupled;
    private short range;
    private boolean enabled;
    private short[] values;

    /**
     * Creates a new ChannelSettings object to store data on Channel Settings.
     * 
     * @param cs_dc_coupled
     * @param cs_range
     * @param cs_enabled 
     */
    public ChannelSettings(short cs_dc_coupled, short cs_range, 
            boolean cs_enabled, int buffer_size) 
    {
        dcCoupled = cs_dc_coupled;
        range = cs_range;
        enabled = cs_enabled;
        
        values = new short[buffer_size];
    }

    /**
     * 
     * @return 
     */
    public short getDcCoupled() 
    {
        return dcCoupled;
    }

    /**
     * 
     * @param cs_dc_coupled
     */
    public void setDcCoupled(short cs_dc_coupled) 
    {
        dcCoupled = cs_dc_coupled;
    }

    /**
     * Indicates if the channel is enabled or not.
     * 
     * @return true if the channel is enabled, false otherwise
     */
    public boolean isEnabled() 
    {
        return enabled;
    }
    
    /**
     * Method to provide a numerical value representing if the channel is 
     * enabled or not.
     * 
     * @return 1 if channel is enabled, 0 otherwise.
     */
    public short isEnabledValue()
    {
        if(enabled == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    /**
     * 
     * @param cs_enabled 
     */
    public void setEnabled(boolean cs_enabled) 
    {
        enabled = cs_enabled;
    }

    /**
     * 
     * @return 
     */
    public short getRange() 
    {
        return range;
    }

    /**
     * 
     * @param cs_range 
     */
    public void setRange(short cs_range) 
    {
        range = cs_range;
    }
    
        /**
     * 
     * @return 
     */
    public short[] getValues() 
    {
        return values;
    }

    /**
     * 
     * @param cs_values 
     */
    public void setValues(short[] cs_values) 
    {
        System.arraycopy(cs_values, 0, values, 0, values.length);
    }

    public void printChannelInformation()
    {
        System.out.println("DC Coupled: " + dcCoupled);
        System.out.println("Range index: " + range);
        System.out.println("Enabled: " + enabled + " (" + isEnabledValue() + ")");
        System.out.println("Values size: " + values.length);
    }
       
}