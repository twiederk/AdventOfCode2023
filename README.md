# Advent Of Code 2023

## Overview of the puzzles

|  Day | Title                             | Part 1 | Part 2      | Notes                                                                                                                                                                                                                                             |
|-----:|-----------------------------------|--------|-------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|  [1] | [Trebuchet?!]                     | SOLVED | SOLVED      | Scan string to find digits and words                                                                                                                                                                                                              |
|  [2] | [Cube Conundrum]                  | SOLVED | SOLVED      | Colored cubes in a bag must be turned in a data model of lists and maps                                                                                                                                                                           |
|  [3] | [Gear Ratios]                     | SOLVED | SOLVED      | Scan list of strings for numbers which are attached to special characters (symbols) in the same line or the line above or below                                                                                                                   |
|  [4] | [Scratchcards]                    | SOLVED | SOLVED      | Count numbers in lists (part 1). Generate objects using recursion.                                                                                                                                                                                |
|  [5] | [If You Give A Seed A Fertilizer] | SOLVED | SOLVED      | Ranges, ranges and ranges to avoid out of memory problems.                                                                                                                                                                                        |
|  [6] | [Wait For It]                     | SOLVED | SOLVED      | Easy for beginners with simple calculation.                                                                                                                                                                                                       |
|  [7] | [Camel Card]                      | SOLVED | SOLVED      | Card game like poker. Nice puzzle, even for beginners.                                                                                                                                                                                            |
|  [8] | [Haunted Wasteland]               | SOLVED | SOLVED      | Graph puzzle. Find loops and use LCM (see [Video])                                                                                                                                                                                                |
|  [9] | [Mirage Maintenance]              | SOLVED | SOLVED      | Nice puzzle for recursion (pyramid top down). history.sum() is not the same as history.all { it == 0 }, because of [-1, 1 ]                                                                                                                       |
| [10] | [Pipe Maze]                       | SOLVED | SOLVED      | First 2D puzzle. Reviewed solution of from [Day 10 of Todd Ginsberg], in particular form movement. This comment about [vector graphics] helped a lot for part 2                                                                                   |
| [11] | [Cosmic Expansion]                | SOLVED | SOLVED      | Nice puzzle to insert elements in lists in part 1, to realize in part 2, that there is a much simpler solution based on Manhatten distance.                                                                                                       |
| [12] | [Hot Springs]                     | SOLVED | NO IDEA     | Pattern matching and finding all possible matches. Solved part 1 with brute force. Don't think I will try part 2          .                                                                                                                       |
| [13] | [Point of Incidence]              | FAILED | NOT STARTED | Find reflections (mirrors) in 2D grid. Tried solving of part 1 two times. Don't think I will try it once more                                                                                                                                     |
| [14] | [Parabolic Reflector Dish]        | SOLVED | SOLVED      | Nice puzzle about gravity, even implemented [visualisation]. Solved part 1 by myself. Needed research for part two. Found help in this [Video from GODdev] and as always from [Day 14 of Todd Ginsberg].                                          |
| [15] | [Lens Library]                    | SOLVED | SOLVED      | Implementation of HASH algorithm (part 1). Create data model to represent a HASHMAP (part 2)                                                                                                                                                      |   
| [16] | [The Floor Will Be Lava]          | SOLVED | SOLVED      | 2D puzzle with loops. Needed help to realize that the beams could fully loop back on themselves. Again the solution form [Day 16 of Todd Ginsberg] gave me the hint. Used brute force for part 2. Luckily first edge I checked was the right one. |   

## Resources

## To Do
* Replace for-loop with streams everywhere
* Day05 optimize performance of part 2
* Day07 refactor strengthWithJoker


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

[Video]: https://www.youtube.com/watch?v=UFa236NO4TU
[vector graphics]: https://www.reddit.com/r/adventofcode/comments/18fgddy/2023_day_10_part_2_using_a_rendering_algorithm_to/
[Day 10 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day10/
[Day 14 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day14/

[visualisation]: src/main/kotlin/Day14Visualisation.kt
[Day 16 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day16/
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
