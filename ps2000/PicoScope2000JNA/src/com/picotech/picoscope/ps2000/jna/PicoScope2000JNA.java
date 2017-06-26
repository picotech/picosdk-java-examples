/**************************************************************************
 *
 * Filename:    PicoScope2000JNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps2000 library
 * for PicoScope 2000 series oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information]
 *      Setup channels
 *      Setup a trigger
 *      Start the signal generator
 *      Collect a block of data
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *      PicoScope 2104 & 2105 
 *      PicoScope 2202 & 2203 
 *	PicoScope 2204 & 2204A 
 *	PicoScope 2205 & 2205A
 * 
 * Copyright © 2013-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps2000.jna;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.picotech.picoscope.*;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.*;

public class PicoScope2000JNA 
{
    List<String> infoList = new LinkedList<>();
    
    public static final String[] infoDescription = {"Driver Version", "USB Version", "Hardware Version", "Variant", "Batch/Serial", "Cal Date", "Error Code", "Kernel Driver"};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        short status = 0;
        
        // Open device
        System.out.println("Opening PicoScope 2000 Series device ...");
        System.out.println();
        
        short handle = PS2000CLibrary.INSTANCE.ps2000_open_unit();

        System.out.println("Handle: " + handle);
        
        if (handle > 0)
        {
            // Print unit information to console

            byte[] infoBytes = new byte[80];

            short stringLength = 0;

            for (short i = 0; i < infoDescription.length; i ++)
            {
                stringLength = PS2000CLibrary.INSTANCE.ps2000_get_unit_info(handle, infoBytes, (short) infoBytes.length, i);

                if (i != 6)
                {
                    System.out.println(infoDescription[i] + ": \t" + Native.toString(infoBytes));
                }
            }

            System.out.println();
            
            // Set Channels
            
            short channelA = (short) PS2000CLibrary.PS2000Channel.PS2000_CHANNEL_A.ordinal();
            short chARange = (short) PS2000CLibrary.PS2000Range.PS2000_2V.ordinal();
            
            short channelB = (short) PS2000CLibrary.PS2000Channel.PS2000_CHANNEL_B.ordinal();
            short chBRange = (short) PS2000CLibrary.PS2000Range.PS2000_2V.ordinal();
            
            // Channel A: enabled, DC coupling, +/- 2V
            status = PS2000CLibrary.INSTANCE.ps2000_set_channel(handle, channelA, (short) 1, (short) 1, chARange);

            if (status == 0)
            {
                System.err.println("ps2000_set_channel: Error setting channel A.");
                closeDeviceOnError(handle);
            }
            
            // Channel B: off, DC coupling, +/- 2V
            status = PS2000CLibrary.INSTANCE.ps2000_set_channel(handle, channelB, (short) 1, (short) 1, chBRange);
            
            if (status == 0)
            {
                System.err.println("ps2000_set_channel: Error setting channel A.");
                closeDeviceOnError(handle);
            }

            // Setup trigger

            // Set threshold to 500 mv
            short threshold = (short) (500.0 / VoltageDefinitions.SCOPE_INPUT_RANGES_MV[chARange] * PS2000CLibrary.PS2000_MAX_VALUE);
            short direction = (short) PS2000CLibrary.PS2000TriggerDirection.PS2000_RISING.ordinal();
            float delay = -50.0f; // Place trigger in centre of block 
            short autoTriggerMs = 2000; // Wait for 2 seconds (set to 0 to wait indefinitely)
            
            status = PS2000CLibrary.INSTANCE.ps2000_set_trigger2(handle, channelA, threshold, direction, delay, autoTriggerMs);

            if (status == 0)
            {
                System.err.println("ps2000_set_trigger2: Error setting trigger.");
                closeDeviceOnError(handle);
            }
            
            // Preselect the timebase for 25 MS/s 

            short timebase = 2; // 25 MS/s with PicoScope 2204A
            int numberOfSamples = 4096;
            short oversample = 1;
            
            IntByReference timeIntervalIbr = new IntByReference(0);
            ShortByReference timeUnitsSbr = new ShortByReference((short) 0);
            IntByReference maxSamplesIbr = new IntByReference(0);
            
            do
            {
                status = PS2000CLibrary.INSTANCE.ps2000_get_timebase(handle, timebase, numberOfSamples, timeIntervalIbr, timeUnitsSbr, oversample, maxSamplesIbr);
                
                // If invalid timebase is used, increment timebase index
                if(status == 0)
                {
                    timebase++;
                }
            }
            while (status == 0);
            
            System.out.println("Timebase: " + timebase + "\t Time interval: " + timeIntervalIbr.getValue() + " ns \t Max Samples: " + maxSamplesIbr.getValue());
            
            // Start signal generator
            
            int offsetVoltage = 0;
            int pkToPk = 4000000; // 4 Vpp
            int waveType = PS2000CLibrary.PS2000WaveType.PS2000_SINE.ordinal();
            float startFrequency = 20.0f;
            float stopFrequency = 20.0f;
            float increment = 0;
            float dwellTime = 0;
            int sweepType = PS2000CLibrary.PS2000SweepType.PS2000_UP.ordinal();
            int sweeps = 0;
            
            // Prompt User to connect the AWG output to channel A
            System.out.println("Connect AWG output to Channel A and press <ENTER> to continue.");
            
            Scanner consoleScanner = new Scanner(System.in);
            consoleScanner.nextLine();
            
            status = PS2000CLibrary.INSTANCE.ps2000_set_sig_gen_built_in(handle, offsetVoltage, pkToPk, waveType, startFrequency, stopFrequency, increment, dwellTime, sweepType, sweeps);

            if (status == 0)
            {
                System.err.println("ps2000_set_sig_gen_built_in: A parameter is out of range.");
                closeDeviceOnError(handle);
            }
            
            // Start collecting data
            
            IntByReference timeIndisposedMsIbr = new IntByReference(0);
                    
            System.out.println("Starting data collection (waiting for trigger)...");
                    
            status = PS2000CLibrary.INSTANCE.ps2000_run_block(handle, numberOfSamples, timebase, oversample, timeIndisposedMsIbr);
            
            if (status == 0)
            {
                System.err.println("ps2000_run_block: A parameter is out of range.");
                closeDeviceOnError(handle);
            }

            // Poll the driver until the device has completed data collection
            
            short ready = 0;
            
            while (ready == 0)
            {
                ready = PS2000CLibrary.INSTANCE.ps2000_ready(handle);
                
                System.out.print(".");
                
                try
                {
                    Thread.sleep(5);
                }
                catch(InterruptedException ie)
                {
                    ie.printStackTrace();
                    closeDeviceOnError(handle);
                }
            }

            System.out.println();
            
            if (ready > 0)
            {
                System.out.println("Data collection complete.");
                
                // Retrieve data values
                
                Memory timesPointer = new Memory(numberOfSamples * Native.getNativeSize(Integer.TYPE));
                Memory chABufferPointer = new Memory(numberOfSamples * Native.getNativeSize(Short.TYPE));
                Memory chBBufferPointer = new Memory(numberOfSamples * Native.getNativeSize(Short.TYPE));
                
                ShortByReference overflowSbr = new ShortByReference((short) 0);

                int numberOfSamplesCollected = PS2000CLibrary.INSTANCE.ps2000_get_times_and_values(handle, timesPointer, chABufferPointer, null, 
                        null, null, overflowSbr, timeUnitsSbr.getValue(), numberOfSamples);
                
                if (numberOfSamplesCollected > 0)
                {
                    System.out.println("Collected " + numberOfSamples + " samples.");
                    System.out.println();
                    
                    // Process data...
                    // e.g. convert ADC counts to millivolts
                    
                    int times[] = timesPointer.getIntArray(0, numberOfSamplesCollected);
                    short[] chAData = chABufferPointer.getShortArray(0, numberOfSamplesCollected);
                    
                    System.out.println("Time\t ChA (ADC Counts)");
                    
                    for(int i = 0; i < numberOfSamplesCollected; i++)
                    {
                        System.out.println(times[i] + ",\t" + chAData[i]);
                    }
                }
                else
                {
                    System.err.println("ps2000_get_times_and_values: No samples collected.");
                }
                

                // Stop the oscilloscopes
                status = PS2000CLibrary.INSTANCE.ps2000_stop(handle);
                
                // Close the unit
                PS2000CLibrary.INSTANCE.ps2000_close_unit(handle);
                
                System.out.println("\nExiting application.");
            }
        }
        else
        {
            System.err.println("Unable to open device.");
            System.exit(-1);
        }
        
    }
    
    private static void closeDeviceOnError(short handle)
    {
        PS2000CLibrary.INSTANCE.ps2000_close_unit(handle);
        System.exit(-1);
    }
    
}
