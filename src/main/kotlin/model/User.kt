package model

import entity.Cards

open class User(val cards: Cards) {
    fun cardsNumberSum(): Int {
        return cards.sumOfNumbers()
    }
}
