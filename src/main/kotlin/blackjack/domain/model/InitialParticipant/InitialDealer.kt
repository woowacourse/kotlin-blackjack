import blackjack.domain.model.Card
import blackjack.domain.model.Hands
import blackjack.domain.model.playing.PlayingDealer

class InitialDealer(override var hands: Hands, override val name: String = DEALER_NAME) : InitialParticipant() {
    override fun showInitCards(): List<Card> = showCards(INIT_VISIBLE_CARD_COUNT)

    fun toPlayingDealer() = PlayingDealer(hands, name)

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val INIT_VISIBLE_CARD_COUNT = 1
    }
}
