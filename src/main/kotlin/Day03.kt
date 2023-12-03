import java.nio.file.Path

typealias EngineSchematic = List<String>

class Day03 {

    fun loadData(path: Path): EngineSchematic {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun isSymbol(char: Char): Boolean {
        return !(char in '0'..'9' || char == '.')
    }

    fun findSymbolsInLine(line: String, y: Int): List<Symbol> {
        val symbolList = mutableListOf<Symbol>()
        for ((index, symbol) in line.withIndex()) {
            if (isSymbol(symbol)) {
                symbolList.add(Symbol(index, y, symbol))
            }
        }
        return symbolList
    }

    fun findAllSymbolsInEngineSchematic(engineSchematic: List<String>): List<Symbol> {
        val symbolList = mutableListOf<Symbol>()
        for ((y, line) in engineSchematic.withIndex()) {
            symbolList.addAll(findSymbolsInLine(line, y))
        }
        return symbolList
    }

    fun scanPartNumber(engineSchematic: EngineSchematic, pointOfNumber: Point): PartNumber {
        val line = engineSchematic[pointOfNumber.y]
        var result = ""
        var x = pointOfNumber.x
        for (indexLeft in pointOfNumber.x downTo 0) {
            val char = line[indexLeft]
            if (char.isDigit()) {
                result = "$char$result"
                x = indexLeft
                continue
            }
            break
        }
        for (indexRight in (pointOfNumber.x + 1)..<line.length) {
            val char = line[indexRight]
            if (char.isDigit()) {
                result = "$result$char"
                continue
            }
            break
        }
        return PartNumber(Point(x, pointOfNumber.y), result.toInt())
    }

    fun scanPartNumbers(engineSchematic: List<String>, neighborNumbers: List<Point>): Set<PartNumber> {
        return neighborNumbers.map { scanPartNumber(engineSchematic, it) }.toSet()
    }

    fun sumPartNumbers(partNumbers: Set<PartNumber>): Int {
        return partNumbers.sumOf { it.number }
    }

    fun executePart1(engineSchematic: EngineSchematic): Int {
        val symbolList = findAllSymbolsInEngineSchematic(engineSchematic)
        val neighborNumbers = symbolList.flatMap { it.neighborNumbers(engineSchematic) }
        val partNumbers = scanPartNumbers(engineSchematic, neighborNumbers)
        println(partNumbers)
        return sumPartNumbers(partNumbers)

    }

    fun findAllPartNumbers(engineSchematic: List<String>): Set<PartNumber> {
        val symbolList = findAllSymbolsInEngineSchematic(engineSchematic)
        val neighborNumbers = symbolList.flatMap { it.neighborNumbers(engineSchematic) }
        return scanPartNumbers(engineSchematic, neighborNumbers)
    }

}

data class Symbol(
    val column: Int,
    val row: Int,
    val symbol: Char
) {
    fun neighborCoords(): List<Point> {
        return listOf(
            Point(column - 1, row - 1),
            Point(column, row - 1),
            Point(column + 1, row - 1),

            Point(column - 1, row),
            Point(column + 1, row),

            Point(column - 1, row + 1),
            Point(column, row + 1),
            Point(column + 1, row + 1),
        )
    }

    fun neighborNumbers(engineSchematic: List<String>): List<Point> {
        val neighborNumbers = mutableListOf<Point>()
        for (neighbor in neighborCoords()) {
            if (engineSchematic[neighbor.y][neighbor.x].isDigit()) {
                neighborNumbers.add(Point(neighbor.x, neighbor.y))
            }
        }
        return neighborNumbers
    }

}


data class PartNumber(
    val coords: Point,
    val number: Int
)


fun main() {

}