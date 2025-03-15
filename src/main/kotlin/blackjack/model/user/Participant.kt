package blackjack.model.user

import blackjack.model.GameJudge
import blackjack.model.card.Card
import blackjack.model.state.GameStatus

open class Participant(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards get() = _cards.toList()

    fun addCard(card: Card) = _cards.add(card)

    fun isBust(): Boolean = GameJudge.judge(cards) == GameStatus.BUST
}
