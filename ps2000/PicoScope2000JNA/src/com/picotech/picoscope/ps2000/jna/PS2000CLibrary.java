/**************************************************************************
 *
 * Filename:    PS2000CLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions and enumerations from the ps2000.h C 
 * header file for PicoScope 2000 series oscilloscopes using the ps2000 
 * driver API functions.
 * 
 * Copyright © 2013-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps2000.jna;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;

/**
 *
 * 
 */
public interface PS2000CLibrary extends Library
{
	
	PS2000CLibrary INSTANCE = (PS2000CLibrary) Native.loadLibrary
                                    (
                                        "ps2000", PS2000CLibrary.class
                                    );
	
        
        // Method definitions from ps2000.h C header file
        // ==============================================
        
	// C prototype definition : 
	// int16_t ps2000_open_unit()	
        
        short ps2000_open_unit();
	
	// C prototype definition
	// int16_t ps2000_close_unit(int16_t handle);
	short ps2000_close_unit(short handle);
	
        // C prototype definition:
        //uint32_t ps2000_get_unit_info (int16_t handle, int8_t *string, int16_t stringLength, int16_t *requiredSize, PICO_INFO info)
        short ps2000_get_unit_info(short handle, byte[] string, short stringLength, short info);
        
        // C prototype definition: 
	// int16_t ps2000_open_unit_async()	
        
        short ps2000_open_unit_async();
        
        // C prototype definition:
        // int16_t (ps2000_open_unit_progress) (int16_t *handle, int16_t *progress_percent)
        short ps2000_open_unit_progress(ShortByReference handle, ShortByReference progressPercent);
        
        // C prototype definition:
        // int16_t (ps2000_set_channel) (int16_t  handle, int16_t channel, int16_t enabled, int16_t dc, int16_t range)
        short ps2000_set_channel(short handle, short channel, short enabled, short dc, short range);
        
        // C prototype definition:
        // int16_t (ps2000_get_timebase) (int16_t  handle, int16_t  timebase, int32_t  no_of_samples, int32_t *time_interval, int16_t *time_units, int16_t  oversample, int32_t *max_samples)
        short ps2000_get_timebase(short handle, short timebase, int no_of_samples, IntByReference time_interval, ShortByReference time_units, short oversample, IntByReference max_samples);
        
        // C prototype definition:
        // int16_t (ps2000_set_trigger) (int16_t handle, int16_t source, int16_t threshold, int16_t direction, int16_t delay, int16_t  auto_trigger_ms)
        short ps2000_set_trigger(short handle, short source, short threshold, short direction, short delay, short auto_trigger_ms);
        
        // C prototype definition:
        // int16_t (ps2000_set_trigger2) (int16_t handle, int16_t source, int16_t threshold, int16_t direction, float delay, int16_t  auto_trigger_ms)
        short ps2000_set_trigger2(short handle, short source, short threshold, short direction, float delay, short auto_trigger_ms);
        
        // C prototype definition:
        // int16_t (ps2000_run_block) (int16_t handle, int32_t no_of_values, int16_t timebase, int16_t oversample, int32_t * time_indisposed_ms)
        short ps2000_run_block(short handle, int no_of_values, short timebase, short oversample, IntByReference time_indisposed_ms);
        
        // C prototype definition:
        // int16_t (ps2000_ready) (int16_t handle)
        short ps2000_ready(short handle); 
        
        // C prototype definition:
        // int16_t (ps2000_stop) (int16_t handle)
        short ps2000_stop(short handle); 
        
        // C prototype definition:
        // int32_t (ps2000_get_values) (int16_t  handle, int16_t *buffer_a, int16_t *buffer_b, int16_t *buffer_c, int16_t *buffer_d, int16_t *overflow, int32_t no_of_values)
        int ps2000_get_values(short handle, Memory buffer_a, Memory buffer_b, Memory buffer_c, Memory buffer_d, ShortByReference overflow, int no_of_values);
        
