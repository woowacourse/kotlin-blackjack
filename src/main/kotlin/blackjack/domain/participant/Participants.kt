package blackjack.domain.participant

import blackjack.domain.card.CardDeck
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantScore
import blackjack.domain.result.PlayerResults

class Participants(private val dealer: Dealer, private val bettingPlayers: BettingPlayers) {
    fun drawAll(deck: CardDeck) {
        dealer.addCard(deck.draw())
        bettingPlayers.drawAll(deck)
    }

    fun getFirstOpenCards(): List<ParticipantCards> = listOf(ParticipantCards(dealer.name, dealer.getFirstOpenCards())) + bettingPlayers.getFirstOpenCards()

    fun getPlayers(): List<BettingPlayer> = bettingPlayers.toList()

    fun drawDealerCard(deck: CardDeck, block: (Boolean) -> Unit) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw())
            block(true)
        }
        block(false)
    }

    fun getCards(): List<ParticipantCards> = listOf(ParticipantCards(dealer.name, dealer.getCards())) + bettingPlayers.getCards()

    fun getTotalScores(): List<ParticipantScore> = listOf(ParticipantScore(dealer.name, dealer.getTotalScore())) + bettingPlayers.getTotalScores()

    fun judgePlayers(): PlayerResults = PlayerResults(
        bettingPlayers.toList().associate { player ->
            player to (dealer judge player)
        }
    )
}
