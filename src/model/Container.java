/*******************************************************
 * Copyright (C) 2017 Alan Quintero <alan_q_b@hotmail.com>
 * 
 * This file is part of My Personal Project: "JSON to Query".
 * 
 * "JSON to Query" can not be copied and/or distributed without the express
 * permission of Alan Quintero.
 *******************************************************/

package model;

import java.util.List;

public class Container {

	private List<Geometry> geometries;

	public List<Geometry> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
	}

}
