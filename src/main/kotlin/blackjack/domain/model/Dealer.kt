package blackjack.domain.model

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    private var initialHandShown: Boolean = false

    constructor(name: String = DEALER_NAME, cards: List<Card>) : this(name) {
        accept(cards)
    }

    override fun canHit(): Boolean {
        return (computePoint() <= DEALER_HIT_THRESHOLD)
    }

    override fun showHand(): List<Card> {
        if (initialHandShown) return hand.show()
        initialHandShown = true
        return hand.show(DEALER_INITIAL_VISIBLE_CARD_COUNT)
    }

    fun getPlayerResult(players: List<Player>): Map<Player, Result> {
        return players.associateWith { player -> player.compareAgainst(this) }
    }

    fun getDealerResults(playerResults: Map<Player, Result>): Map<Result, Int> {
        return Result.entries.associateWith { result ->
            playerResults.values.count { playerResult -> result == playerResult.reverse() }
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_THRESHOLD = 16
        private const val DEALER_INITIAL_VISIBLE_CARD_COUNT = 1
    }
}
