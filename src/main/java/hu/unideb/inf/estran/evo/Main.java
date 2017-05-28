package hu.unideb.inf.estran.evo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.FileWriter;

@SuppressWarnings("deprecation")
public class Main {

  private static final String ALPHABET;
  static {
    String temp = null;
    try (InputStream resourceAsStream =
        Main.class.getClassLoader().getResourceAsStream("alphabet")) {
      if (resourceAsStream == null) {
        Logger.error("The main alphabetical configuration file cannot be found!");
      } else {
        Logger.trace("The main alphabetical configuration file was found succesfully");
      }
      temp = IOUtils.toString(resourceAsStream);
    } catch (IOException e) {
      Logger.error("Error happened during the read of the alphabetical file");
    }
    ALPHABET = temp;
  }

  public static void main(String[] args) {
	  
    Configurator.defaultConfig()
        .addWriter(
            new FileWriter(System.getProperty("user.home") + System.getProperty("file.separator")
                + ".evo" + System.getProperty("file.separator") + "log.txt", false, true))
        .level(Level.INFO).activate();

    int populationSize = 1000;
    int maxCycle = 1000;
    int genomeSize = 12;

    EvolutionEngine ee = new EvolutionEngine(populationSize, genomeSize, ALPHABET, maxCycle);
    
    /*
    
    //for testing
     
    ee.addDrive("Hello*******");
    ee.addDrive("******World*");
    ee.addDrive("************");
    ee.addDrive("***** *****!!!");
    ee.addDrive("XYZ");
    ee.addDrive("ABC");
    
    ee.addDrive("Hello*******");
    ee.addDrive("******World*");
    ee.addDrive("***** *****!");
    
    */
    
    boolean continueReading = true;

    try (Scanner scanner = new Scanner(System.in)) {
      do {

        String input = scanner.nextLine();
        if (input.startsWith("+")) {
          ee.addDrive(StringUtils.remove(input, "+"));        
        } else if ("run".equals(input)) {
          ee.evolution();
          Logger.info( ee.getAllTimeFittestGenome()+"("+ee.getAllTimePeakFitness() + "%)");
        } else if ("exit".equals(input)) {
          continueReading = false;
        }

      } while (continueReading);
    }


  }

}
