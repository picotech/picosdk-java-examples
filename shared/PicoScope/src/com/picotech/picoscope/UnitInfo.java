/*******************************************************************************
 *
 * Filename:    UnitInfo.java
 *
 * Author:      HSM
 *
 * Description:
 *      Object to store information about a PicoScope oscilloscope or 
 *      PicoLog data logger.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope;

/**
 *
 */
public class UnitInfo 
{
    private String driverVersion;
    private String usbVersion;
    private String hardwareVersion;
    private String variantInfo;
    private String batchAndSerial;
    private String calibrationDate;
    private String kernelVersion;
    private String digitalHardwareVersion;
    private String analogueHardwareVersion;
    
    
    /**
     * 
     * @param driver_version
     * @param usb_version
     * @param hardware_version
     * @param variant_info
     * @param batch_and_serial
     * @param cal_date
     * @param error_code
     * @param kernel_version
     * @param d_hw_version
     * @param a_hw_version 
     */
    public UnitInfo(String driver_version, String usb_version, 
            String hardware_version, String variant_info, 
            String batch_and_serial, String cal_date, String error_code, 
            String kernel_version, String d_hw_version, String a_hw_version) 
    {
        driverVersion = driver_version;
        usbVersion = usb_version;
        hardwareVersion = hardware_version;
        variantInfo = variant_info;
        batchAndSerial = batch_and_serial;
        calibrationDate = cal_date;
        kernelVersion = kernel_version;
        digitalHardwareVersion = d_hw_version;
        analogueHardwareVersion = a_hw_version;
    }
    
    /**
     * 
     */
    public UnitInfo()
    {
        driverVersion = "";
        usbVersion = "";
        hardwareVersion = "";
        variantInfo = "";
        batchAndSerial = "";
        calibrationDate = "";
        kernelVersion = "";
        digitalHardwareVersion = "";
        analogueHardwareVersion = "";
    }

    /**
     * 
     * @return 
     */
    public String getBatchAndSerial() 
    {
        return batchAndSerial;
    }

    /**
     * 
     * @return 
     */
    public String getDriverVersion() 
    {
        return driverVersion;
    }

    /**
     * 
     * @return 
     */
    public String getHardwareVersion() 
    {
        return hardwareVersion;
    }

    /**
     * 
     * @return 
     */
    public String getUsbVersion() 
    {
        return usbVersion;
    }

    /**
     * 
     * @return 
     */
    public String getVariantInfo() 
    {
        return variantInfo;
    }

    /**
     * 
     * @return 
     */
    public String getAnalogueHardwareVersion() 
    {
        return analogueHardwareVersion;
    }

    /**
     * 
     * @return 
     */
    public String getCalibrationDate() 
    {
        return calibrationDate;
    }

    /**
     * 
     * @return 
     */
    public String getDigitalHardwareVersion() 
    {
        return digitalHardwareVersion;
    }

    /**
     * 
     * @return 
     */
    public String getKernelVersion() 
    {
        return kernelVersion;
    }

    /**
     * 
     * @param a_hw_version 
     */
    public void setAnalogueHardwareVersion(String a_hw_version) 
    {
        analogueHardwareVersion = a_hw_version;
    }

    /**
     * 
     * @param batch_and_serial 
     */
    public void setBatchAndSerial(String batch_and_serial) 
    {
        batchAndSerial = batch_and_serial;
    }

    /**
     * 
     * @param calibration_date 
     */
    public void setCalibrationDate(String calibration_date) 
    {
        calibrationDate = calibration_date;
    }

    /**
     * 
     * @param d_hw_version 
     */
    public void setDigitalHardwareVersion(String d_hw_version) 
    {
        digitalHardwareVersion = d_hw_version;
    }

    /**
     * 
     * @param driver_version 
     */
    public void setDriverVersion(String driver_version) 
    {
        driverVersion = driver_version;
    }

    /**
     * 
     * @param hardware_version 
     */
    public void setHardwareVersion(String hardware_version) 
    {
        hardwareVersion = hardware_version;
    }

    /**
     * 
     * @param kernel_version 
     */
    public void setKernelVersion(String kernel_version) 
    {
        kernelVersion = kernel_version;
    }

    /**
     * 
     * @param usb_version 
     */
    public void setUsbVersion(String usb_version) 
    {
        usbVersion = usb_version;
    }

    /**
     * 
     * @param variant_info 
     */
    public void setVariantInfo(String variant_info) 
    {
        variantInfo = variant_info;
    }

}
