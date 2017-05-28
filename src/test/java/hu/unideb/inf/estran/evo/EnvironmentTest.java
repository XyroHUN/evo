package hu.unideb.inf.estran.evo;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnvironmentTest {

  private static final String ALPHABET = "0123456789";
  private static final int POPULATION_SIZE  = 100;
  private static final int GENOME_SIZE = 5;
  
  private Vector<String> drives;
  private Environment e;

  @Before
  public void setUp() throws Exception {
	drives = new Vector<>();
    e = new Environment(POPULATION_SIZE, GENOME_SIZE, ALPHABET, drives);
    
    e.addDrive("012345");
    e.addDrive("012345");
    e.addDrive("0123456");
    e.addDrive("01234");
    
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void test() {
	  
    assertEquals(POPULATION_SIZE, e.getPopulationSize() );
    assertEquals(GENOME_SIZE, e.getGenomeSize() );
    assertEquals(drives.elementAt(0), drives.elementAt(1));
    assertEquals(drives.elementAt(1), drives.elementAt(2));
    assertEquals(drives.elementAt(2).length(), drives.elementAt(3).length());
    assertTrue(e.calculateFitness("01234")>95);
    assertTrue(e.calculateFitness("01999")<e.calculateFitness("01299"));
    assertNotNull(e.generateUnit());
  }

}
