/*******************************************************************************
 *
 * Filename:    AbortButtonJFrameRunnable.java
 *
 * Author:      HSM
 *
 * Description:
 *      Helper class to start AbortButtonJFrame instance.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope.util;

import com.picotech.picoscope.PicoScope;

/**
 * Helper class to start AbortButtonJFrame instance.
 *
 */
public class AbortButtonJFrameRunnable implements Runnable
{
    AbortButtonJFrame abortButtonJFrame;
    PicoScope picoScope;

    /**
     * 
     * @param pico_scope 
     */
    public AbortButtonJFrameRunnable(PicoScope pico_scope) 
    {
        picoScope = pico_scope;
        abortButtonJFrame = new AbortButtonJFrame(picoScope);
        picoScope.setAbortButtonPressed(false);
    }
    
    public void run()
    {
        abortButtonJFrame.setVisible(true);
    }

    /**
     * 
     * @return 
     */
    public AbortButtonJFrame getAbortButtonJFrame() 
    {
        return abortButtonJFrame;
    }
    
    
}
