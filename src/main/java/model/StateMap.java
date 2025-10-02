/**
 * Copyright 2025 Alan Quintero
 * Source: https://github.com/alanquintero/jsonToQuery
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to provide a mapping of US state codes to numeric IDs.
 *
 * @author Alan Quintero
 */
public class StateMap {

    private final Map<String, String> stateMap = new HashMap<>();

    public StateMap() {
        initStateMap();
    }

    public String getState(final String key) {
        return stateMap.get(key);
    }

    // IMPORTANT: use it for testing ONLY
    public Map<String, String> getStateMapForTesting() {
        return stateMap;
    }

    private void initStateMap() {
        stateMap.put("AL", "1");
        stateMap.put("AK", "2");
        stateMap.put("AZ", "3");
        stateMap.put("AR", "4");
        stateMap.put("CA", "5");
        stateMap.put("CO", "6");
        stateMap.put("CT", "7");
        stateMap.put("DE", "8");
        stateMap.put("DC", "9");
        stateMap.put("FL", "10");
        stateMap.put("GA", "11");
        stateMap.put("HI", "12");
        stateMap.put("ID", "13");
        stateMap.put("IL", "14");
        stateMap.put("IN", "15");
        stateMap.put("IA", "16");
        stateMap.put("KS", "17");
        stateMap.put("KY", "18");
        stateMap.put("LA", "19");
        stateMap.put("ME", "20");
        stateMap.put("MD", "21");
        stateMap.put("MA", "22");
        stateMap.put("MI", "23");
        stateMap.put("MN", "24");
        stateMap.put("MS", "25");
        stateMap.put("MO", "26");
        stateMap.put("MT", "27");
        stateMap.put("NE", "28");
        stateMap.put("NV", "29");
        stateMap.put("NH", "30");
        stateMap.put("NJ", "31");
        stateMap.put("NM", "32");
        stateMap.put("NY", "33");
        stateMap.put("NC", "34");
        stateMap.put("ND", "35");
        stateMap.put("OH", "36");
        stateMap.put("OK", "37");
        stateMap.put("OR", "38");
        stateMap.put("PA", "39");
        stateMap.put("RI", "40");
        stateMap.put("SC", "41");
        stateMap.put("SD", "42");
        stateMap.put("TN", "43");
        stateMap.put("TX", "44");
        stateMap.put("UT", "45");
        stateMap.put("VT", "46");
        stateMap.put("VA", "47");
        stateMap.put("WA", "48");
        stateMap.put("WV", "49");
        stateMap.put("WI", "50");
        stateMap.put("WY", "51");
        stateMap.put("PR", "52");
    }
}
