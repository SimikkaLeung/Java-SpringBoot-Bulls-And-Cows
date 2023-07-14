package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.config.TestConfig;
import com.example.demo.models.Game;
import com.example.demo.models.Round;


@SpringBootTest
class GameRepositoryTest {

	@Autowired
	GameRepository gameRepo;

	
	@BeforeEach
	void setUp() {
		gameRepo.save(new Game(8, "3456"));
		gameRepo.save(new Game(9, "9403"));
		gameRepo.save(new Game(10, "0358"));
	}
	
	@AfterEach
	void cleanUp() {
		gameRepo.deleteById(8);
		gameRepo.deleteById(9);
		gameRepo.deleteById(10);
	}
	
	@Test
	void testSaveAndDelete() {
		
		Game game = new Game(11,"5386");
		assertEquals(1,gameRepo.save(game));
		assertEquals(1,gameRepo.delete(game));
		
		assertEquals(1,gameRepo.save(game));
		assertEquals(1,gameRepo.deleteById(11));
	}
	
	@Test
	void testFindById() {		
		assertEquals(10,gameRepo.findById(10).getGameId());
	}
	
	@Test
	void testFindAll() {		
		assertEquals(3,gameRepo.findAll().size());
	}
	
	@Test
	void testUpdate() {		
		Game game10 = gameRepo.findById(10);
		game10.setIsFinished(true);
		gameRepo.update(game10);
		assertTrue(gameRepo.findById(10).getIsFinished());
	}
	
	@Test
	void testFindMaxGameId() {		
		assertEquals(gameRepo.findMaxGameId(),10);
	}
	
//	@Test
//	void testSaveRelationFindRelation() {		
//		Round round = new Round(11,"3948",0,0);
//		assertEquals(gameRepo.saveRelation(8,round.roundId),1);
//	}
	
	
}
