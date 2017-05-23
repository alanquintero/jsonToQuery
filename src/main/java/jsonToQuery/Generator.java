/*******************************************************
 * Copyright (C) 2017 Alan Quintero <alan_q_b@hotmail.com>
 * 
 * This file is part of My Personal Project: "JSON to Query".
 * 
 * "JSON to Query" can not be copied and/or distributed without the express
 * permission of Alan Quintero.
 *******************************************************/

package jsonToQuery;

import static util.Constants.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Container;
import model.Geometry;
import model.State;

public class Generator {

	public static void generateQuery() {
		ObjectMapper mapper = new ObjectMapper();
		Container container = null;
		try {
			container = mapper.readValue(new File(JSON_FILE), Container.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		File directory = new File(DIRECTORY);
		if (!directory.exists()) {
			if (directory.mkdir()) {
				System.out.println(CREATE_DIRECTORY_SUCCESS);
			} else {
				System.err.println(CREATE_DIRECTORY_FAIL);
			}
		} else {
			System.out.println(ON_DIRECTORY);
		}

		if (container != null) {
			File file = new File(MYSQL_FILE);
			Path path = Paths.get(MYSQL_PATH);
			BufferedWriter writer = null;
			try {
				Files.deleteIfExists(path);
				if (file.createNewFile()) {
					System.out.println(CREATE_FILE_SUCCESS);
				} else {
					System.err.println(CREATE_FILE_FAIL);
				}
				writer = Files.newBufferedWriter(path);
				writer.write(INSERT_INTO + BREAK_LINE);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Map<String, String> states = State.getStates();
			StringBuilder query = new StringBuilder(QUERY_VALUES);
			int counter = 0;
			System.out.println(RECORDS_TO_CREATE + container.getGeometries().size());
			for (Geometry geometry : container.getGeometries()) {
				query.replace(0, query.length(), query.toString().replace(GEOMETRY_TYPE, geometry.getType()));
				query.replace(0, query.length(),
						query.toString().replace(REGION_NAME, geometry.getProperties().getName()));
				query.replace(0, query.length(), query.toString().replace(ZIP_CODE, geometry.getProperties().getZip()));
				query.replace(0, query.length(),
						query.toString().replace(STATE_CODE, geometry.getProperties().getState()));
				query.replace(0, query.length(), query.toString().replace(ARCS, geometry.getArcs()));
				query.replace(0, query.length(),
						query.toString().replace(ID_STATE_FK, states.get(geometry.getProperties().getState())));

				if (counter == container.getGeometries().size()) {
					query.replace(query.length() - 1, query.length(), SEMICOLON);
				}

				try {
					writer.write(query.toString() + BREAK_LINE);
					if (counter == FIRST_FLUSH || counter == SECOND_FLUSH) {
						writer.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				query = null;
				query = new StringBuilder(QUERY_VALUES);
				counter++;
			}
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(RECORDS_CREATED + counter);
		}

	}

}
