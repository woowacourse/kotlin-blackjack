package domain.state

import constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.card.Card
import domain.card.HandOfCards
import domain.card.strategy.GetAppropriateSum

class FirstTurn(card1: Card, card2: Card) : InProgress() {
    private val handOfCards = HandOfCards(card1, card2)

    override fun nextState(draw: () -> Card): State {
        val cardSum = handOfCards.getTotalCardSum(GetAppropriateSum)
        if (cardSum == BLACK_JACK_NUMBER) return BlackJack()
        return Hit(handOfCards.apply { addCard(draw()) })
    }
}
