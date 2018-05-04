# The approach
## Breadth-first searching algorithm
The algorithm is used to find the minimal path from node A to B in the bi-directed graph.It will
be used to answer the questions.
    _Can someone get from cityA to cityB?_
## Depth-first search algorithm
The algorithm is used to answer the following question.
    _Given city A, can we detect if there is a loop leading back to city A?_

# Design
We have a class Graph, whcih is represented as an adjacency list. An instance of this graph will be created and the adjacency list will be
populated during the input file loading process.
There is a separate class *TeleportExpertSystem*, which holds all the algorithms to trasverse the graph to give out answers.

# Execution
To execute the program in the command line. First package up the uber jar files in maven. Then issue the following command
*java -cp <path_to_uber_jar>/uber-coding.challenge-1.0-Final.jar com.bytecubed.TeleportApp <path_to_input_file>/city_input.txt*



Assumption:
Make sure the input file doesn't contain some unnecessary characters

# Source
https://github.com/hsuhwang/CubeBytes-Exercise.git
