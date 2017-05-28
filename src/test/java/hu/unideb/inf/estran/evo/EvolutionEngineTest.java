package hu.unideb.inf.estran.evo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EvolutionEngineTest {

	private static final String ALPHABET = "0123456789";
	private static final int POPULATION_SIZE = 100;
	private static final int GENOME_SIZE = 5;
	private static final int MAXCYCLE = 100;
	private EvolutionEngine ee;

	@Before
	public void setUp() throws Exception {
		ee = new EvolutionEngine(POPULATION_SIZE, GENOME_SIZE, ALPHABET, MAXCYCLE);
		ee.addDrive("012345");
		ee.evolution();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(ee);
		assertNotNull(ee.getAllTimeFittestGenome());
		assertNotNull(ee.getAllTimePeakFitness());
		assertNotNull(ee.getAverageFitness());
		assertEquals("01234", ee.getAllTimeFittestGenome());
	}

}
