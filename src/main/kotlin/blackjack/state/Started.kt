package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.gamer.Hand
import blackjack.domain.gamer.Score

abstract class Started(val hand: Hand = Hand()) : State {
    override fun totalScore(): Score {
        return hand.totalScore()
    }

    override fun cards(): List<Card> {
        return hand.cards
    }
}