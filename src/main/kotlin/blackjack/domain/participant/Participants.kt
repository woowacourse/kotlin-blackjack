package blackjack.domain.participant

import blackjack.domain.card.Card
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

    fun takeTurns(deck: CardDeck, askDraw: (String) -> Boolean, onPlayerDraw: (String, List<Card>) -> Unit, onDealerDraw: (String) -> Unit) {
        takePlayerTurns(deck, askDraw, onPlayerDraw)
        takeDealerTurn(deck, onDealerDraw)
    }

    private fun takePlayerTurns(deck: CardDeck, askDraw: (String) -> Boolean, onDraw: (String, List<Card>) -> Unit) {
        bettingPlayers.toList().forEach {
            takePlayerTurn(deck, it, askDraw, onDraw)
        }
    }

    private fun takePlayerTurn(deck: CardDeck, player: BettingPlayer, askDraw: (String) -> Boolean, onDraw: (String, List<Card>) -> Unit) {
        if (player.canDraw()) {
            val isDraw = askDraw(player.getName())
            if (!isDraw) return onDraw(player.getName(), player.player.getCards())

            player.draw(deck)
            onDraw(player.getName(), player.player.getCards())
            takePlayerTurn(deck, player, askDraw, onDraw)
        }
    }

    private fun takeDealerTurn(deck: CardDeck, onDraw: (String) -> Unit) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw())
            onDraw(dealer.name)
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
