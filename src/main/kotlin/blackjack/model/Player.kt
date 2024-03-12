package blackjack.model

import blackjack.model.card.CardHand
import blackjack.model.card.CardHandState

data class Player(override val name: PlayerName, override val cardHand: CardHand) : Role(name, cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > BLACK_JACK -> CardHandState.BURST
            sum == BLACK_JACK -> CardHandState.BLACKJACK
            else -> CardHandState.STAY
        }
    }

    companion object {
        private const val BLACK_JACK = 21
    }
}
