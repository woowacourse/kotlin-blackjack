package blackjack.model

import blackjack.model.card.CardHand
import blackjack.model.card.CardHandState

data class Dealer(override val cardHand: CardHand) : Role(name = PlayerName(DEALER), cardHand) {
    override fun getState(): CardHandState {
        val sum = cardHand.sum()

        return when {
            sum > BLACK_JACK -> CardHandState.BURST
            sum == BLACK_JACK -> CardHandState.BLACKJACK
            sum <= DEALER_MAX_HIT_SUM -> CardHandState.HIT
            else -> CardHandState.STAY
        }
    }

    companion object {
        private const val DEALER = "딜러"
        private const val BLACK_JACK = 21
        private const val DEALER_MAX_HIT_SUM = 16
    }
}
