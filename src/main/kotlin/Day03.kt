import java.nio.file.Path

typealias EngineSchematic = List<String>

class Day03 {

    fun loadData(path: Path): EngineSchematic {
        return Resources.resourceAsListOfString(path.toFile().name)
    }

    fun isSymbol(char: Char): Boolean {
        return !(char in '0'..'9' || char == '.')
    }

    fun findSymbols(line: String, x: Int): List<Symbol> {
        val symbolList = mutableListOf<Symbol>()
        for ((index, symbol) in line.withIndex()) {
            if (isSymbol(symbol)) {
                symbolList.add(Symbol(x, index, symbol))
            }
        }
        return symbolList
    }

    fun findAllSymbols(engineSchematic: List<String>): List<Symbol> {
        val symbolList = mutableListOf<Symbol>()
        for ((index, line) in engineSchematic.withIndex()) {
            symbolList.addAll(findSymbols(line, index))
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
            }
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


}

data class Symbol(
    val x: Int,
    val y: Int,
    val symbol: Char
) {
    fun neighborCoords(): List<Point> {
        return listOf(
            Point(x - 1, y - 1),
            Point(x, y - 1),
            Point(x + 1, y - 1),

            Point(x - 1, y),
            Point(x + 1, y),

            Point(x - 1, y + 1),
            Point(x, y + 1),
            Point(x + 1, y + 1),
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