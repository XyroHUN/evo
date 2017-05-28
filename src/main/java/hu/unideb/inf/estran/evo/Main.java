package hu.unideb.inf.estran.evo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

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
        Logger.debug("The main alphabetical configuration file was succesfully read");
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

    Vector<String> drives = new Vector<String>();


    int method = 1;
    int weight = 0;
    int mutationRate = 1;
    boolean differentParents = true;

    int populationSize = 1000;
    int maxCycle = 1000;

    int genomeSize = 5; // TODOOOOOOOOOOOOOO
    // százalékolés
    /// by0

    drives.add("aaaaa");

    EvolutionEngine ee = new EvolutionEngine(populationSize, genomeSize, ALPHABET, drives, maxCycle,
        method, weight, mutationRate, differentParents);


    boolean continueReading = true;

    try (Scanner scanner = new Scanner(System.in)) {
      do {

        String input = scanner.nextLine();
        if (input.startsWith("+")) {
          drives.add(StringUtils.remove(input, "+"));
        } else if (input.startsWith("-")) {
          drives.remove(StringUtils.remove(input, "-"));
        } else if ("run".equals(input)) {
          ee.evolution();
          Logger.info(ee.getAllTimePeakFitness() + ": " + ee.getAllTimeFittestGenome());
        } else if ("exit".equals(input)) {
          continueReading = false;
        }

      } while (continueReading);
    }


  }

}
