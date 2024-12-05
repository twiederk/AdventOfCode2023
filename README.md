# Advent Of Code 2023

## Overview of the puzzles

|  Day | Title                             | Part 1  | Part 2  | Notes                                                                                                                                                                                                                                             |
|-----:|-----------------------------------|:-------:|:-------:|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  [1] | [Trebuchet?!]                     | &#9733; | &#9733; | Scan string to find digits and words                                                                                                                                                                                                              |
|  [2] | [Cube Conundrum]                  | &#9733; | &#9733; | Colored cubes in a bag must be turned in a data model of lists and maps                                                                                                                                                                           |
|  [3] | [Gear Ratios]                     | &#9733; | &#9733; | Scan list of strings for numbers which are attached to special characters (symbols) in the same line or the line above or below                                                                                                                   |
|  [4] | [Scratchcards]                    | &#9733; | &#9733; | Count numbers in lists (part 1). Generate objects using recursion.                                                                                                                                                                                |
|  [5] | [If You Give A Seed A Fertilizer] | &#9733; | &#9733; | Ranges, ranges and ranges to avoid out of memory problems.                                                                                                                                                                                        |
|  [6] | [Wait For It]                     | &#9733; | &#9733; | Easy for beginners with simple calculation.                                                                                                                                                                                                       |
|  [7] | [Camel Card]                      | &#9733; | &#9733; | Card game like poker. Nice puzzle, even for beginners.                                                                                                                                                                                            |
|  [8] | [Haunted Wasteland]               | &#9733; | &#9733; | Graph puzzle. Find loops and use LCM (see [Video])                                                                                                                                                                                                |
|  [9] | [Mirage Maintenance]              | &#9733; | &#9733; | Nice puzzle for recursion (pyramid top down). history.sum() is not the same as history.all { it == 0 }, because of [-1, 1 ]                                                                                                                       |
| [10] | [Pipe Maze]                       | &#9733; | &#9733; | First 2D puzzle. Reviewed solution of from [Day 10 of Todd Ginsberg], in particular form movement. This comment about [vector graphics] helped a lot for part 2                                                                                   |
| [11] | [Cosmic Expansion]                | &#9733; | &#9733; | Nice puzzle to insert elements in lists in part 1, to realize in part 2, that there is a much simpler solution based on Manhatten distance.                                                                                                       |
| [12] | [Hot Springs]                     | &#9733; |         | Pattern matching and finding all possible matches. Solved part 1 with brute force. Don't think I will try part 2          .                                                                                                                       |
| [13] | [Point of Incidence]              |         |         | Find reflections (mirrors) in 2D grid. Tried solving of part 1 two times. Don't think I will try it once more                                                                                                                                     |
| [14] | [Parabolic Reflector Dish]        | &#9733; | &#9733; | Nice puzzle about gravity, even implemented [visualisation]. Solved part 1 by myself. Needed research for part two. Found help in this [Video from GODdev] and as always from [Day 14 of Todd Ginsberg]. Unlivable implementation on [switch].    |
| [15] | [Lens Library]                    | &#9733; | &#9733; | Implementation of HASH algorithm (part 1). Create data model to represent a HASHMAP (part 2)                                                                                                                                                      |   
| [16] | [The Floor Will Be Lava]          | &#9733; | &#9733; | 2D puzzle with loops. Needed help to realize that the beams could fully loop back on themselves. Again the solution form [Day 16 of Todd Ginsberg] gave me the hint. Used brute force for part 2. Luckily first edge I checked was the right one. |   
| [17] | [Clumsy Crucible]                 | &#9733; | &#9733; | Used A* Algorithm, check also [2022/12]. [Day 17 of Todd Ginsberg] uses Dijkstra-Algorithm                                                                                                                                                        |   
| [18] | [Lavaduct Lagoon]                 | &#9733; |         | 2D puzzle with fill algorithm. Started part 2, but I think I won't make it.                                                                                                                                                                       |   
| [19] | [Aplenty]                         | &#9733; |         | Sending a part through a list of workflows based on rules of each workflow. No idea how to solve the 2nd part                                                                                                                                     |   
|   20 | [Pulse Propagation]               |         |         |                                                                                                                                                                                                                                                   |   
| [21] | [Step Counter]                    | &#9733; |         | BFS in part 1. BFS with infinity in part 2.                                                                                                                                                                                                       |   
|   22 | [Sand Slabs]                      |         |         |                                                                                                                                                                                                                                                   |   
| [23] | [A Long Walk]                     | &#9733; |         | BFS in part 1. Out of memory in part 2.                                                                                                                                                                                                           |   
|   24 | [Never Tell Me The Odds]          |         |         |                                                                                                                                                                                                                                                   |   
|   25 | [Snowverload]                     |         |         |                                                                                                                                                                                                                                                   |   

