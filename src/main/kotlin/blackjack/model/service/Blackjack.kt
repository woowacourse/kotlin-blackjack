package blackjack.model.service

import blackjack.model.domain.Deck
import blackjack.model.domain.Participants

class Blackjack {
    fun initGame(
        players: List<Participants>,
        deck: Deck,
    ) {
        players.forEach { player ->
            distributeStartingHands(player, deck)
        }
    }

    private fun distributeStartingHands(
        player: Participants,
        deck: Deck,
    ) {
        repeat(2) {
            player.receiveCard(deck.spreadCard())
        }
    }

    companion object {
        const val BUST_STANDARD: Int = 21
        const val THRESHOLD: Int = 16
    }
}
