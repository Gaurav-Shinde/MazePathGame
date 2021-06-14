# MazePathGame

## Description
This game generates a nxn matrix maze that must be navigated. Path is displayed by pressing the answer button. It also displays the different algorithms and data structures used as well as information about them including tutorials and time complexity.

## Why was this made
To use a classic game for users to interactively learn about the utilization and importance of differnt data structures and search algorithms in a program.

## Prerequisites
Java version 1.8.0 and higher
Java SE JDK 8 or higher -- but it must be the same as Java version

## Prerequisites setup
Use this [link](https://www.oracle.com/java/technologies/javase-downloads.html) to download the appropriate JDK which also installs the Java version compatible.

## How to run
In a terminal or command line type ```cd <to this repo>``` to the src folder. Next, ``` javac <YourMazePath or MyMazePath>.java InputGraphicMaze.java Intcoll6.java``` to compile. Then, ``` java <YourMazePath or MyMazePath> ``` to run the program.

## How to use
Currently there is only a binary node tree in Intcoll6 and DFS (YourMazePath) or BFS (MyMazePath) to be tested.
But new features will soon be added for data structure files like array, FIFO Queues, LinkedList, Hash Table. Additionally, pathfinding algorithms like A*, Hierarchical, DFS, BFS, Dijkstra.

After running the src program file, enter the # of rows and columns for the matrix and it generates a random maze. 

<img src="images/nxnbox.png" alt="20x20 matrix specify"/>

<img src="./images/part1.png" alt="20x20 matrix created"/>

The show path button shows the path to navigate the maze along with information about the data structure and algorithm including time complexity and time differnce.

<img src="images/part2.png" alt="path and stats displayed"/>

New feature will include rank out of the other possible pair combinations based on time complexity for the respective matrix size.

## Restrictions of use 
This repo may be cloned and used, however credit must be given to Gaurav Shinde for any modifications or use of the files and code in this repo.
