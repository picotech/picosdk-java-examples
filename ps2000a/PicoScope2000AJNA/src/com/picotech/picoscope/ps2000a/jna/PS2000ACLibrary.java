/**************************************************************************
 *
 * Filename:    PS2000ACLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions from the ps2000aApi.h 
 * C header file for the PicoScope 2000 Series oscilloscopes using the 
 * ps2000a library API functions.
 * 
 * Copyright © 2015-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps2000a.jna;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;


public interface PS2000ACLibrary extends Library
{
	
	PS2000ACLibrary INSTANCE = (PS2000ACLibrary) Native.loadLibrary
                                    (
                                        "ps2000a", PS2000ACLibrary.class
                                    );
	
        // Method definitions from ps2000aApi.h C header file
        // ==================================================
        
        // C prototype definition: 
	// 		uint32_t ps2000aEnumerateUnits(int16_t * count, int8_t * serials, int16_t serialLth)
        int ps2000aEnumerateUnits(ShortByReference count, byte[] serials, ShortByReference serialLth);
        
	// C prototype definition: 
	// 		uint32_t ps2000aOpenUnit( uint16_t *handle, int8_t *serial)	
        int ps2000aOpenUnit(ShortByReference handle, Memory serial);
	
	// C prototype definition:
	//              uint32_t ps2000aCloseUnit(int16_t handle);
	int ps2000aCloseUnit(short handle);
	
        // C prototype definition:
        //              uint32_t ps2000aGetUnitInfo(int16_t handle, int8_t *string, int16_t stringLength,
        //              int16_t *requiredSize, PICO_INFO info)
        int ps2000aGetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);
           
}
   
