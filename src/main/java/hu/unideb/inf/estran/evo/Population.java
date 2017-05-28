package hu.unideb.inf.estran.evo;

import java.util.Random;
import java.util.Vector;

public class Population {

  public Population(Environment e) {
    this.e = e;
    units = new Vector<>();
  }

  private Vector<Unit> units;
  private int populationFitness;
  private int avarageFitness;
  private Environment e;
  private String allTimePeakGenome;
  private int allTimePeakFitness;

  public int getPopulationFitness() {
    return populationFitness;
  }

  public int getavarageFitness() {
    return avarageFitness;
  }

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

  
  public Unit rouletteWheelSelection() { // weighted by fitness ; IF popF is 0 -> randomS

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

  public Unit randomSelection() { // random
    Random rand = new Random();
    return units.elementAt(rand.nextInt(e.getPopulationSize()));
  }

  public void mutate() {


    for (int i = 0; i < e.getPopulationSize(); i++)
        units.set(i, e.mutate(units.elementAt(i)));

    update();
  }

  public void genesis() { // creates initial population
    for (int i = 0; i < e.getPopulationSize(); i++)
      units.add(e.generateUnit());

    update();
  }

  private void update() {
    updatePeakFitness();
    updatePopulationFitness();
    updateAvarageFitness();
    updateAllTimePeakGenome();
    updateAllTimePeakFitness();
  }

  private void updateAllTimePeakFitness() {
    if (allTimePeakFitness < getPeakFitness())
      allTimePeakFitness = getPeakFitness();
  }

  private void updateAllTimePeakGenome() {
    if (allTimePeakGenome == null)
      allTimePeakGenome = getPeakGenome();
    if (e.calculateFitness(allTimePeakGenome) < e.calculateFitness(getPeakGenome()))
      allTimePeakGenome = getPeakGenome();
  }



  private Unit getFittestUnit() {
    Unit unit = units.elementAt(0);

    for (Unit u : units) {
      if (unit.getFitness() < u.getFitness())
        unit = u;
    }
    return unit;
  }

  public int getPeakFitness() {
    return getFittestUnit().getFitness();
  }

  public String getPeakGenome() {
    return getFittestUnit().getGenome();
  }

  public void updateAvarageFitness() {
    avarageFitness = populationFitness == 0 ? 0 : populationFitness / e.getPopulationSize();
  }

  public void updatePopulationFitness() {
    populationFitness = 0;
    for (Unit u : units)
      populationFitness += u.getFitness();
  }

  public void updatePeakFitness() {
    getPeakFitness();
  }

  public String getAllTimePeakGenome() {
    return allTimePeakGenome;
  }

  public int getAllTimePeakFitness() {

    return allTimePeakFitness;
  }


}
