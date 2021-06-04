package domain.card

class Card(val symbol: Symbol, val value: Value) {
    fun name(): String {
        return value.valueName + symbol.symbol
    }
}
