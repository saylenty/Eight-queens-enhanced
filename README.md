chessPlacementIssue
============================
Algorithm solves the problem of the chess pieces placement on a chess board in a way they can't capture each other.
This is a kind very well known puzzle [Eight queens](https://en.wikipedia.org/wiki/Eight_queens_puzzle) but with other
chess pieces involved.

Implantation details
============================
The main idea of this algorithm is to try every possible combination of calculated pieces places until the correct
 answer(s) is found.
_We focus on finding the number of possible placements._ This is a recursive algorithm with backtracking approach.

Algorithm works like demonstrated below, but additional chess pieces available.<br>
<br>![Algorithm visualization](https://upload.wikimedia.org/wikipedia/commons/b/b0/8queensminconflict.gif)<br>

Available solvers
============================
<ol type="1">
    <li>Java one-thread approach - uses caching techniques in order to find the solution faster</li>
</ol>

Available visualization
============================
<ul>
    <li>Java implementation has a console output which indicates the number of possible combinations</li>
</ul>

Example
============================
We have the following pieces:
<ul>
    <li>Figures
        <ul>
            <li>Black King</li>
            <li>Black Queen</li>
            <li>Black Rock</li>
            <li>Black Knight</li>
            <li>Black Bishop</li>
            <li>White King</li>
        </ul>
    </li>
    <li>ChessBoard [9x6]</li>
</ul>

__Results__:
<ul>
    <li>Elapsed time (ms) 5587 <=> on Intel Core i7, 2.0Ghz, 16GB RAM</li>
</ul>
