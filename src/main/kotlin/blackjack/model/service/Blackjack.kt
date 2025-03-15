package blackjack.model.service

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Participants
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerGroup

class Blackjack(private val deck: PlayingCard, private val playerGroup: PlayerGroup) {
    fun initGame() {
        playerGroup.players.forEach { player ->
            distributeStartingHands(player)
            player.hand.isBlackJack()
        }
        distributeStartingHands(playerGroup.dealer)
        playerGroup.dealer.hand.isBlackJack()
    }

    private fun distributeStartingHands(player: Participants) {
        player.receiveCard(deck.spreadCard(2))
    }

    fun hitAction(participants: Participants) {
        participants.receiveCard(deck.spreadCard(1))
    }

    fun drawUntilThreshold(dealer: Dealer): Int {
        var count: Int = 0
        while (!dealer.canHit()) {
            hitAction(dealer)
            count++
        }
        return count
    }

    fun endGame(): Map<Player, GameResult> {
        val dealerResult = playerGroup.dealer.sumCardNumber

        return playerGroup.players.associateWith { player ->
            player.compareScores(playerGroup.dealer.hand.status, dealerResult)
        }
    }

    companion object {
        const val BUST_STANDARD: Int = 21
    }
}
