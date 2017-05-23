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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testGenerateQuery() {
		Generator.generateQuery();
		String output;
		if (outContent.toString().contains(CREATE_DIRECTORY_SUCCESS)) {
			output = CREATE_DIRECTORY_SUCCESS + System.getProperty("line.separator") + CREATE_FILE_SUCCESS
					+ System.getProperty("line.separator") + RECORDS_TO_CREATE + NUMBER_OF_RECORDS
					+ System.getProperty("line.separator") + RECORDS_CREATED + NUMBER_OF_RECORDS
					+ System.getProperty("line.separator");
		} else {
			output = ON_DIRECTORY + System.getProperty("line.separator") + CREATE_FILE_SUCCESS
					+ System.getProperty("line.separator") + RECORDS_TO_CREATE + NUMBER_OF_RECORDS
					+ System.getProperty("line.separator") + RECORDS_CREATED + NUMBER_OF_RECORDS
					+ System.getProperty("line.separator");
		}
		Assert.assertNotNull(outContent.toString());
		Assert.assertEquals(output, outContent.toString());
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

}
