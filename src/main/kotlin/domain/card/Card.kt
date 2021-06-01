package domain.card

import domain.gamer.Score

class Card(val symbol: Symbol, val value: Value) {
    fun name(): String {
        return value.valueName + symbol.symbol
    }

    fun score(): Score {
        return value.score
    }

    fun isAce(): Boolean {
        return value == Value.ACE
    }
}