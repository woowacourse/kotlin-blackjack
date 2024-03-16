package blackjack.model

data class Card(val number: CardNumber, private val symbol: CardSymbol) {
    override fun toString(): String {
        return number.label + symbol.label
    }
}
