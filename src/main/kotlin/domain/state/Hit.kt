package domain.state

import constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.card.Card
import domain.card.HandOfCards
import domain.card.strategy.GetAppropriateSum

class Hit(val handOfCards: HandOfCards) : InProgress() {
    override fun nextState(draw: () -> Card): State {
        handOfCards.addCard(draw())
        val cardSum = handOfCards.getTotalCardSum(GetAppropriateSum)
        if (cardSum > BLACK_JACK_NUMBER) return Bust()
        return this
    }
}
