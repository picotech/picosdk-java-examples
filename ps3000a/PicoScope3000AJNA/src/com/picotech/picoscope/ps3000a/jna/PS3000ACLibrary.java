/**************************************************************************
 *
 * Filename:    PS3000ACLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions and enumerations from the ps3000aApi.h
 * C header file for PicoScope 3000 series oscilloscopes using the ps3000a 
 * driver API functions.
 * 
 * Copyright © 2015-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps3000a.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;

public interface PS3000ACLibrary extends Library
{
	
    PS3000ACLibrary INSTANCE = (PS3000ACLibrary) Native.loadLibrary
                                ("ps3000a", PS3000ACLibrary.class
                                );

    // Method definitions from ps3000a.h C header file
    // ==============================================
    
    // C prototype definition : 
    // 		uint32_t ps3000aEnumerateUnits(int16_t * count, int8_t * serials, int16_t serialLth)
    int ps3000aEnumerateUnits(ShortByReference count, byte[] serials, ShortByReference serialLth);

    // C prototype definition :	
    int ps3000aOpenUnit(ShortByReference handle, String serial);

    // C prototype definition
    // uint32_t ps3000aCloseUnit(int16_t handle);
    int ps3000aCloseUnit(short handle);

    //uint32_t ps3000aGetUnitInfo(int16_t      handle, int8_t      *string, int16_t      stringLength,
    //int16_t     *requiredSize, PICO_INFO  info)
    int ps3000aGetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);

    // C prototype definition
    //uint32_t ps3000aChangePowerSource(int16_t      handle, PICO_STATUS powerstate)
    int ps3000aChangePowerSource(short handle, int powerstate);

    // uint32_t ps3000aSetChannel(int16_t handle, PS3000A_CHANNEL channel, short enabled, PS3000A_COUPLING type, PS3000A_RANGE range, float analogueOffset)
    int ps3000aSetChannel(short handle, int channel, short enabled, int type, int range, float analogueOffset);
     
    // uint32_t ps3000aGetTimebase2(int16_t handle, uint32_t timebase, int32_t noSamples, float *timeIntervalNanoseconds, int16_t oversample, int32_t *maxSamples, uint32_t segmentIndex)
    int ps3000aGetTimebase2(short handle, int timebase, int noSamples, FloatByReference timeIntervalNanoseconds, short oversample, IntByReference maxSamples, int segmentIndex);
    
    // uint32_t ps3000aSetDataBuffer(int16_t handle, PS3000A_CHANNEL channelOrPort, int16_t *buffer, int32_t bufferLth, uint32_t segmentIndex, PS3000A_RATIO_MODE  mode)
    int ps3000aSetDataBuffer(short handle, int channelOrPort, Pointer buffer, int bufferLength, int segmentIndex, int ratioMode);
    
    
    // Enumerations
    // ============

    public enum PS3000AChannel
    {
        PS3000A_CHANNEL_A,
        PS3000A_CHANNEL_B,
        PS3000A_CHANNEL_C,
        PS3000A_CHANNEL_D,
        PS3000A_EXTERNAL
    }
    
    public enum PS3000ACoupling
    {
        PS3000A_AC,
        PS3000A_DC
    }
    
    public enum PS3000ARange
    {
        PS3000A_10MV,
        PS3000A_20MV,
        PS3000A_50MV,
        PS3000A_100MV,
        PS3000A_200MV,
        PS3000A_500MV,
        PS3000A_1V,
        PS3000A_2V,
        PS3000A_5V,
        PS3000A_10V,
        PS3000A_20V,
        PS3000A_50V,
        PS3000A_MAX_RANGES
    }
    
    public enum PS3000ARatioMode
    {
        PS3000A_RATIO_MODE_NONE(0),
        PS3000A_RATIO_MODE_AGGREGATE(1),
        PS3000A_RATIO_MODE_DECIMATE(2),
        PS3000A_RATIO_MODE_AVERAGE(4);
        
        private final int ratio;
        
        PS3000ARatioMode(int ratio)
        {
            this.ratio = ratio;
        }
    }
    
}

   
