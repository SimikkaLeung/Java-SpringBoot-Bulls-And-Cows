package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Round;
@SpringBootTest
class RoundRepositoryImplTest {

	
	@Autowired
	RoundRepository roundRepo;
	
	@BeforeEach
	void setUp() {
		roundRepo.save(new Round(22, "4389", 0, 1));
		roundRepo.save(new Round(23, "9403", 2, 2));
		roundRepo.save(new Round(24, "0234", 0, 0));
	}
	
	@AfterEach
	void cleanUp() {
		roundRepo.deleteById(22);
		roundRepo.deleteById(23);
		roundRepo.deleteById(24);
	}
	
	@Test
	void testSaveAndDelete() {
		
		Round round = new Round(28,"7028",2,2);
		assertEquals(1,roundRepo.save(round));
		assertEquals(1,roundRepo.delete(round));
		
		assertEquals(1,roundRepo.save(round));
		assertEquals(1,roundRepo.deleteById(28));
	}
	
	@Test
	void testFindById() {		
		System.out.println(roundRepo.findById(23).getRoundId());
		assertEquals(roundRepo.findById(23).getRoundId(),23);
	}
	
	@Test
	void testFindAll() {		
		assertEquals(roundRepo.findAll().size(),3);
	}
	
	@Test
	void testUpdate() {		
		Round round23 = roundRepo.findById(23);
		round23.setExactMatch(1);
		roundRepo.update(round23);
		assertEquals(roundRepo.findById(23).getExactMatch(),1);
	}
	
	@Test
	void testFindMaxRoundId() {		
		assertEquals(roundRepo.findMaxRoundId(),24);
	}
}
