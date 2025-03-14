package blackjack.model.user

import blackjack.model.ScoreCalculator
import blackjack.model.card.Card
import blackjack.model.state.GameStatus

open class Participant(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()
    val gameStatus: GameStatus
        get() = GameStatus.of(ScoreCalculator.calculateOptimalSum(cards), cards.size)

    fun addCard(card: Card) = _cards.add(card)

    fun isBust(): Boolean = gameStatus == GameStatus.BUST
}
