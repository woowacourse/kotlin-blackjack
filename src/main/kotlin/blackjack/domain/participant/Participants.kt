package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.result.GameResults

class Participants(private val dealer: Dealer, private val players: Players) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        players.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> {
        return listOf(ParticipantCards(dealer.name, dealer.getFirstOpenCards())) + players.getFirstOpenCards()
    }

    fun takeTurns(deck: CardDeck, onDraw: (Participant) -> Unit) {
        (players.users + dealer).forEach {
            it.drawUntilPossible(deck, onDraw)
        }
    }

    fun getParticipantResults(): ParticipantResults = judgePlayers().getResults()

    private fun judgePlayers(): GameResults = GameResults(
        dealer,
        players.users.associateWith { player -> (dealer judge player) }
    )
}
