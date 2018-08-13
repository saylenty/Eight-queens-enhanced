chessPlacementIssue
============================
Algorithm solves the problem of the chess pieces placement on a chess board in a way they can't capture each other.
This is a kind very well known puzzle [Eight queens](https://en.wikipedia.org/wiki/Eight_queens_puzzle) but **with other
chess pieces involved**.

Implantation details
============================
The main idea of this algorithm is to try every possible combination of calculated pieces places until the correct
 answer(s) is found.
_We focus on finding the number of possible placements._ This is a recursive algorithm with backtracking approach.

Algorithm works like demonstrated below, but additional chess pieces available.

![Algorithm visualization](https://upload.wikimedia.org/wikipedia/commons/b/b0/8queensminconflict.gif)

Available solvers
============================
1. Java single-thread approach - uses brute force recursive algorithm

Available visualization
============================
* Java implementation has a console output which indicates the number of possible combinations

Example
============================
We have the following pieces:
* Figures
  * Black King
  * Black Queen
  * Black Rock
  * Black Knight
  * Black Bishop
  * White King
* ChessBoard [9x6]

__Results__:
* Elapsed time (ms) 45433 <=> on Intel Core i7, 2.0Ghz, 16GB RAM