        // C prototype definition:
        // int32_t (ps2000_get_times_and_values) (int16_t handle, int32_t times, int16_t *buffer_a, int16_t *buffer_b, int16_t *buffer_c, int16_t *buffer_d, int16_t *overflow, int16_t time_units, int32_t no_of_values)
        int ps2000_get_times_and_values(short handle, Memory times, Memory buffer_a, Memory buffer_b, Memory buffer_c, Memory buffer_d, ShortByReference overflow, short time_units, int no_of_values);
        
        // C prototype definition:
        // short (ps2000_set_sig_gen_built_in) (int16_t handle, int32_t offsetVoltage, uint32_t pkToPk, PS2000_WAVE_TYPE waveType, float startFrequency, float stopFrequency,
        //      float increment, float dwellTime, PS2000_SWEEP_TYPE sweepType, uint32_t sweeps)
        short ps2000_set_sig_gen_built_in (short handle, int offsetVoltage, int pkToPk, int waveType, float startFrequency, float stopFrequency, float increment, float dwellTime, int sweepType, int sweeps);
        
        // End of Method definitions
        
        
        // Constants
        // =========
        
        public final static int PS2000_MAX_VALUE = 32767;
        public final static int PS2000_MIN_VALUE = -32767;
        public final static int PS2000_LOST_DATA = -32768;
        
        // Enumerations
        // ============

        public enum PS2000Channel
        {
            PS2000_CHANNEL_A (0),
            PS2000_CHANNEL_B (1),
            PS2000_CHANNEL_C (2),
            PS2000_CHANNEL_D (3),
            PS2000_EXTERNAL (4),
            PS2000_MAX_CHANNELS (4),
            PS2000_NONE (5);

            private final int channel;

            PS2000Channel(int channel)
            {
                this.channel = channel;
            }
            
            
        }
        
        public enum PS2000Range
        {
            PS2000_10MV,
            PS2000_20MV,
            PS2000_50MV,
            PS2000_100MV,
            PS2000_200MV,
            PS2000_500MV,
            PS2000_1V,
            PS2000_2V,
            PS2000_5V,
            PS2000_10V,
            PS2000_20V,
            PS2000_50V,
            PS2000_MAX_RANGES;
        }
        
        public enum PS2000TimeUnits
        {
            PS2000_FS,
            PS2000_PS,
            PS2000_NS,
            PS2000_US,
            PS2000_MS,
            PS2000_S,
            PS2000_MAX_TIME_UNITS;
        }
        
        public enum PS2000Error
        {
            PS2000_OK,
            PS2000_MAX_UNITS_OPENED,  // More than PS2000_MAX_UNITS
            PS2000_MEM_FAIL,          // Not enough RAM on host machine
            PS2000_NOT_FOUND,         // Cannot find device
            PS2000_FW_FAIL,           // Unable to download firmware
            PS2000_NOT_RESPONDING,
            PS2000_CONFIG_FAIL,       // Missing or corrupted configuration settings
            PS2000_OS_NOT_SUPPORTED,  // Need to use win98SE (or later) or win2k (or later)
            PS2000_PICOPP_TOO_OLD;
        }
        
        public enum PS2000TriggerDirection
        {
            PS2000_RISING,
            PS2000_FALLING,
            PS2000_MAX_DIRS;
        }
        
        public enum PS2000SweepType
        {
            PS2000_UP,
            PS2000_DOWN,
            PS2000_UPDOWN,
            PS2000_DOWNUP,
            MAX_SWEEP_TYPES;
        }
        
        public enum PS2000WaveType
        {
            PS2000_SINE,
            PS2000_SQUARE,
            PS2000_TRIANGLE,
            PS2000_RAMPUP,
            PS2000_RAMPDOWN,
            PS2000_DC_VOLTAGE,
            PS2000_GAUSSIAN,
            PS2000_SINC,
            PS2000_HALF_SINE;
        }
}  
