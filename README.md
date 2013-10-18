Micromouse Simulator (BETA)
====================
A program that tests the maze mapping algorithm used on a micromouse.

The program can be loaded with various maze data to test the algorithm.

Current objective: Get the program working properly

Future plans include,
- Adding shortest route calculation module based on Dijkstra's algorithm
- Optimization of the code


ABOUT MAZE DATA:
The wall location for each cell is represented by hexadecimal where each
direction is represented as:
North = 0x01
East = 0x02
South = 0x04
West = 0x08
ex. if there are walls in the North, East and South, the cell value will be 11

Current Bugs:
Working on the engine function after reaching the middle though.

UCSD, Team S.H.I.E.L.D.
