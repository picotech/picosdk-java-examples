/*******************************************************************************
 *
 * Filename:    TimesAndValues.java
 *
 * Author:      HSM
 *
 * Description:
 *      Object to help retrieve data from PicoScope oscilloscopes using the 
 *      ps2000 and ps3000 driver API functions.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *
 */
public class TimesAndValues 
{
    private static final int DEFAULT_BUFFER_SIZE = 1024;
    
    private int[] times;        // Buffer to hold sample times (interval between trigger event and corresponding sample)
    private short[] bufferA;    // Channel A values 
    private short[] bufferB;    // Channel B values
    private short[] bufferC;    // Channel C values 
    private short[] bufferD;    // Channel D values
    private short overflow;

    /**
     * Constructor method
     */
    public TimesAndValues() 
    {
        times = new int[DEFAULT_BUFFER_SIZE];
        
        bufferA = new short[DEFAULT_BUFFER_SIZE];
        bufferB = new short[DEFAULT_BUFFER_SIZE];
        bufferC = new short[DEFAULT_BUFFER_SIZE];
        bufferD = new short[DEFAULT_BUFFER_SIZE];
        
        overflow = 0;
    }
    
    public TimesAndValues(int buffer_size)
    {
        times = new int[buffer_size];
        
        bufferA = new short[buffer_size];
        bufferB = new short[buffer_size];
        bufferC = new short[buffer_size];
        bufferD = new short[buffer_size];
        
        overflow = 0;
    }

    public short[] getBufferA() 
    {
        return bufferA;
    }

    public void setBufferA(short[] buffer_A) 
    {
        System.arraycopy(buffer_A, 0, bufferA, 0, bufferA.length);
    }

    public short[] getBufferB() 
    {
        return bufferB;
    }

    public void setBufferB(short[] buffer_B) 
    {
        System.arraycopy(buffer_B, 0, bufferB, 0, bufferB.length);
    }
    
    public short[] getBufferC() 
    {
        return bufferC;
    }

    public void setBufferC(short[] buffer_C) 
    {
        System.arraycopy(buffer_C, 0, bufferC, 0, bufferC.length);
    }

    public short[] getBufferD() 
    {
        return bufferD;
    }

    public void setBufferD(short[] buffer_D) 
    {
        System.arraycopy(buffer_D, 0, bufferD, 0, bufferB.length);
    }

    public short getOverflow() 
    {
        return overflow;
    }

    public void setOverflow(short tv_overflow) 
    {
        overflow = tv_overflow;
    }

    public int[] getTimes() 
    {
        return times;
    }

    public void setTimes(int[] tv_times) 
    {
        System.arraycopy(tv_times, 0, times, 0, times.length);
    }
    
    
}
