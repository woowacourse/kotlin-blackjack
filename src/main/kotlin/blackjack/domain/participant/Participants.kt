package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.result.GameResults

class Participants(private val dealer: Dealer, private val bettingPlayers: BettingPlayers) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        bettingPlayers.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> {
        return listOf(ParticipantCards(dealer.name, dealer.getFirstOpenCards())) + bettingPlayers.getFirstOpenCards()
    }

    fun takeTurns(deck: CardDeck, onDraw: (Participant) -> Unit) {
        (bettingPlayers.players.map(BettingPlayer::user) + dealer).forEach {
            it.drawUntilPossible(deck, onDraw)
        }
    }

    fun getParticipantResults(): ParticipantResults = judgePlayers().getResults()

    private fun judgePlayers(): GameResults = GameResults(
        dealer,
        bettingPlayers.players.associateWith { player -> (dealer judge player) }
    )
}
