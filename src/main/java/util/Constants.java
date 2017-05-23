/*******************************************************
 * Copyright (C) 2017 Alan Quintero <alan_q_b@hotmail.com>
 * 
 * This file is part of My Personal Project: "JSON to Query".
 * 
 * "JSON to Query" can not be copied and/or distributed without the express
 * permission of Alan Quintero.
 *******************************************************/

package util;

public class Constants {

	public static final String INSERT_INTO = "INSERT INTO map.geometry (geometryType, regionName, zipCode, stateCode, arcs, idstatefk) VALUES";

	public static final String QUERY_VALUES = "('<geometryType>','<regionName>',<zipCode>,'<stateCode>','<arcs>',<idstatefk>),";

	public static final String JSON_FILE = "src/main/java/resources/region.json";

	public static final String DIRECTORY = "C:\\jsonToQuery";

	public static final String CREATE_DIRECTORY_SUCCESS = "jsonToQuery Directory is created!";

	public static final String CREATE_DIRECTORY_FAIL = "Failed to create jsonToQuery Directory!";

	public static final String ON_DIRECTORY = "On jsonToQuery Directory!";

	public static final String MYSQL_FILE = "C://jsonToQuery//insert-geometry.sql";

	public static final String MYSQL_PATH = "C:/jsonToQuery/insert-geometry.sql";

	public static final String CREATE_FILE_SUCCESS = "File is created!";

	public static final String CREATE_FILE_FAIL = "File already exists.";

	public static final String BREAK_LINE = "\n";

	public static final String RECORDS_TO_CREATE = "Number of records to create: ";

	public static final String GEOMETRY_TYPE = "<geometryType>";

	public static final String REGION_NAME = "<regionName>";

	public static final String ZIP_CODE = "<zipCode>";

	public static final String STATE_CODE = "<stateCode>";

	public static final String ARCS = "<arcs>";

	public static final String ID_STATE_FK = "<idstatefk>";

	public static final String RECORDS_CREATED = "Records created: ";

	public static final int FIRST_FLUSH = 1000;

	public static final int SECOND_FLUSH = 2000;

	public static final String SEMICOLON = ";";
	
	public static final String NUMBER_OF_RECORDS = "28635";

}
