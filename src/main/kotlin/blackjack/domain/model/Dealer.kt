package blackjack.domain.model

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    constructor(name: String, cards: List<Card>) : this(name) {
        accept(cards)
    }

    fun getPlayerResult(players: List<Player>): Map<Player, Result> {
        return players.associateWith { player -> player.compareAgainst(this) }
    }

    fun getDealerResults(playerResults: Map<Player, Result>): Map<Result, Int> {
        return Result.entries.associateWith { result ->
            playerResults.values.count { playerVResult -> result == playerVResult.reverse() }
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_DRAW_THRESHOLD = 16
    }
}
