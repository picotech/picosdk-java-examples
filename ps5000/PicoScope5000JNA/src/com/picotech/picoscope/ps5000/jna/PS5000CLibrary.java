/**************************************************************************
 *
 * Filename:    PS5000CLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions from the ps5000Api.h C header file 
 * for the PicoScope 5203 and 5204 oscilloscopes using the 
 * ps5000 library API functions.
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps5000.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;

public interface PS5000CLibrary extends Library
{
	
	PS5000CLibrary INSTANCE = (PS5000CLibrary) Native.loadLibrary
                                    (
                                        "ps5000", PS5000CLibrary.class
                                    );
	
        // Method definitions from ps5000Api.h C header file
        // =================================================
        
	// C prototype definition:
        // uint32_t ps5000OpenUnit(int16_t *handle)
        int ps5000OpenUnit(ShortByReference handle);
	
	// C prototype definition:
	// uint32_t ps5000CloseUnit(int16_t handle);
	int ps5000CloseUnit(short handle);
	
        // C prototype definition:
        // unit32_t ps5000GetUnitInfo(int16_t handle, int8_t *string, int16_t stringLength,
        // int16_t *requiredSize, PICO_INFO info)
        int ps5000GetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);
        
        
}
   
