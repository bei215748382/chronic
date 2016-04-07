package com.mlnx.chronic.util;

public enum EcgLead {

    I(0), II(1), III(-1), aVR(-1), aVL(-1), aVF(-1), V1(2), V2(3), V3(4), V4(5), V5(
            6), V6(7);

    private final int channelIndex;

    EcgLead(int channelIndex) {

        this.channelIndex = channelIndex;
    }

    public int getChannelIndex() {

        return channelIndex;
    }

    public boolean isDerived() {

        return channelIndex < 0;
    }

    public static int count() {

        return EcgLead.values().length;
    }

    public static EcgLead fromChannelIndex(int channelIndex) {

        if (channelIndex < 0 || channelIndex >= count()) {
            return null;
        }
        for (EcgLead ecgLead : EcgLead.values()) {
            if (ecgLead.channelIndex == channelIndex) {
                return ecgLead;
            }
        }
        return null;
    }

    public static int derive(EcgLead derivedLead, int valueI, int valueII) {

        // 0 <= y < 2Y, v = y - Y, -Y <= v < Y
        final int Y = 0x10000 / 2;
        int yI = valueI & 0xFFFF;
        int yII = valueII & 0xFFFF;
        switch (derivedLead) {
        case III:
            // v_III = v_II - v_I
            return yII - yI + Y;

        case aVR:
            // v_aVR = - (v_I + v_II) / 2
            return Y * 2 - (yI + yII) / 2;

        case aVL:
            // v_aVL = v_I - v_II / 2
            return yI - (yII - Y) / 2;

        case aVF:
            // v_aVF = v_II - v_I / 2
            return yII - (yI - Y) / 2;

        default:
            throw new IllegalArgumentException(derivedLead
                    + " is not a derived lead");
        }
    }
}
