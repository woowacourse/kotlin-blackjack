package blackjack.domain.service

import blackjack.domain.model.BetAmount
import blackjack.domain.model.Proceed
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.participant.Participants
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.PlayerGroup

class Blackjack(
    private val deck: PlayingCard,
    val playerGroup: PlayerGroup,
) {
    fun initGame() {
        playerGroup.participants.forEach { player ->
            distributeStartingHands(player)
        }
    }

    private fun distributeStartingHands(player: Participants) {
        repeat(2) {
            player.receiveCard(deck.spreadCard())
        }
    }

    fun hitAction(player: Player) {
        player.receiveCard(deck.spreadCard())
    }

    fun drawUntilThreshold(): Int {
        var count: Int = 0
        while (playerGroup.dealer.canHit()) {
            playerGroup.dealer.receiveCard(deck.spreadCard())
            count++
        }
        return count
    }

    fun endGame(betStatus: Map<Player, BetAmount>): Map<Player, Proceed> {
        return playerGroup.players.associateWith { player ->
            val result = player.compareScores(playerGroup.dealer)
            val proceed = betStatus[player]?.calculateProceed(result, player.hand.isBlackjack()) ?: throw IllegalArgumentException()
            Proceed(proceed)
        }
    }
}
