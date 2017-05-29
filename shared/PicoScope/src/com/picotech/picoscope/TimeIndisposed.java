/*******************************************************************************
 *
 * Filename:    StreamingValuesParameters.java
 *
 * Author:      HSM
 *
 * Description:
 *      This is a wrapper class to pass a time indisposed value to a wrapper 
 *      library as a pointer is required for a run_block function.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 * This is a wrapper class to pass a time indisposed value to a wrapper library
 * as a pointer is required for a run_block function.
 * 
 */
public class TimeIndisposed 
{
    private int timeIndisposed; //milliseconds
    
    /**
     * Constructor - initialises timeIndisposed to 0;
     */
    public TimeIndisposed()
    {
        timeIndisposed = 0;
    }

    /**
     * 
     * @return the approximate time (in milliseconds) that the ADC will take to 
     * collect data.
     */
    public int getTimeIndisposed() 
    {
        return timeIndisposed;
    }

    /**
     * 
     * @param time_indisposed 
     */
    public void setTimeIndisposed(int time_indisposed) 
    {
        timeIndisposed = time_indisposed;
    }
    
    
}
