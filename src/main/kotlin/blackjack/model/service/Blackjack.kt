package blackjack.model.service

import blackjack.model.domain.Dealer
import blackjack.model.domain.Deck
import blackjack.model.domain.Participants
import blackjack.model.domain.Player
import blackjack.model.domain.Status

class Blackjack(private val deck: Deck) {
    fun initGame(players: List<Participants>) {
        players.forEach { player ->
            distributeStartingHands(player)
        }
    }

    private fun distributeStartingHands(player: Participants) {
        repeat(2) {
            player.receiveCard(deck.spreadCard())
        }
    }

    fun endGame(
        players: List<Player>,
        dealer: Dealer,
    ) {
        if (dealer.status == Status.Bust) return

        val dealerResult = dealer.sumCardNumber

        players.forEach { player ->
            player.isAlive(dealerResult)
        }
    }

    companion object {
        const val BUST_STANDARD: Int = 21
        const val THRESHOLD: Int = 16
    }
}
