/**************************************************************************
 *
 * Filename:    PS4000ACLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions and enumerations from the ps4000aApi.h 
 * C header file for the PicoScope 4000 Series oscilloscopes using the 
 * ps4000a library API functions.
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps4000a.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;

public interface PS4000ACLibrary extends Library
{
	
	PS4000ACLibrary INSTANCE = (PS4000ACLibrary) Native.loadLibrary
                                    ("ps4000a", PS4000ACLibrary.class
                                    );
	
        // Method definitions from ps4000aApi.h C header file
        // ==================================================
        
        // C prototype definition: 
	// 		uint32_t ps4000aEnumerateUnits(int16_t * count, int8_t * serials, int16_t serialLth)
        int ps4000aEnumerateUnits(ShortByReference count, byte[] serials, ShortByReference serialLth);
        
	// C prototype definition:	
        //              uint32_t ps4000aOpenUnit(int16 * handle, int8_t * serial)
        int ps4000aOpenUnit(ShortByReference handle, String serial);
	
        // C prototype definition:	
        //              uint32_t ps4000aOpenUnitWithResolution(int16 * handle, int8_t * serial, PS4000A_DEVICE_RESOLUTION resolution)
        int ps4000aOpenUnitWithResolution(ShortByReference handle, String serial, int resolution);
        
	// C prototype definition:
	//              uint32_t ps4000aCloseUnit(int16_t handle);
	int ps4000aCloseUnit(short handle);
	
        // C prototype definition:
        //              uint32_t ps4000aGetUnitInfo(int16_t handle, int8_t *string, int16_t stringLength, int16_t *requiredSize, PICO_INFO info)
        int ps4000aGetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);
        
        // C prototype definition:
        //              uint32_t ps4000aChangePowerSource(int16_t handle, PICO_STATUS powerstate)
        int ps4000aChangePowerSource(short handle, int powerstate);
        
        // Enumerations
        // ============

        public enum PS4000AChannel
        {
            PS4000A_CHANNEL_A(0),
            PS4000A_CHANNEL_B(1),
            PS4000A_CHANNEL_C(2),
            PS4000A_CHANNEL_D(3),
            PS4000A_MAX_4_CHANNELS(4),
            PS4000A_CHANNEL_E(4),
            PS4000A_CHANNEL_F(5),
            PS4000A_CHANNEL_G(6),
            PS4000A_CHANNEL_H(7),
            PS4000A_EXTERNAL(8),
            PS4000A_MAX_CHANNELS(8),
            PS4000A_TRIGGER_AUX(9),
            PS4000A_TRIGGER_SOURCES(10),
            PS4000A_PULSE_WIDTH_SOURCE(268435456);
            
            private final int channel;

            PS4000AChannel(int channel)
            {
                this.channel = channel;
            }
        }

        public enum PS4000ACoupling
        {
            PS4000A_AC,
            PS4000A_DC
        }

        public enum PS4000ARange
        {
            PS4000A_10MV,
            PS4000A_20MV,
            PS4000A_50MV,
            PS4000A_100MV,
            PS4000A_200MV,
            PS4000A_500MV,
            PS4000A_1V,
            PS4000A_2V,
            PS4000A_5V,
            PS4000A_10V,
            PS4000A_20V,
            PS4000A_50V,
            PS4000A_100V,
            PS4000A_200V,
            PS4000A_MAX_RANGES
        }

        public enum PS4000ARatioMode
        {
            PS4000A_RATIO_MODE_NONE(0),
            PS4000A_RATIO_MODE_AGGREGATE(1),
            PS4000A_RATIO_MODE_DECIMATE(2),
            PS4000A_RATIO_MODE_AVERAGE(4);

            private final int ratio;

            PS4000ARatioMode(int ratio)
            {
                this.ratio = ratio;
            }
        }
        
        public enum PS4000ADeviceResolution
        {
            PS4000A_DR_8BIT,
            PS4000A_DR_12BIT,
            PS4000A_DR_14BIT,
            PS4000A_DR_15BIT,
            PS4000A_DR_16BIT;
        }
        
}
   
