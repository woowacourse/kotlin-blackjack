package blackjack.model.service

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Participants
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerGroup

class Blackjack(private val deck: PlayingCard) {
    fun initGame(players: List<Participants>) {
        players.forEach { player ->
            distributeStartingHands(player)
        }
    }

    private fun distributeStartingHands(player: Participants) {
        player.receiveCard(deck.spreadCard(2))
    }

    fun hitAction(player: Player) {
        player.receiveCard(deck.spreadCard(1))
    }

    fun drawUntilThreshold(dealer: Dealer): Int {
        var count: Int = 0
        while (dealer.canHit()) {
            dealer.receiveCard(deck.spreadCard(1))
            count++
        }
        return count
    }

    fun endGame(playerGroup: PlayerGroup): Map<Player, GameResult> {
        val dealerResult = playerGroup.dealer.sumCardNumber

        return playerGroup.players.associateWith { player ->
            player.compareScores(playerGroup.dealer.checkBust(), dealerResult)
        }
    }

    companion object {
        const val BUST_STANDARD: Int = 21
    }
}
