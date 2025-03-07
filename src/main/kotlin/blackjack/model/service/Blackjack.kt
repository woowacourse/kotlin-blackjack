package blackjack.model.service

import blackjack.model.domain.ActionType
import blackjack.model.domain.Dealer
import blackjack.model.domain.Deck
import blackjack.model.domain.ParticipantStatus
import blackjack.model.domain.Participants
import blackjack.model.domain.Player

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

    fun shouldStopDrawing(
        playerAction: ActionType,
        player: Player,
    ): Boolean {
        when (playerAction) {
            ActionType.Hit -> hitAction(player)
            ActionType.Stay -> return true
        }
        return false
    }

    private fun hitAction(player: Player) {
        player.receiveCard(deck.spreadCard())
        player.checkBust()
    }

    fun drawUntilThreshold(dealer: Dealer): Int {
        var count: Int = 0
        while (!dealer.overThreshold()) {
            dealer.receiveCard(deck.spreadCard())
            count++
        }
        return count
    }

    fun endGame(
        players: List<Player>,
        dealer: Dealer,
    ) {
        if (dealer.status == ParticipantStatus.Bust) return

        val dealerResult = dealer.sumCardNumber

        players.forEach { player ->
            player.compareScores(dealerResult)
        }
    }

    companion object {
        const val BUST_STANDARD: Int = 21
        const val THRESHOLD: Int = 16
    }
}
