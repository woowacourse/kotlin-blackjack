package blackjack.domain.participant

import blackjack.domain.GameResult
import blackjack.domain.ParticipantCards
import blackjack.domain.ParticipantCards.Companion.BUST_STANDARD
import blackjack.domain.card.TrumpCard
import blackjack.domain.deck.Deck

class Player(
    val name: String,
    cards: ParticipantCards,
) : Participant(cards) {
    override fun showInitialCards(): List<TrumpCard> = takeCards(PLAYER_INITIAL_CARD_COUNT)

    override fun isDrawable(): Boolean = cards.sumOfCards <= BUST_STANDARD

    fun choice(
        deck: Deck,
        getPlayerChoice: (String) -> Boolean,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        if (!isDrawable()) return

        if (getPlayerChoice(name)) {
            receiveCard(deck.pop())
            onPlayerStateUpdated(this)
            choice(deck, getPlayerChoice, onPlayerStateUpdated)
        }
    }

    override fun getResult(other: Participant): GameResult =
        when {
            cards.isBust() -> GameResult.LOSE
            else -> super.getResult(other)
        }

    companion object {
        private const val PLAYER_INITIAL_CARD_COUNT = 2
    }
}
