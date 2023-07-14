package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Game;
import com.example.demo.models.Round;
@SpringBootTest
class GameRoundRepositoryImplTest {
	
	@Autowired
	GameRepository gameRepo;
	
	@Autowired
	RoundRepository roundRepo;
	
	@BeforeEach
	void setUp() {
		gameRepo.save(new Game(8, "3456"));
		gameRepo.save(new Game(9, "9403"));
		gameRepo.save(new Game(10, "0358"));
		roundRepo.save(new Round(22, "4389", 0, 1));
		roundRepo.save(new Round(23, "9403", 2, 2));
		roundRepo.save(new Round(24, "0234", 0, 0));
	}
	
	@AfterEach
	void cleanUp() {
		gameRepo.deleteAllRelation();
		gameRepo.deleteById(8);
		gameRepo.deleteById(9);
		gameRepo.deleteById(10);
		roundRepo.deleteById(22);
		roundRepo.deleteById(23);
		roundRepo.deleteById(24);
	}

	@Test
	void testSaveRelationFindRelation() {		
		assertEquals(gameRepo.saveRelation(8,22),1);
		assertEquals(gameRepo.deleteAllRelation(),1);
	}
}
