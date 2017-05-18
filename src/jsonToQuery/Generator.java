/*******************************************************
 * Copyright (C) 2017 Alan Quintero <alan_q_b@hotmail.com>
 * 
 * This file is part of My Personal Project: "JSON to Query".
 * 
 * "JSON to Query" can not be copied and/or distributed without the express
 * permission of Alan Quintero.
 *******************************************************/

package jsonToQuery;

import static util.Constants.INSERT_INTO;
import static util.Constants.QUERY_VALUES;

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
			container = mapper.readValue(new File("src/resources/region.json"), Container.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (container != null) {
			File file = new File("c://jsonToQuery//insert-geometry.sql");
			Path path = Paths.get("c:/jsonToQuery/insert-geometry.sql");
			BufferedWriter writer = null;
			try {
				Files.deleteIfExists(path);
				if (file.createNewFile()) {
					System.out.println("File is created!");
				} else {
					System.out.println("File already exists.");
				}
				writer = Files.newBufferedWriter(path);
				writer.write(INSERT_INTO + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}

			Map<String, String> states = State.getStates();
			StringBuilder query = new StringBuilder(QUERY_VALUES);
			int counter = 1;
			System.out.println("Number of records to create: " + container.getGeometries().size());
			for (Geometry geometry : container.getGeometries()) {
				query.replace(0, query.length(), query.toString().replace("<geometryType>", geometry.getType()));
				query.replace(0, query.length(),
						query.toString().replace("<regionName>", geometry.getProperties().getName()));
				query.replace(0, query.length(),
						query.toString().replace("<zipCode>", geometry.getProperties().getZip()));
				query.replace(0, query.length(),
						query.toString().replace("<stateCode>", geometry.getProperties().getState()));
				query.replace(0, query.length(), query.toString().replace("<arcs>", geometry.getArcs()));
				query.replace(0, query.length(),
						query.toString().replace("<idstatefk>", states.get(geometry.getProperties().getState())));

				if (counter == container.getGeometries().size()) {
					query.replace(query.length() - 1, query.length(), ";");
				}

				try {
					writer.write(query.toString() + "\n");
					if (counter == 10000 || counter == 20000) {
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
			System.out.println("Records created: " + counter);
		}

	}

}
