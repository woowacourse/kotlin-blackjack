package domain.player

import domain.card.Card
import domain.score.Score

data class PlayerCards(val cards: List<Card> = listOf()) {

    private val score = Score(cards)
    private val type = score.type

    fun add(additionCards: List<Card>): PlayerCards {
        return PlayerCards(cards.plus(additionCards))
    }

    fun isWin(other: PlayerCards): Boolean {
        return score.isWin(other.score)
    }

    fun isLose(other: PlayerCards): Boolean {
        return score.isLose(other.score)
    }

    fun isBlackJack(): Boolean {
        return type.isBlackJack()
    }

    fun score(): Int {
        return this.score.value
    }
}