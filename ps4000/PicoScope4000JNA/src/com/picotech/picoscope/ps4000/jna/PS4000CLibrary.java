/**************************************************************************
 *
 * Filename:    PS4000CLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions and enumerations from the ps4000Api.h 
 * C header file for the PicoScope 4000 Series oscilloscopes using the 
 * ps4000 library API functions.
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps4000.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;

public interface PS4000CLibrary extends Library
{
	
	PS4000CLibrary INSTANCE = (PS4000CLibrary) Native.loadLibrary
                                    (
                                        "ps4000", PS4000CLibrary.class
                                    );
	
        // Method definitions from ps4000Api.h C header file
        // =================================================
        
        // C prototype definition : 
	//      uint32_t ps4000EnumerateUnits(int16_t * count, int8_t * serials, int16_t serialLth)
        int ps4000EnumerateUnits(ShortByReference count, byte[] serials, ShortByReference serialLth);
        
	// C prototype definition :
        //      uint32_t ps4000OpenUnit(int16_t * handle)             
        int ps4000OpenUnit(ShortByReference handle);
	
	// C prototype definition
	//      uint32_t ps4000CloseUnit(int16_t handle);
	int ps4000CloseUnit(short handle);
	
        // C prototype definition
        //      unit32_t ps4000GetUnitInfo(int16_t handle, int8_t *string, int16_t stringLength,
        //                                  int16_t *requiredSize, PICO_INFO info)
        int ps4000GetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);
        
        
        // Enumerations
        // ============

        public enum PS4000Channel
        {
            PS4000_CHANNEL_A(0),
            PS4000_CHANNEL_B(1),
            PS4000_CHANNEL_C(2),
            PS4000_CHANNEL_D(3),
            PS4000_EXTERNAL(4),
            PS4000_MAX_CHANNELS(4),
            PS4000_TRIGGER_AUX(5),
            PS4000_MAX_TRIGGER_SOURCES(6);
            
            private final int channel;

            PS4000Channel(int channel)
            {
                this.channel = channel;
            }
        }

        public enum PS4000Range
        {
            PS4000_10MV,
            PS4000_20MV,
            PS4000_50MV,
            PS4000_100MV,
            PS4000_200MV,
            PS4000_500MV,
            PS4000_1V,
            PS4000_2V,
            PS4000_5V,
            PS4000_10V,
            PS4000_20V,
            PS4000_50V,
            PS4000_100V,
            PS4000_MAX_RANGES
        }

        public enum PS4000RatioMode
        {
            RATIO_MODE_NONE(0),
            RATIO_MODE_AGGREGATE(1),
            RATIO_MODE_AVERAGE(2);

            private final int ratio;

            PS4000RatioMode(int ratio)
            {
                this.ratio = ratio;
            }
        }
}
   
