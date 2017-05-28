package hu.unideb.inf.estran.evo;

/**
 * Representation of a single unit in a population.
 * @author Xyro
 *
 */
public class Unit {

  /**
   * Constructor for the class.
   *
   * @param genome is the genome(gene sequence) of the unit
   * @param fitness is the fitness(score) of the unit
   */
  public Unit(String genome, int fitness) {
    this.genome = genome;
    this.fitness = fitness;
  }

  /**
   * Represents a gene sequence specified by the environment, aiming to be optimal.
   */
  private String genome;

  /**
   * Shows the environment dependent effectiveness of the unit(0%-100%).
   */
  private int fitness;

  /**
   * Returns the genome of the unit.
   * @return the genome of the unit
   */
  public String getGenome() {
    return genome;
  }

  /**
   * Return the fitness of the unit.
   * @return the fitness of the unit
   */
  public int getFitness() {
    return fitness;
  }
}
