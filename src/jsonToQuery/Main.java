/*******************************************************
 * Copyright (C) 2017 Alan Quintero <alan_q_b@hotmail.com>
 * 
 * This file is part of My Personal Project: "JSON to Query".
 * 
 * "JSON to Query" can not be copied and/or distributed without the express
 * permission of Alan Quintero.
 *******************************************************/

package jsonToQuery;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Container;
import model.State;

public class Main {

	public static void main(String[] args) {
		Map<String, String> states = State.getStates();

		ObjectMapper mapper = new ObjectMapper();
		Container container = null;
		try {
			container = mapper.readValue(new File("src/resources/region.json"), Container.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
