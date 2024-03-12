package blackjack.model

import blackjack.model.CardNumber.Companion.convertCardNumber
import blackjack.model.CardSymbol.Companion.convertCardSymbol

class Card(val number: CardNumber, private val symbol: CardSymbol) {
    fun convertCard(): String {
        return convertCardNumber(number) + convertCardSymbol(symbol)
    }
}
