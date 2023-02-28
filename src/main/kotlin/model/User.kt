package model

import entity.Cards

class User(val cards: Cards) {
    fun cardsNumberSum(): Int {
        return cards.sumOfNumbers()
    }
}
