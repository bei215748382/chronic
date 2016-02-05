package com.mlnx.chronic.ecg;

import java.util.ArrayList;
import java.util.List;

/**
 * This enumeration defines the names of the 12 leads used in ECG.
 * 
 * @author Raphael Yu Ning <raphael.ning@gmail.com>
 * 
 */
public enum EcgLead {

    I, II, III, aVR, aVL, aVF, V1, V2, V3, V4, V5, V6;

    /**
     * Get the total number of leads defined.
     * 
     * @return The total number of ECG leads, which is 12.
     */
    public static int count() {

        return values().length;
    }

    /**
     * Convert a list of {@code EcgLead} instances to a single string of
     * comma-separated lead names.
     * 
     * @param leads
     *            The list of {@code EcgLead} instances to convert.
     * @return The conversion result.
     */
    public static String join(List<EcgLead> leads) {

        StringBuilder commaSeparatedLeads = new StringBuilder();
        for (int i = 0, n = leads.size(); i < n; i++) {
            commaSeparatedLeads.append(leads.get(i));
            if (i < n - 1) {
                commaSeparatedLeads.append(',');
            }
        }
        return commaSeparatedLeads.toString();
    }

    /**
     * Convert a string of comma-separated lead names to a list of
     * {@code EcgLead} instances.
     * 
     * @param commaSeparatedLeads
     *            The string to convert.
     * @return The conversion result.
     */
    public static List<EcgLead> split(String commaSeparatedLeads) {

        String[] leadNames = commaSeparatedLeads.split(",", count());
        List<EcgLead> leads = new ArrayList<EcgLead>(leadNames.length);
        for (String leadName : leadNames) {
            leadName = leadName.trim();
            if (!leadName.isEmpty()) {
                leads.add(valueOf(leadName));
            }
        }
        return leads;
    }
}
