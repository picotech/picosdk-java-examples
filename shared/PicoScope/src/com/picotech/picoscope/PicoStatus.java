/*******************************************************************************
 *
 * Filename:    PicoStatus.java
 *
 * Author:      HSM
 *
 * Description:
 *      PicoStatus contains a set of constants used to define the status codes 
 *      listed in the PicoStatus.h file.
 *
 * Copyright (C) 2014 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *  PicoStatus contains a set of constants used to define the error codes 
 *  listed in the PicoStatus.h file.
 */
public final class PicoStatus 
{
    public final static int PICO_OK                             = Integer.parseInt("0", 16);
    public final static int PICO_MAX_UNITS_OPENED               = Integer.parseInt("1", 16);
    public final static int PICO_MEMORY_FAIL                    = Integer.parseInt("2", 16);
    public final static int PICO_NOT_FOUND                      = Integer.parseInt("3", 16);
    public final static int PICO_FW_FAIL                        = Integer.parseInt("4", 16);		
    public final static int PICO_OPEN_OPERATION_IN_PROGRESS     = Integer.parseInt("5", 16);
    public final static int PICO_OPERATION_FAILED               = Integer.parseInt("6", 16);
    public final static int PICO_NOT_RESPONDING                 = Integer.parseInt("7", 16);	
    public final static int PICO_CONFIG_FAIL                    = Integer.parseInt("8", 16);	
    public final static int PICO_KERNEL_DRIVER_TOO_OLD          = Integer.parseInt("9", 16);	
    public final static int PICO_EEPROM_CORRUPT                 = Integer.parseInt("A", 16);	
    public final static int PICO_OS_NOT_SUPPORTED               = Integer.parseInt("B", 16);	
    public final static int PICO_INVALID_HANDLE                 = Integer.parseInt("C", 16); 		
    public final static int PICO_INVALID_PARAMETER              = Integer.parseInt("D", 16);				
    public final static int PICO_INVALID_TIMEBASE               = Integer.parseInt("E", 16);				
    public final static int PICO_INVALID_VOLTAGE_RANGE          = Integer.parseInt("F", 16);		
    public final static int PICO_INVALID_CHANNEL                = Integer.parseInt("10", 16);	
    public final static int PICO_INVALID_TRIGGER_CHANNEL        = Integer.parseInt("11", 16);
    public final static int PICO_INVALID_CONDITION_CHANNEL      = Integer.parseInt("12", 16);
    public final static int PICO_NO_SIGNAL_GENERATOR            = Integer.parseInt("13", 16);				
    public final static int PICO_STREAMING_FAILED               = Integer.parseInt("14", 16);				
    public final static int PICO_BLOCK_MODE_FAILED              = Integer.parseInt("15", 16);				
    public final static int PICO_NULL_PARAMETER                 = Integer.parseInt("16", 16);				
    public final static int PICO_ETS_MODE_SET                   = Integer.parseInt("17", 16);				
    public final static int PICO_DATA_NOT_AVAILABLE             = Integer.parseInt("18", 16);				
    public final static int PICO_STRING_BUFFER_TO_SMALL         = Integer.parseInt("19", 16);				
    public final static int PICO_ETS_NOT_SUPPORTED              = Integer.parseInt("1A", 16);				
    public final static int PICO_AUTO_TRIGGER_TIME_TO_SHORT     = Integer.parseInt("1B", 16);				
    public final static int PICO_BUFFER_STALL                   = Integer.parseInt("1C", 16);				
    public final static int PICO_TOO_MANY_SAMPLES               = Integer.parseInt("1D", 16);	
    public final static int PICO_TOO_MANY_SEGMENTS              = Integer.parseInt("1E", 16);	
    public final static int PICO_PULSE_WIDTH_QUALIFIER          = Integer.parseInt("1F", 16);	
    public final static int PICO_DELAY                          = Integer.parseInt("20", 16);	
    public final static int PICO_SOURCE_DETAILS                 = Integer.parseInt("21", 16);	
    public final static int PICO_CONDITIONS                     = Integer.parseInt("22", 16);	
    public final static int PICO_USER_CALLBACK                  = Integer.parseInt("23", 16);	
    public final static int PICO_DEVICE_SAMPLING                = Integer.parseInt("24", 16);	
    public final static int PICO_NO_SAMPLES_AVAILABLE           = Integer.parseInt("25", 16);	
    public final static int PICO_SEGMENT_OUT_OF_RANGE           = Integer.parseInt("26", 16);	
    public final static int PICO_BUSY                           = Integer.parseInt("27", 16);	
    public final static int PICO_STARTINDEX_INVALID             = Integer.parseInt("28", 16);	
    public final static int PICO_INVALID_INFO                   = Integer.parseInt("29", 16);	
    public final static int PICO_INFO_UNAVAILABLE               = Integer.parseInt("2A", 16); 
    public final static int PICO_INVALID_SAMPLE_INTERVAL        = Integer.parseInt("2B", 16);
    public final static int PICO_TRIGGER_ERROR                  = Integer.parseInt("2C", 16);
    public final static int PICO_MEMORY                         = Integer.parseInt("2D", 16);
    public final static int PICO_SIG_GEN_PARAM                  = Integer.parseInt("2E", 16);
    public final static int PICO_SHOTS_SWEEPS_WARNING           = Integer.parseInt("2F", 16);
    public final static int PICO_SIGGEN_TRIGGER_SOURCE 		= Integer.parseInt("30", 16);
    public final static int PICO_AUX_OUTPUT_CONFLICT            = Integer.parseInt("31", 16);
    public final static int PICO_AUX_OUTPUT_ETS_CONFLICT        = Integer.parseInt("32", 16);
    public final static int PICO_WARNING_EXT_THRESHOLD_CONFLICT = Integer.parseInt("33", 16);
    public final static int PICO_WARNING_AUX_OUTPUT_CONFLICT    = Integer.parseInt("34", 16);
    public final static int PICO_SIGGEN_OUTPUT_OVER_VOLTAGE     = Integer.parseInt("35", 16);
    public final static int PICO_DELAY_NULL			= Integer.parseInt("36", 16);
    public final static int PICO_INVALID_BUFFER                 = Integer.parseInt("37", 16);
    public final static int PICO_SIGGEN_OFFSET_VOLTAGE 		= Integer.parseInt("38", 16);
    public final static int PICO_SIGGEN_PK_TO_PK                = Integer.parseInt("39", 16);
    public final static int PICO_CANCELLED			= Integer.parseInt("3A", 16);
    public final static int PICO_SEGMENT_NOT_USED               = Integer.parseInt("3B", 16);
    public final static int PICO_INVALID_CALL			= Integer.parseInt("3C", 16);
    public final static int PICO_GET_VALUES_INTERRUPTED 	= Integer.parseInt("3D", 16);
    public final static int PICO_NOT_USED			= Integer.parseInt("3F", 16);
    public final static int PICO_INVALID_SAMPLERATIO 		= Integer.parseInt("40", 16);
    // Operation could not be carried out because device was in an invalid state.
    public final static int PICO_INVALID_STATE                  = Integer.parseInt("41", 16);
    // Operation could not be carried out as rapid capture no of waveforms are greater than the 
    // no of memory segments.
    public final static int PICO_NOT_ENOUGH_SEGMENTS 		= Integer.parseInt("42", 16);
    // A driver function has already been called and not yet finished
    // only one call to the driver can be made at any one time
    public final static int PICO_DRIVER_FUNCTION                = Integer.parseInt("43", 16);
    public final static int PICO_RESERVED			= Integer.parseInt("44", 16);
    public final static int PICO_INVALID_COUPLING               = Integer.parseInt("45", 16);
    public final static int PICO_BUFFERS_NOT_SET                = Integer.parseInt("46", 16);
    public final static int PICO_RATIO_MODE_NOT_SUPPORTED 	= Integer.parseInt("47", 16);
    public final static int PICO_RAPID_NOT_SUPPORT_AGGREGATION	= Integer.parseInt("48", 16);
    public final static int PICO_INVALID_TRIGGER_PROPERTY 	= Integer.parseInt("49", 16);
    public final static int PICO_INTERFACE_NOT_CONNECTED 	= Integer.parseInt("4A", 16);
    public final static int PICO_RESISTANCE_AND_PROBE_NOT_ALLOWED = Integer.parseInt("4B", 16);
    public final static int PICO_POWER_FAILED			= Integer.parseInt("4C", 16);
    public final static int PICO_SIGGEN_WAVEFORM_SETUP_FAILED	= Integer.parseInt("4D", 16);
    public final static int PICO_FPGA_FAIL			= Integer.parseInt("4E", 16);
    public final static int PICO_POWER_MANAGER                  = Integer.parseInt("4F", 16);
    public final static int PICO_INVALID_ANALOGUE_OFFSET 	= Integer.parseInt("50", 16);
    // unable to configure the ps6000
    public final static int PICO_PLL_LOCK_FAILED                = Integer.parseInt("51", 16);
    // the ps6000 Analog board is not detectly connected
    //to the digital board
    public final static int PICO_ANALOG_BOARD			= Integer.parseInt("52", 16);					
    // unable to configure the Signal Generator
    public final static int PICO_CONFIG_FAIL_AWG                = Integer.parseInt("53", 16);
    public final static int PICO_INITIALISE_FPGA                = Integer.parseInt("54", 16);
    public final static int PICO_EXTERNAL_FREQUENCY_INVALID     = Integer.parseInt("56", 16);
    public final static int PICO_CLOCK_CHANGE_ERROR             = Integer.parseInt("57", 16);
    public final static int PICO_TRIGGER_AND_EXTERNAL_CLOCK_CLASH = Integer.parseInt("58", 16);
    public final static int PICO_PWQ_AND_EXTERNAL_CLOCK_CLASH   = Integer.parseInt("59", 16);
    public final static int PICO_UNABLE_TO_OPEN_SCALING_FILE	= Integer.parseInt("5A", 16);
    public final static int PICO_MEMORY_CLOCK_FREQUENCY 	= Integer.parseInt("5B", 16);
    public final static int PICO_I2C_NOT_RESPONDING             = Integer.parseInt("5C", 16);
    public final static int PICO_NO_CAPTURES_AVAILABLE 		= Integer.parseInt("5D", 16);
    public final static int PICO_NOT_USED_IN_THIS_CAPTURE_MODE	= Integer.parseInt("5E", 16);

