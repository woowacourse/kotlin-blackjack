package blackjack.model

import blackjack.model.card.Card

abstract class Participant(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()
    val gameStatus: GameStatus
        get() = GameStatus.of(ScoreCalculator.sum(cards), cards.size)

    fun addCard(card: Card) = _cards.add(card)

    abstract fun isBust(): Boolean
}
