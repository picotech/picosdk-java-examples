/*******************************************************************************
 *
 * Filename:    UnitModel.java
 *
 * Author:      HSM
 *
 * Description:
 *      Class to hold information on a PicoScope unit.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;


/**
 * This class holds information on a PicoScope Unit
 * 
 */
public class UnitModel 
{
    ////////////////////
    //
    // Variables
    //
    ////////////////////
    
    private short handle;
    
    private Enum modelType;
    private Enum firstRange;
    private Enum lastRange;
    
    private TriggerChannel triggerChannel;
    
    private short maxTimebase;
    private short timebases;
    private short channelCount;
    
    private ChannelSettings[] channelSettings;
    
    private boolean hasFastStreaming;
    private boolean hasEts;
    private boolean hasSignalGenerator;

    private boolean hasAdvancedTriggering;
    
    private UnitInfo unitInfo;
    
    ////////////////////
    //
    // Constructor
    //
    ////////////////////
    
    /**
     * Constructor - creates new object to hold information on the Unit
     * 
     * @param unit_handle 
     */
    public UnitModel(short unit_handle) 
    {
        handle = unit_handle;
        
        triggerChannel = new TriggerChannel();
    }
    
    /**
     * Constructor - creates new object to hold information on the Unit
     * 
     *  
     */
    public UnitModel() 
    {
       triggerChannel = new TriggerChannel(); 
    }
    
    
    ///////////////////////
    //
    // Getter and Setters
    //
    ///////////////////////
    public short getHandle() 
    {
        return handle;
    }

    public void setHandle(short unit_handle) 
    {
        handle = unit_handle;
    }

    public Enum getFirstRange() 
    {
        return firstRange;
    }

    public void setFirstRange(Enum first_range) 
    {
        firstRange = first_range;
    }

    public Enum getLastRange() 
    {
        return lastRange;
    }

    public void setLastRange(Enum last_range) 
    {
        lastRange = last_range;
    }

    public Enum getModelType() 
    {
        return modelType;
    }

    public void setModelType(Enum model_type) 
    {
        modelType = model_type;
    }

    public TriggerChannel getTriggerChannel() 
    {
        return triggerChannel;
    }

    public void setTriggerChannel(TriggerChannel trigger_channel) 
    {
        triggerChannel = trigger_channel;
    }
    
    

    public boolean getHasAdvancedTriggering() 
    {
        return hasAdvancedTriggering;
    }

    public void setHasAdvancedTriggering(boolean has_advanced_triggering) 
    {
        hasAdvancedTriggering = has_advanced_triggering;
    }

    public boolean getHasEts() 
    {
        return hasEts;
    }

    public void setHasEts(boolean has_ets) 
    {
        hasEts = has_ets;
    }

    public boolean getHasFastStreaming() 
    {
        return hasFastStreaming;
    }

    public void setHasFastStreaming(boolean has_fast_streaming) 
    {
        hasFastStreaming = has_fast_streaming;
    }

    public boolean getHasSignalGenerator() 
    {
        return hasSignalGenerator;
    }

    public void setHasSignalGenerator(boolean has_signal_generator) 
    {
        hasSignalGenerator = has_signal_generator;
    }

    public short getMaxTimebase() 
    {
        return maxTimebase;
    }

    public void setMaxTimebase(short max_timebase) 
    {
        maxTimebase = max_timebase;
    }

    public short getChannelCount() 
    {
        return channelCount;
    }

    public void setChannelCount(short channel_count) 
    {
        channelCount = channel_count;
    }

    public short getTimebases() 
    {
        return timebases;
    }

    public void setTimebases(short unit_timebases) 
    {
        timebases = unit_timebases;
    }

    public ChannelSettings[] getChannelSettings() 
    {
        return channelSettings;
    }

    public void setChannelSettings(ChannelSettings[] channel_settings) 
    {
        channelSettings = channel_settings;
    }

    public UnitInfo getUnitInfo() 
    {
        return unitInfo;
    }

    public void setUnitInfo(UnitInfo unit_info) 
    {
        unitInfo = unit_info;
    }
      
}

