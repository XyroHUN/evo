package hu.unideb.inf.estran.evo;

import java.util.Vector;

/**
 * Contains the core mechanism of the application.
 * @author Xyro
 *
 */
public class EvolutionEngine {

	/**
	 * Constructor of the class.
	 * @param populationSize is the size of the population
	 * @param genomeSize is the size of a gene
	 * @param alphabet is used(only) to create genes
	 * @param maxCycle is the maximum cycles of the evolution
	 */
  public EvolutionEngine(int populationSize, int genomeSize, String alphabet,
      int maxCycle) {
    this.maxCycle = maxCycle;
    this.populationSize = populationSize;
    this.genomeSize=genomeSize;
    this.alphabet=alphabet;
    
    drives = new Vector<>();
    averageFitness = new Vector<>();
    peakFitness = new Vector<>();
    
    e = new Environment(populationSize, genomeSize, alphabet, drives); 
    p = new Population(e);
    p.genesis();
  }

  /**
   * An environment instance that used by the engine.
   */
  private Environment e;
  
  /**
   * The exact size of the population.
   */
  private Population p;
  
  /**
   * Maximum number of evolutionary cycles.
   */
  private int maxCycle;
  
  /**
   * Average fitnesses of the cycles.
   */
  private Vector<Integer> averageFitness;
  
  /**
   * Best fitnesses of the cycles. 
   */
  private Vector<Integer> peakFitness;
  /**
   * (One of) the best candidates's genome code.
   */
  private String allTimeFittestGenome;
  /**
   * (One of) the best candidates's fitness score.
   */
  private int allTimePeakFitness;
  /**
   * Exact size of the population.
   */
private int populationSize;
/**
 * Exact size of a gene.
 */
private int genomeSize;
/**
 * Usable characters for the genes.
 */
private String alphabet;
/**
 * Contains the evolutionary drives.
 */
private Vector<String> drives;

  /**
   * Starts the evolution cycle with the configurated parameters.
   */
  public void evolution() {

	  
	  
    for (int currentCycle = 0; currentCycle < maxCycle; currentCycle++) {

      averageFitness.add(p.getavarageFitness());
      peakFitness.add(p.getPeakFitness());
      p.evolve();
      p.mutate();
    }

    allTimeFittestGenome = p.getAllTimePeakGenome();
    allTimePeakFitness = p.getAllTimePeakFitness();
    
    //clean up for next run
    
	averageFitness.clear();
	peakFitness.clear();
	
	e = new Environment(populationSize, genomeSize, alphabet, drives); 
    p = new Population(e);
    p.genesis();
  }

    /**
     * Adds a drive to the drive pool through the {@link hu.unideb.inf.estran.evo.Environment#addDrive(String)} method.
     * @param drive is the string parameter that will be inserted into the drives
     */
	public void addDrive(String drive) {
		e.addDrive(drive);		
		
	}

  /**
   * Returns the average fitness of the evolutionary process. 
   * @return the average fitness of the evolutionary process
   */
  public Vector<Integer> getAverageFitness() {
    return averageFitness;
  }

  /**
   * Returns the highest fitness scores of the cycles.
   * @return the highest fitness scores of the cycles
   */
  public Vector<Integer> getPeakFitness() {
    return peakFitness;
  }

  /**
   * Returns the best candidate through the whole evolution cycle.
   * 
   * @return the best candidate through the whole evolution cycle
   */
  public String getAllTimeFittestGenome() {
    return allTimeFittestGenome;
  }

  /**
   * Returns the all time peak fitness of the population.
   * 
   * @return the all time peak fitness of the population
   */
  public int getAllTimePeakFitness() {
    return allTimePeakFitness;
  }
}
