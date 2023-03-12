package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.result.ResultManager

class Participants(private val dealer: Dealer, private val bettingPlayers: BettingPlayers) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        bettingPlayers.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> {
        return listOf(ParticipantCards(dealer.name, dealer.getFirstOpenCards())) + bettingPlayers.getFirstOpenCards()
    }

    fun takeTurns(deck: CardDeck, onDraw: (Participant) -> Unit) {
        (bettingPlayers.players + dealer).forEach {
            it.drawUntilPossible(deck, onDraw)
        }
    }

    fun getParticipantResults(): ParticipantResults = ResultManager(dealer, bettingPlayers).judge()
}
