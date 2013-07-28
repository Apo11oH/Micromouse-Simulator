Micromouse-Simulator
====================
A program that tests the maze mapping algorithm used on a micromouse.

The program can be loaded with various maze data to test the algorithm.

Current objective: Get the program working properly

Future plans include,
- Adding shortest route calculation module based on Dijkstra's algorithm
- Optimization of the code


ABOUT MAZE DATA:
The wall location for each cell is represented by an integer where each
direction is represented as:
North = 1
East = 2
South = 4
West = 8
ex. if there are walls in the North, East and South, the cell value will be 11

Might change this to hex in the future...


Team S.H.I.E.L.D.