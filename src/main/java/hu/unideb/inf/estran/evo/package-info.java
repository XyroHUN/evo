/**
*
*Unit.java - contains the representation of a single unit.
*Contains a single constructor and the genome/fitness private fields with the getters.
*
*Population.java - contains the representation of units as a group, it depends on the environment.
*Calculate statistics, update information, provides getters and setters.
*Contains the following evolutionary programming based methods:
*evolve
*rouletteWheelSelection
*randomSelection
*mutate
*genesis
*
*Environment.java - contains the evolution specific details and evolutionary strategies.
*Handles the mutation and drive specific tasks, including fitness calculation and drive management.
*Contains getters, setters and the following evolutionary programming based methods:
*calculateFitness
*crossOver
*mutate
*addDrive
*generateUnit
*generateGenome
*
*EvolutionEngine.java - contains the core mechanism of the application, the
*evolution() method.
*
*Main.java
*reads the ALPHABET from source, does the logging and contains the main I/O cycle which communicates with the user, 
*calling the alghorithm, and providing information through the tinylog interface.
*
**/
package hu.unideb.inf.estran.evo;