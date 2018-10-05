package ca.eaq.transformers;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.eaq.transformers.models.Transformer;
import ca.eaq.transformers.models.TransformerSpecs;
import io.restassured.RestAssured;

public class TransformersCRUDTest {

	@After
	public void testDelete() throws JSONException {
		RestAssured.delete("/transformers/bumblebee").andReturn().then().statusCode(200);
	}

	@Before
	public void testInsert() {
		TransformerSpecs specs = new TransformerSpecs("A", 3, 3, 3, 3, 3, 3, 3, 3);
		Transformer transformer = new Transformer("bumblebee", specs);
		
		RestAssured.given().contentType("application/json").body(transformer).when().post("/transformers").then().statusCode(201);
				
	}
	
	@Test
	public void testUpdate() {
		TransformerSpecs specs = new TransformerSpecs("A", 5, 3, 3, 4, 3, 3, 3, 3);
		Transformer transformer = new Transformer("A3", specs);
		
		RestAssured.given().contentType("application/json").body(transformer).when().put("/transformers").then().statusCode(200);
				
	}
	
	/**
	 * if the number is bigger than 10, the number 10 will be set
	 * if the number is smaller than 1, the number 1 will be set
	 */
	@Test
	public void testUpdateWithInvalidRangeNumber() {
		TransformerSpecs specs = new TransformerSpecs("A", 5, 13, 3, 4, -3, 3, 3, 3);
		Transformer transformer = new Transformer("A3", specs);
		
		RestAssured.given().contentType("application/json").body(transformer).when().put("/transformers").then().statusCode(200);
				
	}
	
	/**
	 * if the team is different from A or D the update throws an exception
	 */
	@Test
	public void testUpdateWithInvalidParameterTeam() {
		TransformerSpecs specs = new TransformerSpecs("C", 5, 3, 3, 4, 3, 3, 3, 3);
		Transformer transformer = new Transformer("A3", specs);
		
		RestAssured.given().contentType("application/json").body(transformer).when().put("/transformers").then().statusCode(400);
				
	}
	
	/**
	 * if the team is different from A or D the insert throws an exception
	 */
	@Test
	public void testInsertWithInvalidParameterTeam() {
		TransformerSpecs specs = new TransformerSpecs("C", 5, 3, 3, 4, 3, 3, 3, 3);
		Transformer transformer = new Transformer("A3", specs);
		
		RestAssured.given().contentType("application/json").body(transformer).when().put("/transformers").then().statusCode(400);
				
	}
	

}