## Resources

## To Do
* Replace for-loop with streams everywhere
* Day05 optimize performance of part 2
* Day07 refactor strengthWithJoker
* Day17
  * Implement Dijkstra-Algorithmus


[1]: src/main/kotlin/Day01.kt
[2]: src/main/kotlin/Day02.kt
[3]: src/main/kotlin/Day03.kt
[4]: src/main/kotlin/Day04.kt
[5]: src/main/kotlin/Day05.kt
[6]: src/main/kotlin/Day06.kt
[7]: src/main/kotlin/Day07.kt
[8]: src/main/kotlin/Day08.kt
[9]: src/main/kotlin/Day09.kt
[10]: src/main/kotlin/Day10.kt
[11]: src/main/kotlin/Day11.kt
[12]: src/main/kotlin/Day12.kt
[13]: src/main/kotlin/Day13.kt
[14]: src/main/kotlin/Day14.kt
[15]: src/main/kotlin/Day15.kt
[16]: src/main/kotlin/Day16.kt
[17]: src/main/kotlin/Day17.kt
[18]: src/main/kotlin/Day18.kt
[19]: src/main/kotlin/Day19.kt
[21]: src/main/kotlin/Day21.kt

[23]: src/main/kotlin/Day23.kt

[Video]: https://www.youtube.com/watch?v=UFa236NO4TU
[vector graphics]: https://www.reddit.com/r/adventofcode/comments/18fgddy/2023_day_10_part_2_using_a_rendering_algorithm_to/
[Day 10 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day10/
[Day 14 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day14/
[visualisation]: src/main/kotlin/Day14Visualisation.kt
[switch]: https://www.reddit.com/r/adventofcode/comments/18jy3tt/2023_day_14_tilting_visualization_with_nintendo/
[Day 16 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day16/
[Day 17 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day17/
[2022/12]: https://github.com/twiederk/AdventOfCode2022/blob/main/src/main/kotlin/Day12.kt
[Video from GODdev]: https://www.youtube.com/watch?v=hxC3MmhyUDM

[Trebuchet?!]: https://adventofcode.com/2023/day/1
[Cube Conundrum]: https://adventofcode.com/2023/day/2
[Gear Ratios]: https://adventofcode.com/2023/day/3
[Scratchcards]: https://adventofcode.com/2023/day/4
[If You Give A Seed A Fertilizer]: https://adventofcode.com/2023/day/5
[Wait For It]: https://adventofcode.com/2023/day/6
[Camel Card]: https://adventofcode.com/2023/day/7
[Haunted Wasteland]: https://adventofcode.com/2023/day/8
[Mirage Maintenance]: https://adventofcode.com/2023/day/9
[Pipe Maze]: https://adventofcode.com/2023/day/10
[Cosmic Expansion]: https://adventofcode.com/2023/day/11
[Hot Springs]: https://adventofcode.com/2023/day/12
[Point of Incidence]: https://adventofcode.com/2023/day/13
[Parabolic Reflector Dish]: https://adventofcode.com/2023/day/14
[Lens Library]: https://adventofcode.com/2023/day/15
[The Floor Will Be Lava]: https://adventofcode.com/2023/day/16
[Clumsy Crucible]: https://adventofcode.com/2023/day/17
[Lavaduct Lagoon]: https://adventofcode.com/2023/day/18
[Aplenty]: https://adventofcode.com/2023/day/19
[Pulse Propagation]: https://adventofcode.com/2023/day/20
[Step Counter]: https://adventofcode.com/2023/day/21
[Sand Slabs]: https://adventofcode.com/2023/day/22
[A Long Walk]: https://adventofcode.com/2023/day/23
[Never Tell Me The Odds]: https://adventofcode.com/2023/day/24
[Snowverload]: https://adventofcode.com/2023/day/25
