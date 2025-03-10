package blackjack.domain

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Participant
import blackjack.domain.participants.Player
import blackjack.domain.state.GameState

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

    fun askHit(onHit: (Player) -> Unit) {
        players.forEach { player ->
            askHitForEachPlayer(player, onHit)
        }
    }

    fun processDealerHit(): Boolean {
        if (dealer.shouldHit()) {
            handOutCard(dealer)
            return true
        }
        return false
    }

    fun matchResult(): GameResult = GameResult.create(dealer, players)

    private fun askHitForEachPlayer(
        player: Player,
        onHit: (Player) -> Unit,
    ) {
        if (player.gameState == GameState.BLACKJACK) return
        processTurn(player, onHit)
    }

    private fun processTurn(
        player: Player,
        onHit: (Player) -> Unit,
    ) {
        if (player.gameState == GameState.FIRST_TURN) {
            onHit(player)
        }
        while (player.shouldHit()) {
            handOutCard(player)
            onHit(player)
        }
    }

    private fun handOutCard(participant: Participant) {
        repeat(participant.getDrawAmount()) {
            val card = dealer.draw()
            participant.addCard(card)
        }
    }
}
