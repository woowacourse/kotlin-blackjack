package blackjack.domain.model

class Dealer(name: String, cards: List<Card>) : Participant(name, cards) {
    private var initialHandShown: Boolean = false

    constructor(cards: List<Card>) : this(DEFAULT_NAME, cards)

    constructor(vararg cards: Card) : this(DEFAULT_NAME, cards.toList())

    constructor(name: String, vararg cards: Card) : this(name, cards.toList())

    override fun canHit(): Boolean {
        return (computePoint() <= HIT_THRESHOLD)
    }

    override fun showHand(): List<Card> {
        if (initialHandShown) return hand.show()
        initialHandShown = true
        return hand.show(INITIAL_VISIBLE_CARD_COUNT)
    }

    fun processHits(
        deck: Deck,
        printStatus: (Dealer) -> Unit,
    ) {
        while (canHit()) {
            printStatus(this)
            accept(deck.draw())
        }
    }

    fun getPlayerResults(players: List<Player>): Map<Player, Result> {
        return players.associateWith { player -> player.compareAgainst(this) }
    }

    fun getDealerResults(playerResults: Map<Player, Result>): Map<Result, Int> {
        return Result.entries.associateWith { result ->
            playerResults.values.count { playerResult -> result == playerResult.reverse() }
        }
    }

    fun getPlayersProfits(players: List<Player>): Map<Player, Int> {
        return players.associateWith { player -> player.computeProfitAgainst(this) }
    }

    fun getDealerProfit(playersProfits: Map<Player, Int>): Int {
        return -1 * playersProfits.values.sum()
    }

    companion object {
        const val HIT_THRESHOLD = 16

        private const val DEFAULT_NAME = "딜러"
        private const val INITIAL_VISIBLE_CARD_COUNT = 1
    }
}
