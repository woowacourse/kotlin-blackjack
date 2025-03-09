package blackjack.domain.model

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    constructor(name: String, cards: List<Card>) : this(name) {
        accept(cards)
    }

    fun getPlayerVerdict(players: List<Player>): Map<Player, Verdict> {
        return players.associateWith { player -> player.compareAgainst(this) }
    }

    fun getDealerVerdicts(playerVerdicts: Map<Player, Verdict>): Map<Verdict, Int> {
        return Verdict.entries.associateWith { verdict ->
            playerVerdicts.values.count { playerVerdict -> verdict == playerVerdict.reverse() }
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
