package blackjack.model

class Card(val number: CardNumber, private val symbol: CardSymbol) {
    fun convertCard(): String {
        return number.label + symbol.label
    }
}
