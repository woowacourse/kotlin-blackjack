package domain.participant

import domain.card.Card
import domain.card.Cards
import domain.card.Score

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun showInitCards(): List<Card>
    abstract fun isPossibleDrawCard(): Boolean
    fun getScore(): Score {
        return cards.getScore()
    }
}
