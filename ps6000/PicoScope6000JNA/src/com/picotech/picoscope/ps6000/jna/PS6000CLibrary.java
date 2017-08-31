/**************************************************************************
 *
 * Filename:    PS6000CLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions from the ps6000Api.h C header file 
 * for the PicoScope 6000 Series oscilloscopes using the ps6000 library 
 * API functions.
 * 
 * Copyright © 2016-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/
package com.picotech.picoscope.ps6000.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;


public interface PS6000CLibrary extends Library
{
    PS6000CLibrary INSTANCE = (PS6000CLibrary) Native.loadLibrary
                                    (
                                        "ps6000", PS6000CLibrary.class
                                    );
	
        // Method definitions from ps6000Api.h C header file
        // =================================================
    
        // C prototype definition: 
	// 		uint32_t ps6000EnumerateUnits(int16_t * count, int8_t * serials, int16_t serialLth)
        int ps6000EnumerateUnits(ShortByReference count, byte[] serials, ShortByReference serialLth);
        
	// C prototype definition:	
        // uint32_t ps6000OpenUnit(int16_t * handle, int8_t * serial);
        int ps6000OpenUnit(ShortByReference handle, String serial);
	
	// C prototype definition:
	// uint32_t ps6000CloseUnit(int16_t handle);
	int ps6000CloseUnit(short handle);
	
        // C prototype definition:
        // uint32_t ps6000GetUnitInfo(int16_t handle, int8_t *string, int16_t stringLength,
        // int16_t *requiredSize, PICO_INFO info)
        int ps6000GetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);
}



