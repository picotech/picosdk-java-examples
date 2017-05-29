/*******************************************************************************
 *
 * Filename:    VoltageDefinitions.java
 *
 * Author:      HSM
 *
 * Description:
 *      Class to hold information on a PicoScope unit.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *
 */
public class VoltageDefinitions 
{
    public static final int ONE_VOLT                = 1;
    public static final int ONE_VOLT_MICROVOLTS     = 1000000;
    
    // Oscilloscope input ranges in millivolts
    public static final int[] SCOPE_INPUT_RANGES_MV = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 400000};
    
}
