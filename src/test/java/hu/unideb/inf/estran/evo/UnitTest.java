package hu.unideb.inf.estran.evo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UnitTest {


	private static final String TEST_GENOME = "genome";
	private static final int TEST_FITNESS = 0;
	Unit unit;
	
	@Before
	public void setUp() throws Exception {
		unit = new Unit(TEST_GENOME, TEST_FITNESS);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(unit);
		assertEquals(TEST_FITNESS, unit.getFitness());
		assertEquals(TEST_GENOME, unit.getGenome());
	}

}
