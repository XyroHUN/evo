package hu.unideb.inf.estran.evo;

import java.util.Random;
import java.util.Vector;

/**
 * Contains the representation of units as a group, it depends on the environment.
 * Calculate statistics, update information, provides getters and setters.
 * Also contains evolutionary programming based methods.
 * @author Xyro
 *
 */
public class Population {

	/**
	 * The constructor of the class. Uses an Environment instance as a parameter for defining the population and its units attributes.
	 * @param e The Environments instance that used in the creating process of the population
	 */
  public Population(Environment e) {
    this.e = e;
    units = new Vector<>();
  }

  /**
   * The collection containing the units of the population.
   */
  private Vector<Unit> units;
  /**
   * The summarized fitness score of the population. 
   */
  private int populationFitness;
  /**
   * The average fitness score of the population.
   */
  private int avarageFitness;
  /**
   * The Environment instance used to define the population.
   */
  private Environment e;
  /**
   * The best candidates genome in the evolution.
   */
  private String allTimePeakGenome;
  /**
   * The highest fitness score in the evolution.
   */
  private int allTimePeakFitness;

  /**
   * Return the summarized fitness of the population in one evolutionary cycle.
   * @return the summarized fitness of the population in one evolutionary cycle
   */
  public int getPopulationFitness() {
    return populationFitness;
  }

  /**
   * Return the average fitness of the population in one evolutionary cycle.
   * @return the average fitness of the population in one evolutionary cycle
   */
  public int getavarageFitness() {
    return avarageFitness;
  }

  /**
   * Overwrites the whole population with new units of the same number.
   */
  public void evolve() {

    Vector<Unit> nextGeneration = new Vector<>();
    Unit u1, u2;

    for (int i = 0; i < e.getPopulationSize(); i++) {

      u1 = rouletteWheelSelection();
      u2 = rouletteWheelSelection();
      while (u1.equals(u2)) {
        u2 = rouletteWheelSelection();
      }

      nextGeneration.add(e.crossOver(u1, u2));
    }

    units.clear();
    units = nextGeneration;

    update();
  }

  /**
   * A selection strategy which is weighted by fitness. If necessary uses the {@link hu.unideb.inf.estran.evo.Population} methode.
   * @return a unit from the population
   */
  public Unit rouletteWheelSelection() {

    if (populationFitness == 0)
      return randomSelection();

    else {

      int rand = new Random().nextInt(populationFitness);
      int i = 0;
      int counter = units.elementAt(i).getFitness();
      while (counter < rand)
        counter += units.elementAt(++i).getFitness();

      return units.elementAt(i);
    }
  }

  /**
   * A selection strategy which is completely random.
   * @return a random unit from the population.
   */
  public Unit randomSelection() {
    Random rand = new Random();
    return units.elementAt(rand.nextInt(e.getPopulationSize()));
  }

  /**
   * Mutates all unit in the population.
   */
  public void mutate() {

    for (int i = 0; i < e.getPopulationSize(); i++)
        units.set(i, e.mutate(units.elementAt(i)));

    update();
  }

  /**
   * Creates initial population.
   */
  public void genesis() { 
    for (int i = 0; i < e.getPopulationSize(); i++)
      units.add(e.generateUnit());

    update();
  }

  /**
   * Main update methode that calls others. Used after an evolution cycle, for updating any necessary information.
   */
  private void update() {
    updatePopulationFitness();
    updateAvarageFitness();
    updateAllTimePeakGenome();
    updateAllTimePeakFitness();
  }

  /** 
   * Updates the fitness score of the genome that represents the best solution in the whole evolution.
   * Used after an evolution cycle.
   */
  private void updateAllTimePeakFitness() {
    if (allTimePeakFitness < getPeakFitness())
      allTimePeakFitness = getPeakFitness();
  }

  /**
   * 
   * Updates the genome that represents the best solution in the whole evolution.
   * Used after an evolution cycle.
   */
  private void updateAllTimePeakGenome() {
    if (allTimePeakGenome == null)
      allTimePeakGenome = getPeakGenome();
    if (e.calculateFitness(allTimePeakGenome) < e.calculateFitness(getPeakGenome()))
      allTimePeakGenome = getPeakGenome();
  }


/**
 * Searches for (one of) the best candidates in the population. 
 * @return the best candidate of the population.
 */
  private Unit getFittestUnit() {
    Unit unit = units.elementAt(0);

    for (Unit u : units) {
      if (unit.getFitness() < u.getFitness())
        unit = u;
    }
    return unit;
  }

  /**
   * Returns the best candidates fitness score in one cycle.
   * @return the best candidates fitness score in one cycle
   */
  public int getPeakFitness() {
    return getFittestUnit().getFitness();
  }

  /**
   * Returns the best candidates genome in one cycle.
   * @return the best candidates genome in one cycle
   */
  public String getPeakGenome() {
    return getFittestUnit().getGenome();
  }

  /**
   * Updates the average fitness value of the population.
   * Used after an evolution cycle.
   */
  public void updateAvarageFitness() {
    avarageFitness = populationFitness == 0 ? 0 : populationFitness / e.getPopulationSize();
  }

  /**
   * Updates the summarized fitness value of the population.
   * Used after an evolution cycle.
   */
  public void updatePopulationFitness() {
    populationFitness = 0;
    for (Unit u : units)
      populationFitness += u.getFitness();
  }

 
/**
 * Returns the genome of the best candidate in the whole evolution.
 * @return the genome of the best candidate in the whole evolution
 */
  public String getAllTimePeakGenome() {
    return allTimePeakGenome;
  }

  /**
   * Returns the highest fitness score in the whole evolution.
   * @return the highest fitness score in the whole evolution
   */
  public int getAllTimePeakFitness() {

    return allTimePeakFitness;
  }

}