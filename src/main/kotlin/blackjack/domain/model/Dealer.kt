package blackjack.domain.model

class Dealer() : Player(DEALER_NAME, mutableListOf()) {
    fun getPlayerVerdict(players: List<Player>): Map<Player, Verdict> {
        val dealerScore = getScore()
        return players.associateWith { player -> Verdict.determine(player.getScore(), dealerScore) }
    }

    fun getDealerVerdicts(players: List<Player>): List<Verdict> {
        val dealerScore = getScore()
        return players.map { player -> Verdict.determine(dealerScore, player.getScore()) }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
