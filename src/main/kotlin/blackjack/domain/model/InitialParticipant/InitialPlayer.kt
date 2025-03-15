
import blackjack.domain.model.Card
import blackjack.domain.model.Hands
import blackjack.domain.model.playing.PlayingPlayer

class InitialPlayer(override var hands: Hands, override val name: String) : InitialParticipant() {
    override fun showInitCards(): List<Card> = showCards(INIT_VISIBLE_CARD_COUNT)

    fun toPlayingPlayer() = PlayingPlayer(hands, name)

    companion object {
        private const val INIT_VISIBLE_CARD_COUNT = 2
    }
}
