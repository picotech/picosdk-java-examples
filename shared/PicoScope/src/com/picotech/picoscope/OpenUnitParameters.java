/*******************************************************************************
 *
 * Filename:    OpenUnitParameters.java
 *
 * Author:      HSM
 *
 * Description:
 *      This class provides parameters for obtaining the handle of a unit and 
 *      sending a specified serial number to the library.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 * This class provides parameters for obtaining the handle of a unit and 
 * sending a specified serial number to the library.
 * 
 */
public class OpenUnitParameters 
{
    private short handle;
    private String serial;

    
    /**
     * Constructs an object with the serial number specified.
     * 
     * @param unit_serial 
     */
    public OpenUnitParameters(String unit_serial) 
    {
        serial = unit_serial;
    }
    
    /**
     * Constructs an object with a null String where the serial number has not 
     * been specified.
     * 
     */
    public OpenUnitParameters()
    {
        serial = null;
    }

    public short getHandle() 
    {
        return handle;
    }

    public void setHandle(short ouHandle) 
    {
        handle = ouHandle;
    }

    public String getSerial() 
    {
        return serial;
    }

    public void setSerial(String ouSerial) 
    {
        serial = ouSerial;
    }
    
    
    
}
