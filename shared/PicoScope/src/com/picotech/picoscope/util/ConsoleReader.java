/*******************************************************************************
 *
 * Filename:    ConsoleReader.java
 *
 * Author:      HSM
 *
 * Description:
 *      Utility class to assist reading input from a console window.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope.util;

import com.picotech.picoscope.PicoScope;
import java.util.Scanner;

/**
 *
 * 
 */
public class ConsoleReader implements Runnable
{
    private boolean continueReading;
    private boolean inputEntered;
    private Scanner consoleScanner;
    
    private PicoScope picoScope;
    
    /**
     * 
     * @param pico_scope a PicoScope object
     */
    public ConsoleReader(PicoScope pico_scope)
    {
        continueReading = true;
        inputEntered = false;
        consoleScanner = pico_scope.getConsoleScanner();
        
        picoScope = pico_scope;
    }
    
    /**
     * Implements run method. Checks for input - if there is input to
     */
    public void run()
    {
        while(continueReading == true)
        {

            if(consoleScanner.hasNextLine() == true)
            {
                String console_input = consoleScanner.nextLine();

                System.out.println("User entered '" + console_input + 
                        "' to stop listening for input.");

                System.out.println();

                //consoleScanner.nextLine();

                // Ensure break out of loop in thread
                continueReading = false;

                // Indicate if keyboard hit
                inputEntered = true;

                // Signal to main class to stop streaming
                picoScope.setContinueStreaming(false);
            }

            try 
            {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) 
            {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Method to confirm if an input was detected.
     * 
     * NOTE: An input could consist of an empty string if the ENTER key is 
     * pressed without entering any other characters.
     * 
     * @return true if input was detected, false otherwise.
     */
    public synchronized boolean isInputEntered() 
    {
        return inputEntered;
    }

    /**
     * Allows the user to stop the thread if a key has not been pressed.
     * 
     * @param continue_reading Set to false to stop the thread waiting for input 
     * from the Scanner.
     */
    public synchronized void setContinueReading(boolean continue_reading) 
    {
        continueReading = continue_reading;
    }
    
    
    
    
    
}
