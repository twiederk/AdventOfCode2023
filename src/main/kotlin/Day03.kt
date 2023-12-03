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

fun main() {

}