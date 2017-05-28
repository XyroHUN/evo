package hu.unideb.inf.estran.evo;

import java.util.Random;
import java.util.Vector;

public class Environment {

	private final int unitMutationRate = 8;
	private final int geneMutationRate = 2;

	private int populationSize;
	private int genomeSize;
	private String alphabet; // gene pool
	private Vector<String> drives; // motivators, evolutionary drives

	public Environment(int populationSize, int genomeSize, String alphabet, Vector<String> drives) {

		this.populationSize = populationSize;
		this.genomeSize = genomeSize;
		this.alphabet = alphabet;
		this.drives = drives;

	}

	public int getPopulationSize() {
		return populationSize;
	}

	public int getGenomeSize() {
		return genomeSize;
	}

	public String getAlphabet() {
		return alphabet;
	}

	public void addDrive(String drive) {

		drive = drive.substring(0, Math.min(drive.length(), genomeSize));
		String tail = "";
		for(int i = 0;i < genomeSize-drive.length();i++) tail += "*";
		
		drives.add(drive+tail);
	}

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


	public String generateGenome() {

		Random rand = new Random();
		String s = "";
		for (int i = 0; i < genomeSize; i++)
			s += alphabet.charAt(rand.nextInt(alphabet.length()));

		return s;
	}

	public Unit generateUnit() {
		String genome = generateGenome();
		Unit u = new Unit(genome, calculateFitness(genome));
		return u;
	}

}
