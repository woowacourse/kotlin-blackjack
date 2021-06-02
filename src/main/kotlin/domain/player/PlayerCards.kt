package domain.player

import domain.card.Card
import domain.score.Score

data class PlayerCards(val cards: List<Card> = listOf()) {

    private val score = Score(cards)
    private val type = score.type

    fun add(additionCards: List<Card>) = PlayerCards(cards + additionCards)

    fun isWin(other: PlayerCards) = score.isWin(other.score)

    fun isLose(other: PlayerCards) = score.isLose(other.score)

    fun isBlackJack() = type.isBlackJack()

    fun score() = score.value
}
