package com.producter.basketball;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.producter.basketball.entity.Player;
import com.producter.basketball.service.PlayerService;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
 */

/*


 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BasketballApplicationTests {

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

	@Autowired
	PlayerService playerService;

	private static ObjectNode playerVariables = new ObjectMapper().createObjectNode().put("name", "DeMar").put("surname", "DeRozan").put("position", "SF");
	private static ObjectNode teamVariables = new ObjectMapper().createObjectNode().put("name", "CHICAGO BULLS");

	@DisplayName("create team successfully")
	@Test
	public void createTeam() throws IOException {
		GraphQLResponse response = graphQLTestTemplate.perform("graphql/addTeam.graphql", teamVariables);
		assertNotNull(response);
		assertTrue(response.isOk());
		JsonNode jsonNode = response.readTree();
		assertNull(jsonNode.get("errors"));
		assertEquals(teamVariables.get("name").asText(), response.get("$.data.addTeam.name"));

	}

	@DisplayName("create player successfully")
	@Test
	public void createPlayer() throws IOException {
		playerVariables.put("teamName","Utah Jazz");

		GraphQLResponse response = graphQLTestTemplate.perform("graphql/createPlayer.graphql", playerVariables);
		assertNotNull(response);
		assertTrue(response.isOk());
		JsonNode jsonNode = response.readTree();
		assertNull(jsonNode.get("errors"));

		assertEquals(playerVariables.get("name").asText(), response.get("$.data.addPlayer.name"));
		assertEquals(playerVariables.get("position").asText(), response.get("$.data.addPlayer.position"));
		assertEquals(playerVariables.get("teamName").asText(), response.get("$.data.addPlayer.team.name"));

	}

	@DisplayName("delete player successfully")
	@Test
	public void deletePlayer() throws IOException  {
		ObjectNode deletedPlayerVariables = new ObjectMapper().createObjectNode().put("name", "LeBron").put("surname", "James");
		Player currentPlayer = playerService.findPlayerByName("LeBron", "James");

		assertEquals(currentPlayer.getName(),deletedPlayerVariables.get("name").asText());

		GraphQLResponse response = graphQLTestTemplate.perform("graphql/deletePlayer.graphql", deletedPlayerVariables);
		assertNotNull(response);
		assertTrue(response.isOk());
		JsonNode jsonNode = response.readTree();
		assertNull(jsonNode.get("errors"));

		assertFalse(playerService.playerExists("LeBron", "James"));

	}

	@DisplayName("get players successfully")
	@Test
	public void getPlayers() throws IOException {

		GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/getPlayers.graphql");
		assertNotNull(response);
		assertTrue(response.isOk());

		JsonNode jsonNode = response.readTree();
		assertNull(jsonNode.get("errors"));
		assertTrue(jsonNode.get("data").get("findAllPlayers").isArray());

	}

	@DisplayName("transfer is not allowed due to squad limit, tested successfully")
	@Test
	public void transferToBoston() throws IOException {

		playerVariables.put("teamName","Boston Celtics");
		GraphQLResponse response = graphQLTestTemplate.perform("graphql/createPlayer.graphql", playerVariables);
		assertNotNull(response);
		assertTrue(response.isOk());
		JsonNode jsonNode = response.readTree();
		assertNotNull(jsonNode.get("errors"));
		assertTrue(jsonNode.get("errors").get(0).get("message").asText().contains("Team is already full"));

	}





}
