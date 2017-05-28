#**evo**

**Evolutionary Programming**
The application aiming to solve a simple task by mimicking the mechanism of the natural evolution.
Instead of finding something unknown, the user can explicitly specify evolutionary drives.
Thus the program works as a simple but dynamic environment, with multiple fitness alterations.

**Strategies:**
Different parents - yes
Selection - Roulette Rheel
Mutation chance - 8%(unit), 2%(gene)
Evolutionary cycles - 1000
Population size - 1000
Gene size - 12

**Usage:**
run - 
Runs the full evolutionary cycle, can be called multiple time, always initializing with a new population.
The providing information shows (one of) the best candidate of the evolution, with its fitness score as a percent.
Logging to the hidden .evo folder in the users home folder. and gives the same output through the console.
exit - 
closes the program
+[text] - 
Permanently adds [text] to the evolutionary drives.
Can be used multiple times, before or after running the evolutionary cycle.
Any text longer than the gene size will be truncated without warning.
Any text shorter with the gene size will be filled with joker genes.
Any character thats not included in the ALPHABET(located in the resources folder), will be handled as joker genes.

---

Bereczki László
University of Debrecen, Faculty of Informatics
2017