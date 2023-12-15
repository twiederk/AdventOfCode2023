# Advent Of Code 2023

## Overview of the puzzles

|  Day | Title                           | Part 1 | Part 2  | Notes                                                                                                                                          |
|-----:|---------------------------------|--------|---------|------------------------------------------------------------------------------------------------------------------------------------------------|
|  [1] | Trebuchet?!                     | SOLVED | SOLVED  |                                                                                                                                                |
|  [2] | Cube Conundrum                  | SOLVED | SOLVED  |                                                                                                                                                |
|  [3] | Gear Ratios                     | SOLVED | SOLVED  |                                                                                                                                                |
|  [4] | Scratchcards                    | SOLVED | SOLVED  |                                                                                                                                                |
|  [5] | If You Give A Seed A Fertilizer | SOLVED | SOLVED  |                                                                                                                                                |
|  [6] | Wait For It                     | SOLVED | SOLVED  |                                                                                                                                                |
|  [7] | Camel Card                      | SOLVED | SOLVED  |                                                                                                                                                |
|  [8] | Haunted Wasteland               | SOLVED | SOLVED  | Find loops and use LCM (see [Video])                                                                                                           |
|  [9] | Mirage Maintenance              | SOLVED | SOLVED  | history.sum() is not the same as history.all { it == 0 }, because of [-1, 1 ]                                                                  |
| [10] | Mirage Maintenance              | SOLVED | SOLVED  | Reviewed solution of from [Day 10 of Todd Ginsberg], in particular form movement. This comment about [vector graphics] helped a lot for part 2 |
| [11] | Cosmic Expansion                | SOLVED | SOLVED  |                                                                                                                                                |
| [12] | Hot Springs                     | SOLVED | NO IDEA | Solved part 1 with brute force. Don't think I will try part 2          .                                                                       |
| [13] | Point of Incidence              | FAILED |         | Tried solving of part 1 two times. Don't think I will try it once more                                                                         |
| [14] | Parabolic Reflector Dish        | SOLVED | SOLVED  | Solved part 1 by myself. Needed research for part two. Found help in this [Video from GODdev] and as always from [Day 14 of Todd Ginsberg].    |   

## Resources

## To Do
* Replace for-loop with streams everywhere
* Day05 optimize performance of part 2
* Day07 refactor strengthWithJoker
* Day14 implement visualisation


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

[Video]: https://www.youtube.com/watch?v=UFa236NO4TU
[vector graphics]: https://www.reddit.com/r/adventofcode/comments/18fgddy/2023_day_10_part_2_using_a_rendering_algorithm_to/
[Day 10 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day10/
[Day 14 of Todd Ginsberg]: https://todd.ginsberg.com/post/advent-of-code/2023/day14/
[Video from GODdev]: https://www.youtube.com/watch?v=hxC3MmhyUDM
