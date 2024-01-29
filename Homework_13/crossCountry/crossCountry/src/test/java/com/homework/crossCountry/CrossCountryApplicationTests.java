package com.homework.crossCountry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.homework.crossCountry.controller.CrossCountryController;
import com.homework.crossCountry.model.Result;
import com.homework.crossCountry.model.Runner;
import com.homework.crossCountry.model.Team;
import com.homework.crossCountry.service.Results;

@SpringBootTest
class CrossCountryApplicationTests {

	@InjectMocks
	private CrossCountryController controller;

	@Mock
	private Results results;

	@Mock
	private Result result;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testCalculateRaceResultsForNull() { // Arrange
		List<String> teamNames = Arrays.asList("B", "B", "B", "B", "A");

		// Create mock data for runners and teams
		Team teamA = new Team("A");
		Team teamB = new Team("B");

		for (int i = 1; i <= 5; i++) {
			teamA.addRunner(new Runner(i, teamA));
			teamB.addRunner(new Runner(i + 5, teamB));
		}

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();
		resultList.add(new Result("A", teamA.score(), teamA.getTiebreakPlace()));
		resultList.add(new Result("B", teamB.score(), teamB.getTiebreakPlace()));
		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(2, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}

	@Test
	public void testCalculateRaceResults() {
		List<String> teamNames = Arrays.asList("A", "B", "B", "A", "B", "A", "B", "A", "A", "A", "B", "B", "A", "B");

		// Create mock data for runners and teams
		Team teamA = new Team("A");
		Team teamB = new Team("B");

		teamA.addRunner(new Runner(1, teamA));
		teamB.addRunner(new Runner(2, teamB));
		teamA.addRunner(new Runner(4, teamA));
		teamB.addRunner(new Runner(3, teamB));
		teamA.addRunner(new Runner(6, teamA));
		teamB.addRunner(new Runner(5, teamB));
		teamB.addRunner(new Runner(7, teamB));
		teamA.addRunner(new Runner(8, teamA));
		teamA.addRunner(new Runner(9, teamA));
		teamB.addRunner(new Runner(11, teamB));
		teamA.addRunner(new Runner(10, teamA));
		teamB.addRunner(new Runner(12, teamB));
		teamA.addRunner(new Runner(13, teamA));
		teamB.addRunner(new Runner(14, teamB));

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();
		resultList.add(new Result("A", teamA.score(), teamA.getTiebreakPlace()));
		resultList.add(new Result("B", teamB.score(), teamB.getTiebreakPlace()));
		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(2, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}

	@Test
	public void testCalculateRaceResultsForNoTie() {
		List<String> teamNames = Arrays.asList("A", "B", "B", "A", "B", "A", "A", "A", "A", "B", "B", "B");

		// Create mock data for runners and teams
		Team teamA = new Team("A");
		Team teamB = new Team("B");

		teamA.addRunner(new Runner(1, teamA));
		teamB.addRunner(new Runner(2, teamB));
		teamA.addRunner(new Runner(4, teamA));
		teamB.addRunner(new Runner(3, teamB));
		teamA.addRunner(new Runner(6, teamA));
		teamB.addRunner(new Runner(5, teamB));
		teamB.addRunner(new Runner(7, teamA));
		teamA.addRunner(new Runner(8, teamA));
		teamA.addRunner(new Runner(9, teamA));
		teamB.addRunner(new Runner(11, teamB));
		teamA.addRunner(new Runner(10, teamB));
		teamB.addRunner(new Runner(12, teamB));

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();
		resultList.add(new Result("A", teamA.score(), teamA.getTiebreakPlace()));
		resultList.add(new Result("B", teamB.score(), teamB.getTiebreakPlace()));
		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(2, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}

	public void testCalculateRaceResultsForB() {
		List<String> teamNames = Arrays.asList("B", "B", "B", "B", "B", "A", "A", "A", "A", "A", "A", "B");

		// Create mock data for runners and teams
		Team teamA = new Team("A");
		Team teamB = new Team("B");

		teamA.addRunner(new Runner(1, teamB));
		teamB.addRunner(new Runner(2, teamB));
		teamA.addRunner(new Runner(4, teamB));
		teamB.addRunner(new Runner(3, teamB));
		teamA.addRunner(new Runner(6, teamA));
		teamB.addRunner(new Runner(5, teamB));
		teamB.addRunner(new Runner(7, teamA));
		teamA.addRunner(new Runner(8, teamA));
		teamA.addRunner(new Runner(9, teamA));
		teamB.addRunner(new Runner(11, teamA));
		teamA.addRunner(new Runner(10, teamA));
		teamB.addRunner(new Runner(12, teamB));

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();
		resultList.add(new Result("A", teamA.score(), teamA.getTiebreakPlace()));
		resultList.add(new Result("B", teamB.score(), teamB.getTiebreakPlace()));
		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(2, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}

	@Test
	public void testCalculateRaceResultsForEmptyRequest() {
		List<String> teamNames = Arrays.asList();

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();

		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(0, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}

	@Test
	public void testCalculateRaceResultsForOneLessThan5() {
		List<String> teamNames = Arrays.asList("B", "B", "B", "B", "B", "A", "A", "A", "B");

		// Create mock data for runners and teams
		Team teamA = new Team("A");
		Team teamB = new Team("B");

		teamA.addRunner(new Runner(1, teamB));
		teamB.addRunner(new Runner(2, teamB));
		teamA.addRunner(new Runner(4, teamB));
		teamB.addRunner(new Runner(3, teamB));
		teamA.addRunner(new Runner(6, teamA));
		teamB.addRunner(new Runner(5, teamB));
		teamB.addRunner(new Runner(7, teamA));
		teamA.addRunner(new Runner(8, teamA));
		teamB.addRunner(new Runner(9, teamB));

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();
		resultList.add(new Result("A", teamA.score(), teamA.getTiebreakPlace()));
		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(1, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}

	@Test
	public void testCalculateRaceResultsForThree() {
		List<String> teamNames = Arrays.asList("C", "C",
				"C", "C", "C", "C","B", "B", "B", "B", "B", "A", "A", "A", "B", "A", "A", "A");

		// Create mock data for runners and teams
		Team teamA = new Team("A");
		Team teamB = new Team("B");
		Team teamC = new Team("C");

		teamB.addRunner(new Runner(1, teamC));
		teamA.addRunner(new Runner(2, teamC));
		teamB.addRunner(new Runner(3, teamC));
		teamA.addRunner(new Runner(4, teamC));
		teamB.addRunner(new Runner(5, teamC));
		teamB.addRunner(new Runner(6, teamC));
		teamA.addRunner(new Runner(7, teamB));
		teamB.addRunner(new Runner(8, teamB));
		teamA.addRunner(new Runner(9, teamB));
		teamB.addRunner(new Runner(10, teamB));
		teamB.addRunner(new Runner(11, teamB));

		teamA.addRunner(new Runner(11, teamA));
		teamB.addRunner(new Runner(13, teamA));
		teamA.addRunner(new Runner(14, teamA));
		teamB.addRunner(new Runner(15, teamB));
		teamA.addRunner(new Runner(16, teamA));
		teamB.addRunner(new Runner(17, teamA));
		teamA.addRunner(new Runner(18, teamA));

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();
		resultList.add(new Result("A", teamA.score(), teamA.getTiebreakPlace()));
		resultList.add(new Result("B", teamB.score(), teamB.getTiebreakPlace()));
		resultList.add(new Result("C", teamC.score(), teamC.getTiebreakPlace()));
		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(3, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}
	@Test
	public void testCalculateRaceResultsForThreeWhereOnIsLessThan5() {
		List<String> teamNames = Arrays.asList("C", "C",
				"C", "C", "C", "C","B", "B", "B", "B","B", "A", "A", "A", "B", "A");

		// Create mock data for runners and teams
		Team teamA = new Team("A");
		Team teamB = new Team("B");
		Team teamC = new Team("C");

		teamB.addRunner(new Runner(1, teamC));
		teamA.addRunner(new Runner(2, teamC));
		teamB.addRunner(new Runner(3, teamC));
		teamA.addRunner(new Runner(4, teamC));
		teamB.addRunner(new Runner(5, teamC));
		teamB.addRunner(new Runner(6, teamC));
		teamA.addRunner(new Runner(7, teamB));
		teamB.addRunner(new Runner(8, teamB));
		teamA.addRunner(new Runner(9, teamB));
		teamB.addRunner(new Runner(10, teamB));
		teamB.addRunner(new Runner(11, teamB));

		teamA.addRunner(new Runner(11, teamA));
		teamB.addRunner(new Runner(13, teamA));
		teamA.addRunner(new Runner(14, teamA));
		teamB.addRunner(new Runner(15, teamB));
		teamA.addRunner(new Runner(16, teamA));

		// Mock behavior of the Results service
		List<Result> resultList = new ArrayList<>();
		resultList.add(new Result("B", teamB.score(), teamB.getTiebreakPlace()));
		resultList.add(new Result("C", teamC.score(), teamC.getTiebreakPlace()));
		Mockito.when(results.getTeams()).thenReturn(resultList); // Act
		Results calculatedResults = controller.calculateRaceResults(teamNames);

		//
		assertNotNull(calculatedResults);
		List<Result> teams = calculatedResults.getTeams();
		assertEquals(2, teams.size());

		// Verify that the Results service was called to set the teams
		Mockito.verify(results).setTeams(Mockito.anyList());

		// Assert that the message indicates the correct winner assertEquals(null,
		calculatedResults.getMessage();
	}

}
