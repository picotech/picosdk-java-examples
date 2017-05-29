/*******************************************************************************
 *
 * Filename:    AdcMvConverter.java
 *
 * Author:      HSM
 *
 * Description:
 *      Utility class to assist with conversion between ADC count values 
 *      and voltages.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope.util;

/**
 *
 * @author hitesh
 */
public class AdcMvConverter 
{

    /**
     * Converts a numerical value into a string representation of the units
     * for data values.
     * 
     * Time unit to string representation mapping:
     * 
     * Time Unit    Representation
     * 0            ADC
     * 1            fs
     * 2            ps 
     * 3            ns
     * 4            us
     * 5            ms
     * 
     * @param time_units
     * @return "ADC" if time_units is 0, a two character representation of the   
     */
    public static String adc_units (short time_units)
    {

        time_units = (short) (time_units + 1);

        //System.out.println("Time unit: " + time_units) ;

        switch (time_units)
        {
            case 0:
                return "ADC";
            case 1:
                return "fs";
            case 2:
                return "ps";
            case 3:
                return "ns";
            case 4:
                return "us";
            case 5:
                return "ms";
        }

        return "Not Known";
    }

//
///****************************************************************************
// * adc_to_mv
// *
// * If the user selects scaling to millivolts,
// * Convert an 12-bit ADC count into millivolts
// ****************************************************************************/
    
    /**
     * Converts an Analogue to Digital Converter count value to milliVolts.
     * 
     * Note: As ADC counts are 16-bit values, the Short.MAX_VALUE (32767) is used
     * to calculated the value in milliVolts.
     * 
     * @param raw the raw ADC count value
     * @param input_range the voltage range value from the PicoScope's set of supported voltage ranges.
     * @param scale_to_mv set true if values are to be scaled to millivolts, false otherwise
     * @return the data value in millivolts
     */
    public static int adc_to_mv (int raw, int input_range, boolean scale_to_mv)
    {
        if(scale_to_mv == true)
        {
            return (raw * input_range / Short.MAX_VALUE);
        }
        else
        {
            return raw;
        }
    }

    /**
     * Convert a milliVolt value into a 16-bit ADC count
     * (useful for setting trigger thresholds)
     * 
     * 
     * @param mv the voltage value (in milliVolts)
     * @param input_range the voltage range value from the PicoScope's set of supported voltage ranges. 
     * 
     * @return 
     */
    public static short mv_to_adc(short mv, int input_range)
    {
        return (short) ( ( mv * Short.MAX_VALUE ) / input_range );
    }
}
