package blackjack.domain

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Participant
import blackjack.domain.participants.Player

class Game(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        players.forEach { player ->
            handOutCard(player)
        }
        handOutCard(dealer)
    }

    fun askHit(
        decideHit: (String) -> Boolean,
        onHit: (Player) -> Unit,
    ) {
        players.forEach { player ->
            askHitForEachPlayer(player, decideHit, onHit)
        }
    }

    fun processDealerHit(): Boolean {
        if (dealer.canHit()) {
            handOutCard(dealer)
            return true
        }
        return false
    }

    private fun askHitForEachPlayer(
        player: Player,
        decideHit: (String) -> Boolean,
        onHit: (Player) -> Unit,
    ) {
        if (player.isBlackjack()) return
        processTurn(player, decideHit, onHit)
    }

    private fun processTurn(
        player: Player,
        decideHit: (String) -> Boolean,
        onHit: (Player) -> Unit,
    ) {
        if (player.isBust()) {
            onHit(player)
        }
        while (player.canHit() && decideHit(player.name)) {
            handOutCard(player)
            onHit(player)
        }
    }

    private fun handOutCard(participant: Participant) {
        repeat(participant.getDrawAmount()) {
            dealer.handOut(participant)
        }
    }
}
