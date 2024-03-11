package blackjack.model

class Dealer(hand: Hand) : Participant(hand) {
    fun canHit(): Boolean {
        if (hand.isBust()) return false
        return hand.sumOptimized() < HIT_CONDITION
    }

    fun createScoreBoard(players: List<Player>): ScoreBoard {
        val results: List<PlayerResult> =
            players.map { player ->
                PlayerResult(player.name, player.comparePoints(this))
            }
        return ScoreBoard.from(results)
    }

    companion object {
        const val HIT_CONDITION = 17
        const val BLACKJACK_NUMBER = 21
    }
}
