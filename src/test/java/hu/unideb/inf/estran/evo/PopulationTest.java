package hu.unideb.inf.estran.evo;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PopulationTest {

	private static final String ALPHABET = "0123456789";
	  private static final int POPULATION_SIZE  = 100;
	  private static final int GENOME_SIZE = 5;
	  
	  private Vector<String> drives;
	  private Environment e;
	  private Population p;

	  @Before
	  public void setUp() throws Exception {
		drives = new Vector<>();
	    e = new Environment(POPULATION_SIZE, GENOME_SIZE, ALPHABET, drives);
	    	    
	    e.addDrive("012345");
	    e.addDrive("012345");
	    e.addDrive("0123456");
	    e.addDrive("01234");
	    
	    p = new Population(e);
	    p.genesis();
	    
	  }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(p);
		assertEquals(hu.unideb.inf.estran.evo.Unit.class, p.rouletteWheelSelection().getClass());
	}

}