    public final static int PICO_GET_DATA_ACTIVE                        = Integer.parseInt("103", 16);
    // used by the PT104 (USB) when connected via the Network Socket
    public final static int PICO_IP_NETWORKED                           = Integer.parseInt("104", 16);
    public final static int PICO_INVALID_IP_ADDRESS                     = Integer.parseInt("105", 16);
    public final static int PICO_IPSOCKET_FAILED                        = Integer.parseInt("106", 16);
    public final static int PICO_IPSOCKET_TIMEDOUT                      = Integer.parseInt("107", 16);
    public final static int PICO_SETTINGS_FAILED                        = Integer.parseInt("108", 16);
    public final static int PICO_NETWORK_FAILED                         = Integer.parseInt("109", 16);
    public final static int PICO_WS2_32_DLL_NOT_LOADED                  = Integer.parseInt("10A", 16);	
    public final static int PICO_INVALID_IP_PORT                        = Integer.parseInt("10B", 16);
    public final static int PICO_COUPLING_NOT_SUPPORTED                 = Integer.parseInt("10C", 16);
    public final static int PICO_BANDWIDTH_NOT_SUPPORTED                = Integer.parseInt("10D", 16);
    public final static int PICO_INVALID_BANDWIDTH                      = Integer.parseInt("10E", 16);
    public final static int PICO_AWG_NOT_SUPPORTED                      = Integer.parseInt("10F", 16);
    public final static int PICO_ETS_NOT_RUNNING                        = Integer.parseInt("110", 16);
    public final static int PICO_SIG_GEN_WHITENOISE_NOT_SUPPORTED       = Integer.parseInt("111", 16);
    public final static int PICO_SIG_GEN_WAVETYPE_NOT_SUPPORTED         = Integer.parseInt("112", 16);
    public final static int PICO_INVALID_DIGITAL_PORT                   = Integer.parseInt("113", 16);
    public final static int PICO_INVALID_DIGITAL_CHANNEL                = Integer.parseInt("114", 16);
    public final static int PICO_INVALID_DIGITAL_TRIGGER_DIRECTION      = Integer.parseInt("115", 16);
    public final static int PICO_SIG_GEN_PRBS_NOT_SUPPORTED             = Integer.parseInt("116", 16);
    public final static int PICO_ETS_NOT_AVAILABLE_WITH_LOGIC_CHANNELS  = Integer.parseInt("117", 16);
    public final static int PICO_WARNING_REPEAT_VALUE 			= Integer.parseInt("118", 16);
    public final static int PICO_POWER_SUPPLY_CONNECTED                 = Integer.parseInt("119", 16);
    public final static int PICO_POWER_SUPPLY_NOT_CONNECTED             = Integer.parseInt("11A", 16);    
    public final static int PICO_POWER_SUPPLY_REQUEST_INVALID           = Integer.parseInt("11B", 16);
    public final static int PICO_POWER_SUPPLY_UNDERVOLTAGE              = Integer.parseInt("11C", 16);
    public final static int PICO_CAPTURING_DATA                         = Integer.parseInt("11D", 16);
    public final static int PICO_USB3_0_DEVICE_NON_USB3_0_PORT          = Integer.parseInt("11E", 16);
    
    public final static int PICO_WATCHDOGTIMER                          = Integer.parseInt("10000000", 16);
}
