package blackjack.model.playing.participants

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.participants.player.PlayerName

data class Dealer(override val cardHand: CardHand) : Role(name = PlayerName(DEALER), cardHand) {
    override fun canDraw(): Boolean = cardHand.calculateScore() < DEALER_MAX_HIT_SUM

    companion object {
        private const val DEALER = "딜러"
        private const val DEALER_MAX_HIT_SUM = 16
    }
}
