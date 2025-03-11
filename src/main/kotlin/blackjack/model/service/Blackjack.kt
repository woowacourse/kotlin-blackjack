package blackjack.model.service

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.participant.Participants
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerGroup

class Blackjack(private val deck: PlayingCard, private val playerGroup: PlayerGroup) {
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

    fun endGame(): Map<Player, GameResult> {
        return playerGroup.players.associateWith { it.compareScores(playerGroup.dealer) }
    }
}
