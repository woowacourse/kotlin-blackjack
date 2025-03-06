package blackjack.domain.model

class Dealer() : Player(DEALER_NAME, mutableListOf()) {
    fun getPlayerVerdict(players: List<Player>): Map<Player, Verdict> {
        return players.associateWith { player -> Verdict.determine(this, player) }
    }

    fun getDealerVerdicts(players: List<Player>): Map<Verdict, Int> {
        val playersVerdict = players.map { player -> Verdict.determine(this, player) }
        return Verdict.entries.associateWith { verdict ->
            playersVerdict.count { verdict == it.reverse() }
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
