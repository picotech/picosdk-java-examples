/**************************************************************************
 *
 * Filename:    USBTC08CLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions and enumerations from the usbtc08.h C 
 * header file for the USB TC-08 Thermocouple Data Logger.
 * 
 * Copyright © 2016-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/
package com.picotech.picolog.usbtc08jna;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ShortByReference;


public interface USBTC08CLibrary extends Library
{
    USBTC08CLibrary INSTANCE = (USBTC08CLibrary) Native.loadLibrary
                                ( 
                                    "usbtc08", USBTC08CLibrary.class
                                );


    // Method definitions from usbtc08.h C header file
    // ===============================================
    
    // C prototype definition :	
    // int16_t (usb_tc08_open_unit) (void);
    short usb_tc08_open_unit();

    // C prototype definition :
    // int16_t (usb_tc08_close_unit) (int16_t handle);
    short usb_tc08_close_unit(short handle);

    // C prototype definition :
    // int16_t (usb_tc08_set_mains) (int16_t handle, int16_t sixty_hertz);
    short usb_tc08_set_mains(short handle, short sixty_hertz);
    
    // C prototype definition :
    // int16_t (usb_tc08_set_channel) (int16_t handle, int16_t channel, int8_t  tc_type);
    short usb_tc08_set_channel(short handle, short channel, byte tc_type);

    // C prototype definition :
    // int32_t (usb_tc08_get_minimum_interval_ms) (int16_t handle);
    int usb_tc08_get_minimum_interval_ms(short handle);
    
    // C prototype definition :
    // int16_t (usb_tc08_get_formatted_info) (int16_t  handle, int8_t  *unit_info, int16_t  string_length);
    short usb_tc08_get_formatted_info(short handle, byte[] unitInfo, short stringLength);
    
    // C prototype definition :
    // int16_t (usb_tc08_get_single) (int16_t handle, float * temp, int16_t * overflow_flags, int16_t units);
    short usb_tc08_get_single(short handle, Memory temp, ShortByReference overflowFlags, short units);
    
    
    // Constants
    // =========
    public static final int USBTC08_MAX_CHANNELS = 8;
    
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_B = (byte) 'B';
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_E = (byte) 'E';
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_J = (byte) 'J';
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_K = (byte) 'K';
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_N = (byte) 'N';
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_R = (byte) 'R';
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_S = (byte) 'S';
    public static final byte USB_TC08_THERMOCOUPLE_TYPE_T = (byte) 'T';
    public static final byte USB_TC08_VOLTAGE_READINGS    = (byte) 'X';
    public static final byte USB_TC08_DISABLE_CHANNEL     = (byte) ' ';
    
    
    // Enumerations
    // ============

    public enum USBTC08Channels
    {
        USBTC08_CHANNEL_CJC,
        USBTC08_CHANNEL_1,
        USBTC08_CHANNEL_2,
        USBTC08_CHANNEL_3,
        USBTC08_CHANNEL_4,
        USBTC08_CHANNEL_5,
        USBTC08_CHANNEL_6,
        USBTC08_CHANNEL_7,
        USBTC08_CHANNEL_8;
    }
    
    public enum USBTC08Units
    {
        USBTC08_UNITS_CENTIGRADE,
        USBTC08_UNITS_FAHRENHEIT,
        USBTC08_UNITS_KELVIN,
        USBTC08_UNITS_RANKINE;
    }
    
    public enum USBTC08MainsFrequency
    {
        USBTC08_FIFTY_HERTZ,
        USBTC08_SIXTY_HERTZ;
    }
}
    
