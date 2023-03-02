package domain.person

import domain.card.Card
import domain.constant.BLACK_JACK

class Player(override val name: String) : Person() {
    override fun receiveCard(card: Card) {
        super.receiveCard(card)
        checkBust()
    }

    private fun checkBust() {
        if (calculateMinTotal() > BLACK_JACK) gameState = GameState.BUST
    }

    private fun calculateMinTotal() = calculateSumExceptAce() + countAce()

    fun rejectReceiveCard() {
        gameState = GameState.STAND
    }
}
