/*******************************************************
 * Copyright (C) 2017 Alan Quintero <alan_q_b@hotmail.com>
 * 
 * This file is part of My Personal Project: "JSON to Query".
 * 
 * "JSON to Query" can not be copied and/or distributed without the express
 * permission of Alan Quintero.
 *******************************************************/

package model;

import java.util.HashMap;
import java.util.Map;

public class State {

	public static Map<String, String> getStates() {
		return setStates();
	}

	private static Map<String, String> setStates() {
		Map<String, String> mapStates = new HashMap<>();
		mapStates.put("AL", "1");
		mapStates.put("AK", "2");
		mapStates.put("AZ", "3");
		mapStates.put("AR", "4");
		mapStates.put("CA", "5");
		mapStates.put("CO", "6");
		mapStates.put("CT", "7");
		mapStates.put("DE", "8");
		mapStates.put("DC", "9");
		mapStates.put("FL", "10");
		mapStates.put("GA", "11");
		mapStates.put("HI", "12");
		mapStates.put("ID", "13");
		mapStates.put("IL", "14");
		mapStates.put("IN", "15");
		mapStates.put("IA", "16");
		mapStates.put("KS", "17");
		mapStates.put("KY", "18");
		mapStates.put("LA", "19");
		mapStates.put("ME", "20");
		mapStates.put("MD", "21");
		mapStates.put("MA", "22");
		mapStates.put("MI", "23");
		mapStates.put("MN", "24");
		mapStates.put("MS", "25");
		mapStates.put("MO", "26");
		mapStates.put("MT", "27");
		mapStates.put("NE", "28");
		mapStates.put("NV", "29");
		mapStates.put("NH", "30");
		mapStates.put("NJ", "31");
		mapStates.put("NM", "32");
		mapStates.put("NY", "33");
		mapStates.put("NC", "34");
		mapStates.put("ND", "35");
		mapStates.put("OH", "36");
		mapStates.put("OK", "37");
		mapStates.put("OR", "38");
		mapStates.put("PA", "39");
		mapStates.put("RI", "40");
		mapStates.put("SC", "41");
		mapStates.put("SD", "42");
		mapStates.put("TN", "43");
		mapStates.put("TX", "44");
		mapStates.put("UT", "45");
		mapStates.put("VT", "46");
		mapStates.put("VA", "47");
		mapStates.put("WA", "48");
		mapStates.put("WV", "49");
		mapStates.put("WI", "50");
		mapStates.put("WY", "51");
		mapStates.put("PR", "52");
		return mapStates;
	}

}
