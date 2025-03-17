package blackjack.model.user

import blackjack.model.GameJudge
import blackjack.model.Hand
import blackjack.model.card.Card
import blackjack.model.state.GameStatus

open class Participant(
    val name: String,
) {
    val hand = Hand()

    fun addCard(card: Card) = hand.addCard(card)

    fun isBust(): Boolean = GameJudge.judge(hand.cards) == GameStatus.BUST
}
