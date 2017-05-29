/*******************************************************************************
 *
 * Filename:    TriggerChannel.java
 *
 * Author:      HSM
 *
 * Description:
 *      Object to hold simple and advanced trigger objects.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *
 */
public class TriggerChannel 
{
    private SimpleTrigger simpleTrigger;
    private AdvancedTrigger advancedTrigger;

    /**
     * 
     * @param simple_trigger
     * @param advanced_trigger 
     */
    public TriggerChannel(SimpleTrigger simple_trigger, 
            AdvancedTrigger advanced_trigger) 
    {
        simpleTrigger = simple_trigger;
        advancedTrigger = advanced_trigger;
    }
    
    /**
     * Empty constructor
     */
    public TriggerChannel()
    {
        // Do nothing
    }

    /**
     * 
     * @return 
     */
    public AdvancedTrigger getAdvancedTrigger() 
    {
        return advancedTrigger;
    }
    
    /**
     * 
     * @param advanced_trigger 
     */
    public void setAdvancedTrigger(AdvancedTrigger advanced_trigger)
    {
        advancedTrigger = advanced_trigger;
    }

    /**
     * 
     * @return 
     */
    public SimpleTrigger getSimpleTrigger() 
    {
        return simpleTrigger;
    }

    public void setSimpleTrigger(SimpleTrigger simple_trigger) 
    {
        simpleTrigger = simple_trigger;
    }
    
    
    
    
}
