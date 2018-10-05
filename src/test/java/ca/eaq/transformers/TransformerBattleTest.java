package ca.eaq.transformers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TransformerBattleTest {

	private final String AUTOBOTS = "AUTOBOTS";
	private final String DECEPTICONS = "DECEPTICONS";
	private final String NONE = "NONE";

	@Before
	public void load() {

	}

	@Test
	public void testTie() throws JSONException {
		
		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=ATIE, DTIE").andReturn().jsonPath();

		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = new ArrayList<String>();
		List<String> losingTeamSurviversCompare = new ArrayList<String>();

		assertEquals(1, numberOfBattles);
		assertEquals(NONE, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);

	}

	@Test
	public void testVictoryByName() throws JSONException {
		
		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=optimus prime,d5").andReturn().jsonPath();

		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = Arrays.asList("OPTIMUS PRIME");
		List<String> losingTeamSurviversCompare = new ArrayList<String>();

		assertEquals(1, numberOfBattles);
		assertEquals(AUTOBOTS, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);

	}

	@Test
	public void testTiebyName() throws JSONException {
		
		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=optimus prime, PREDAKING").andReturn()
				.jsonPath();

		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = new ArrayList<String>();
		List<String> losingTeamSurviversCompare = new ArrayList<String>();

		assertEquals(1, numberOfBattles);
		assertEquals(NONE, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);
	}

	/**
	 * Victory by Courage keeps the loser alive
	 * 
	 * @throws JSONException
	 */
	@Test
	public void testVictoryByCourage() throws JSONException {
		
		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=ABRAVE, dweak").andReturn().jsonPath();
		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = Arrays.asList("ABRAVE");
		List<String> losingTeamSurviversCompare = Arrays.asList("DWEAK");

		assertEquals(1, numberOfBattles);
		assertEquals(AUTOBOTS, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);

	}

	@Test
	public void testVictoryByOverAllRating() {
		
		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=D4,A2").andReturn().jsonPath();
		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = Arrays.asList("D4");
		List<String> losingTeamSurviversCompare = new ArrayList<String>();

		assertEquals(1, numberOfBattles);
		assertEquals(DECEPTICONS, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);
	}

	@Test
	public void testVictoryBySkill() {
		
		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=ASkill, DWEAK").andReturn().jsonPath();
		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = Arrays.asList("ASKILL");
		List<String> losingTeamSurviversCompare = new ArrayList<String>();

		assertEquals(1, numberOfBattles);
		assertEquals(AUTOBOTS, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);

	}

	@Test
	public void testGroupBattle() {

		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=ASkill, D5,ABRAVE, D4,A2,D5").andReturn()
				.jsonPath();
		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = Arrays.asList("ABRAVE", "ASKILL");
		List<String> losingTeamSurviversCompare = Arrays.asList("D4", "D5");

		assertEquals(3, numberOfBattles);
		assertEquals(AUTOBOTS, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);

	}

	@Test
	public void testGroupBattleWithOneTeamBigger() {

		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=ASkill, D5,ABRAVE, D4,A2").andReturn()
				.jsonPath();
		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = new ArrayList<String>();
		List<String> losingTeamSurviversCompare = new ArrayList<String>();

		assertEquals(2, numberOfBattles);
		assertEquals(NONE, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);

	}

	@Test
	public void testBattleWithOneAbsentTeam() {

		JsonPath jsonPath = RestAssured.get("/transformers/battle?names=a1,a2,a3").andReturn().jsonPath();
		int numberOfBattles = jsonPath.getInt("numberOfBattles");
		String winningTeam = jsonPath.getString("winningTeam");
		List<String> winningTeamSurvivers = jsonPath.getList("winningTeamSurvivers");
		List<String> losingTeamSurvivers = jsonPath.getList("losingTeamSurvivers");

		List<String> winningTeamSurviversCompare = new ArrayList<String>();
		List<String> losingTeamSurviversCompare = new ArrayList<String>();

		assertEquals(0, numberOfBattles);
		assertEquals(NONE, winningTeam);
		assertEquals(winningTeamSurviversCompare, winningTeamSurvivers);
		assertEquals(losingTeamSurviversCompare, losingTeamSurvivers);

	}

}
