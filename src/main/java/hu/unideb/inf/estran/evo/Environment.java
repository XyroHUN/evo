package hu.unideb.inf.estran.evo;

import java.util.Random;
import java.util.Vector;

public class Environment {

  private int populationSize;
  private int genomeSize;
  private String alphabet; // gene pool
  private Vector<String> drives; // motivators, evolutionary drives

  public Environment(int populationSize, int genomeSize, String alphabet, Vector<String> drives) {

    this.populationSize = populationSize;
    this.genomeSize = genomeSize;
    this.alphabet = alphabet;
    this.drives = drives;

    // if(isAlphaValid() && isOmegaValid()) {} else {} //its okay...
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


  public boolean isDriveValid() { // to add it to the vector
    /*
     * boolean charFound = false; boolean allFound = true;
     * 
     * for (char co: omega.toCharArray()) { for (char cab: alphabet.toCharArray()) { if (co==cab)
     * charFound = true; } allFound &= charFound; charFound = false; }
     * 
     * return allFound && omega.length()==genomeSize;
     */
    return true;
  }

  public void addDrive(String drive) {
    drives.add(drive);
  }


  public Unit mutate(Unit u, int mutationRate) {

    mutationRate = mutationRate < 0 ? 0 : mutationRate > 10 ? 10 : mutationRate;
    Random rand = new Random();
    String genome = "";
    for (int i = 0; i < genomeSize; i++) {
      genome += rand.nextInt(10) < mutationRate ? alphabet.charAt(rand.nextInt(alphabet.length()))
          : u.getGenome().charAt(i);
    }

    return new Unit(genome, calculateFitness(genome));
  }

  public Unit crossOver(Unit u1, Unit u2, int weight) {

    Random rand = new Random();
    String s = "";

    if (weight == -1)
      for (int i = 0; i < genomeSize; i++)
        s += rand.nextInt(1) == 0 ? u1.getGenome().charAt(i) : u2.getGenome().charAt(i);

    else if (weight == 0) {
      Unit u3 = u1.getFitness() > u2.getFitness() ? u1 : u2;
      int whyTHOUGH = Math.abs(u1.getFitness() - u2.getFitness() + 1);
      whyTHOUGH = whyTHOUGH == 0 ? 1 : whyTHOUGH;
      int chance = Math.round(100 / (whyTHOUGH));
      for (int i = 0; i < genomeSize; i++)
        s += rand.nextInt(chance) == 0 ? u3.getGenome().charAt(i)
            : rand.nextInt(1) == 0 ? u1.getGenome().charAt(i) : u2.getGenome().charAt(i);
    }

    else {
      Unit u3 = u1.getFitness() > u2.getFitness() ? u1 : u2;
      for (int i = 0; i < genomeSize; i++)
        s += rand.nextInt(4) != 0 ? u3.getGenome().charAt(i)
            : rand.nextInt(1) == 0 ? u1.getGenome().charAt(i) : u2.getGenome().charAt(i);
    }

    Unit u = new Unit(s, calculateFitness(s));
    return u;
  }

  /*
   * public Unit Mutate (Unit u, int mutationRate) { return u; } //optional upgrade path public
   * Vector<Unit> Elitism (Population p, int elitismRate) { return p.getUnits(); }
   */
  public int calculateFitness(String s) { // 0 is a no match, 100 is a perfect match - peakUnit

    // üres vector
    // vector valid
    // vector elemei validak - ez csak hozzáadásnál kell

    int genomeFitness = 0;

    for (String drive : drives) {

      if (s.equals(drive))
        genomeFitness += 100;
      else {

        int geneFitness = Math.round(100 / (genomeSize - unGeneNumber(drive)));

        for (int i = 0; i < s.length(); i++) {
          // if (drive.charAt(i)!='*')
          genomeFitness += s.charAt(i) == drive.charAt(i) ? geneFitness : 0;
        }


      }
    }
    return genomeFitness / drives.size();

  }

  private int unGeneNumber(String drive) {
    int count = 0;
    for (char c : drive.toCharArray())
      count += c == '*' ? 1 : 0;
    return count;
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
