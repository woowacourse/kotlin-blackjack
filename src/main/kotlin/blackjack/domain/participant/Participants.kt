package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore
import blackjack.domain.result.GameResults

class Participants(private val dealer: Dealer, private val bettingPlayers: BettingPlayers) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        bettingPlayers.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> = listOf(ParticipantCards(dealer.name, dealer.getFirstOpenCards())) + bettingPlayers.getFirstOpenCards()

    fun takeTurns(deck: CardDeck, onDraw: (Participant) -> Unit) {
        (bettingPlayers.toList().map(BettingPlayer::user) + dealer).forEach {
            it.drawUntilPossible(deck, onDraw)
        }
    }

    fun getCards(): List<ParticipantCards> = listOf(ParticipantCards(dealer.name, dealer.getCards())) + bettingPlayers.getCards()

    fun getTotalScores(): List<ParticipantScore> = listOf(ParticipantScore(dealer.name, dealer.getTotalScore())) + bettingPlayers.getTotalScores()

    fun getParticipantResults(): ParticipantResults {
        val gameResults = judgePlayers()
        return ParticipantResults(gameResults.getDealerResult(dealer.name), gameResults.getPlayerResults(), gameResults.calculateProfits())
    }

    private fun judgePlayers(): GameResults = GameResults(
        bettingPlayers.toList().associateWith { player -> (dealer judge player) }
    )
}
