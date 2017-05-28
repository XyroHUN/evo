package hu.unideb.inf.estran.evo;

import java.util.Random;
import java.util.Vector;

/**
 *Contains the evolution specific details and evolutionary strategies.
 *Handles the mutation and drive specific tasks, including fitness calculation and drive management.
 * @author Xyro
 *
 */
public class Environment {

	/**
	 * Defines the mutation chance(in percent) of a unit.
	 */
	private final int unitMutationRate = 8;
	/**
	 * Defines the mutation chance(in percent) of a gene.
	 */
	private final int geneMutationRate = 2;

	/**
	 * The exact size of a population.
	 */
	private int populationSize;
	/**
	 * The exact size of the genomes.
	 */
	private int genomeSize;
	/**
	 * The gene pool of the genomes.
	 */
	private String alphabet; 
	/**
	 * Contains the motivators, evolutionary drives.
	 */
	private Vector<String> drives; 

	/**
	 * The constructor of the Environment class.
	 * @param populationSize size of the population
	 * @param genomeSize size of the genomes
	 * @param alphabet the alphabet used in the creation of genomes
	 * @param drives the list of the evolutionary drives
	 */
	public Environment(int populationSize, int genomeSize, String alphabet, Vector<String> drives) {

		this.populationSize = populationSize;
		this.genomeSize = genomeSize;
		this.alphabet = alphabet;
		this.drives = drives;

	}
	
	/**
	 * Returns the exact size of the population.
	 * @return the exact size of the population
	 */
	public int getPopulationSize() {
		return populationSize;
	}

	/**
	 * Returns the exact size of the genomes.
	 * @return the exact size of the genomes
	 */
	public int getGenomeSize() {
		return genomeSize;
	}


	/**
	 * Adds a drive to the drive pool. Before adding, its gene sequence will be truncated and prompted if necessary.
	 * @param drive is the drive that will be added to the evolutionary drive list
	 */
	public void addDrive(String drive) {

		drive = drive.substring(0, Math.min(drive.length(), genomeSize));
		String tail = "";
		for(int i = 0;i < genomeSize-drive.length();i++) tail += "*";
		
		drives.add(drive+tail);
	}

	/**
	 * Mutates the parameter unit. The mutation itself is random by chance and the mutation rate is affected by chance to.
	 * These mutation rates are hardwired to 8%(unit) and 2%(gene). 
	 * @param u is the unit that will be mutated
	 * @return the parameter unit after mutation
	 */
	public Unit mutate(Unit u) {

		Random rand = new Random();
		if (rand.nextInt(100) < unitMutationRate) {
			String genome = "";
			for (int i = 0; i < genomeSize; i++) {
				genome += rand.nextInt(100) < geneMutationRate ? alphabet.charAt(rand.nextInt(alphabet.length()))
						: u.getGenome().charAt(i);
			}

			return new Unit(genome, calculateFitness(genome));
		}

		else
			return u;
	}

	/**
	 * Generates a new unit from 2 other as parents. The chance of inheriting genes is tide to the 
	 * fitness scores of the parents. 
	 * @param u1 represents one of the parents
	 * @param u2 represents one of the parents
	 * @return a child unit of the 2 parameter units
	 */
	public Unit crossOver(Unit u1, Unit u2) {

		Random rand = new Random();
		Unit better;
		Unit worse;

		if (u1.getFitness() > u2.getFitness()) {
			better = u1;
			worse = u2;
		} else {
			better = u2;
			worse = u1;
		}

		String s = "";
		int chance = better.getFitness();

		for (int i = 0; i < genomeSize; i++)
			s += rand.nextInt(100) <= chance ? better.getGenome().charAt(i) : worse.getGenome().charAt(i);

		Unit u = new Unit(s, calculateFitness(s));
		return u;

	}

	/**
	 * Calculates the fitness score(in percent) of a non existing unit, by the string parameter that represent the units genome.
	 * In this scenario maximum scored can be achieved by being acceptable by all of the drives.
	 * @param s represent a non existing units genome
	 * @return the fitness score of the parameter in percent
	 */
	public int calculateFitness(String s) { // 0 is a no match, 100 is a perfect
											// match - peakUnit

		int genomeFitness = 0;

		for (String drive : drives) {

			if (s.equals(drive))
				genomeFitness += 100;
			else {

				int geneFitness = Math.round(100 / genomeSize);

				for (int i = 0; i < s.length(); i++) {
					genomeFitness += s.charAt(i) == drive.charAt(i) ? geneFitness : 0;
				}

			}
		}
		return genomeFitness;

	}


	/**
	 * Generates a random gene, thats acceptable by the environment's laws.
	 * @return a random genome
	 */
	public String generateGenome() {

		Random rand = new Random();
		String s = "";
		for (int i = 0; i < genomeSize; i++)
			s += alphabet.charAt(rand.nextInt(alphabet.length()));

		return s;
	}

	/**
	 * Generates a random unit that fits to the environment.
	 * @return a random unit
	 */
	public Unit generateUnit() {
		String genome = generateGenome();
		Unit u = new Unit(genome, calculateFitness(genome));
		return u;
	}

}
