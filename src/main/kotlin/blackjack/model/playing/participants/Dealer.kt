package blackjack.model.playing.participants

import blackjack.model.playing.cardhand.CardHand
import blackjack.model.playing.cardhand.CardHandState
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.winning.Betting

data class Dealer(override val cardHand: CardHand) : Role(name = PlayerName(DEALER), cardHand, betting = Betting(0)) {
    override fun canDraw(): Boolean = cardHand.getDealerState() == CardHandState.HIT

    companion object {
        private const val DEALER = "딜러"
    }
}
