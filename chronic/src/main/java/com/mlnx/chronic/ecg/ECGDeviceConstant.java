package com.mlnx.chronic.ecg;

public class ECGDeviceConstant {

    public static int ECGCHANNELNUM = 12;   // the number of channels
    public static float ECGREFERENCE = 2.4f; //reference voltage
    public static int ECGBASELINE = 32768; // baseline for ECG signal

    public static final int ECG_INVALIDVALUE = 65535;
    public static final int BPM_INVALIDVALUE = -1;
    public static final int ECG_GAIN = 4;
    public static final int ECG_THRESHOLD = 4;
    public static final float ECG_INVALID = -15.0f; //-15mv is invalid value of ECG
    public static final int FAKEPATIENT = -1; //patient id of fake patient(not real data)
    public static final int EXCEPTIONAL_HEARTRATE = 254; // when bpm is 255 means some exception occurs
    public static final float VERTICALRANGE = 2.0f;

    public static final float ECGConvertor(int dataBufferV) {
        if (dataBufferV == ECG_INVALIDVALUE)
            return ECG_INVALID;
        float output = (dataBufferV - ECGBASELINE) * 1000 * ECGREFERENCE
                / ((float) Math.pow(2.0, 20) * ECG_GAIN);
        if (output > VERTICALRANGE * 1.5f) {
            //System.out.println("out of drawing range of the panel");
            output = VERTICALRANGE * 1.5f;
        }
        if (output < -VERTICALRANGE * 1.5f)
            output = -VERTICALRANGE * 1.5f;

        return output;
    }
}