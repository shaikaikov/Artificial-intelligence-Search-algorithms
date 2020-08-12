# Artificial-intelligence-Search-algorithms:

here is the project on Search-algorithms- BFS ,DFID ,A* ,IDA* , DFBnB. 
the algorithms act on the NxM tile-puzzle. 
the rules of the game: you can move the tile in adjacent to the empty tile-and only replace This block with the empty block .
in addition if the block is black block you can not to replace him with the empty block and move him. 
If the block is red- the cost to replace him with the empty block is 30.
if the block is green the cost is 1.
BFS and DFID go with the shortest path. 
The rest of the algorithms  Are go with the lowest cost path.
here I putted the inputs files and their outputs files .
if you want to create your own input-just create a file as input and put it as path in the main function in the Ex1.java file . 
and the output file is in the output function in the Ex1.java file- if you want to change his name(the name of the output file).
the inputs in the input file need to be in the next rules:

1.first row in the file is for the kind of the algorithm you want to use. there name as
to be like that: "BFS" | "DFID" | "A*" | "IDA*" | "DFBnB" in order the run will success.

2.the second row is for your wish-if you want to print the run-time of the algorithm or not.
you need to write :"with time" or "no time" in order to run the file.

3.the next row will be decide if to print the open list in every step of the algorithm(print to the screen). 
you need to write: "with open" or "no open".

4.the next row will be the NxM(row on columns)-like "3x3" and etc.

5.in the next row will be the list of the black tiles .
need to be write-"Black:... "(the list of the numbers separated by ",").
if there no numbers than write just "Black:"

6.just like the rule of the previous row-but just with the word –"Red:..."

7.the last row will be the board of the numbers that will be separated by "," .
the empty block will be sign as "_".

If you don’t understand the rules than see the examples in the inputs files.
Note that the details file is a proof in Hebrew  for the heuristic function of the heuristics algorithms.

