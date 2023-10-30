package com.tecnocoli.ems50.device;

import android.bluetooth.BluetoothDevice;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeferson Coli on 10/26/23.
 * Tecnocoli
 * jcoli@tecnocoli.com.br
 */
public class EMSDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private BluetoothDevice rmDevice;
    private BufferedReader in;
    private PrintWriter out;
    private String partnerName;
    private String addressDevice;
    private String friendlyName;
    private Boolean connectedDevice;
    private Float batteryLevel;
    private Float batteryCel1Level;
    private Float batteryCel2Level;
    private Float temperatureLevel;
    private String typeLastUsed;
    private Boolean connected;
    private LocalDateTime lastDateUsed;
    private LocalDateTime strongLastDateUsed;
//   private ReceiveMsg receiveMsg;
    private List<Channel> channels = new ArrayList<>();
//    private DeviceConnection deviceConnection;
    private Training training;
    private Boolean deviceRunning;

    public EMSDevice() {
    }

    public EMSDevice(Integer id, String partnerName, String addressDevice, String friendlyName) {
        this.id = id;
        this.partnerName = partnerName;
        this.addressDevice = addressDevice;
        this.friendlyName = friendlyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getAddressDevice() {
        return addressDevice;
    }

    public void setAddressDevice(String addressDevice) {
        this.addressDevice = addressDevice;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public Boolean getConnectedDevice() {
        return connectedDevice;
    }

    public void setConnectedDevice(Boolean connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    public Float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public Float getBatteryCel1Level() {
        return batteryCel1Level;
    }

    public void setBatteryCel1Level(Float batteryCel1Level) {
        this.batteryCel1Level = batteryCel1Level;
    }

    public Float getBatteryCel2Level() {
        return batteryCel2Level;
    }

    public void setBatteryCel2Level(Float batteryCel2Level) {
        this.batteryCel2Level = batteryCel2Level;
    }

    public Float getTemperatureLevel() {
        return temperatureLevel;
    }

    public void setTemperatureLevel(Float temperatureLevel) {
        this.temperatureLevel = temperatureLevel;
    }

    public String getTypeLastUsed() {
        return typeLastUsed;
    }

    public void setTypeLastUsed(String typeLastUsed) {
        this.typeLastUsed = typeLastUsed;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public LocalDateTime getLastDateUsed() {
        return lastDateUsed;
    }

    public void setLastDateUsed(LocalDateTime lastDateUsed) {
        this.lastDateUsed = lastDateUsed;
    }

    public LocalDateTime getStrongLastDateUsed() {
        return strongLastDateUsed;
    }

    public void setStrongLastDateUsed(LocalDateTime strongLastDateUsed) {
        this.strongLastDateUsed = strongLastDateUsed;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Boolean getDeviceRunning() {
        return deviceRunning;
    }

    public void setDeviceRunning(Boolean deviceRunning) {
        this.deviceRunning = deviceRunning;
    }

    public BluetoothDevice getRmDevice() {
        return rmDevice;
    }

    public void setRmDevice(BluetoothDevice rmDevice) {
        this.rmDevice = rmDevice;
    }

    @Override
    public String toString() {
        return "EMSDevice{" +
                "id=" + id +
                ", partnerName='" + partnerName + '\'' +
                ", addressDevice='" + addressDevice + '\'' +
                ", friendlyName='" + friendlyName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EMSDevice emsDevice = (EMSDevice) o;
        return id.equals(emsDevice.id) && addressDevice.equals(emsDevice.addressDevice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressDevice);
    }
}
