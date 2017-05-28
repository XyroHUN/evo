package hu.unideb.inf.estran.evo;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnvironmentTest {

  private static final String TEST_ALPHABET = "testAlphabet";
  private Environment environment;

  @Before
  public void setUp() throws Exception {
    environment = new Environment(0, 0, TEST_ALPHABET, new Vector<>());
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void test() {
   // assertEquals(TEST_ALPHABET, environment.getAlphabet());
  }

}
