/**************************************************************************
 *
 * Filename:    USBPT104CLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions from the usbPT104Api.h C header file 
 * for the USB PT-104 Platinum Resistance Temperature data logger using 
 * the usbpt104 library API functions.
 * 
 * Copyright © 2018 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/
package com.picotech.picolog.usbpt104.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.ptr.IntByReference;

public interface USBPT104CLibrary extends Library
{
    USBPT104CLibrary INSTANCE = (USBPT104CLibrary) Native.loadLibrary
                                ( 
                                    "usbpt104", USBPT104CLibrary.class
                                );
    
    
    // Method definitions from usbPT104Api.h C header file
    // ===================================================
    
    // C prototype definition :
    // PICO_STATUS UsbPt104Enumerate(int8_t *details, uint32_t *length, COMMUNICATION_TYPE type);
    int UsbPt104Enumerate(byte[] serials, IntByReference length, int type); 
    
    // C prototype definition : 
    // PICO_STATUS UsbPt104OpenUnit(int16_t *handle, int8_t *serial);
    int UsbPt104OpenUnit(ShortByReference handle, String serial);
    
    // C prototype definition : 
    // PICO_STATUS UsbPt104OpenUnitViaIp(int16_t *handle, int8_t  *serial, int8_t *ipAddress);
    int UsbPt104OpenUnitViaIp(ShortByReference handle, String serial, String ipAddress);

    // C prototype definition : 
    // PICO_STATUS UsbPt104CloseUnit(int16_t handle);
    int UsbPt104CloseUnit(short handle);

    // C prototype definition : 
    // PICO_STATUS UsbPt104IpDetails(int16_t handle, int16_t  *enabled, 
    // int8_t *ipaddress, uint16_t *length, uint16_t *listeningPort, IP_DETAILS_TYPE type);
    int UsbPt104IpDetails(short handle, ShortByReference enabled, byte[] ipaddress, 
            ShortByReference length, ShortByReference listeningPort, int type);
            
    // C prototype definition : 
    // PICO_STATUS UsbPt104GetUnitInfo(int16_t handle, int8_t *string, int16_t stringLength,
    // int16_t *requiredSize, PICO_INFO info)
    int UsbPt104GetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);

    // C prototype definition : 
    // PICO_STATUS UsbPt104SetChannel(int16_t handle, USBPT104_CHANNELS channel, USBPT104_DATA_TYPES type, int16_t noOfWires);
    int UsbPt104SetChannel(short handle, int channel, int type, short noOfWires);

    // C prototype definition : 
    // PICO_STATUS UsbPt104SetMains(int16_t  handle, uint16_t sixtyHertz);
    int UsbPt104SetMains(short handle, short sixtyHertz);

    // C prototype definition : 
    // PICO_STATUS UsbPt104GetValue(int16_t handle, USBPT104_CHANNELS channel, int32_t *value, int16_t filtered);
    int UsbPt104GetValue(short handle, int channel, IntByReference value, short filtered);
    
    // Constants
    
    public static final int USBPT104_MIN_WIRES = 2;
    public static final int USBPT104_MAX_WIRES = 4;
    
    // Enumerations
    // ============
    

    public enum UsbPt104Channels
    {
        USBPT104_CHANNEL_1(1),
        USBPT104_CHANNEL_2(2),
        USBPT104_CHANNEL_3(3),
        USBPT104_CHANNEL_4(4),
        USBPT104_CHANNEL_5(5),
        USBPT104_CHANNEL_6(6),
        USBPT104_CHANNEL_7(7),
        USBPT104_CHANNEL_8(8),
        USBPT104_MAX_CHANNELS(8);

        private final int channel;
        
        UsbPt104Channels(int channel)
        {
            this.channel = channel;
        }
        
        public int getValue()
        {
            return channel;
        }
    } 

    public enum UsbPt104DataType
    {
        USBPT104_OFF,
        USBPT104_PT100,
        USBPT104_PT1000,
        USBPT104_RESISTANCE_TO_375R,
        USBPT104_RESISTANCE_TO_10K,
        USBPT104_DIFFERENTIAL_TO_115MV,
        USBPT104_DIFFERENTIAL_TO_2500MV,
        USBPT104_SINGLE_ENDED_TO_115MV,
        USBPT104_SINGLE_ENDED_TO_2500MV,
        USBPT104_MAX_DATA_TYPES

    }

    public enum IpDetailsType
    {
      IDT_GET,
      IDT_SET
    }

    public enum CommunicationType
    {
      CT_USB(Integer.parseInt("00000001", 16)),
      CT_ETHERNET(Integer.parseInt("00000002", 16)),
      CT_ALL(Integer.parseUnsignedInt("FFFFFFFF", 16));
    
        private final int communicationType;

        CommunicationType(int communicationType)
        {
            this.communicationType = communicationType;
        }
        
        public int getValue()
        {
            return communicationType;
        }
    }
}
