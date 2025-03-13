package blackjack.domain.model

class Dealer(name: String = DEFAULT_NAME) : Participant(name) {
    private var initialHandShown: Boolean = false

    constructor(name: String, vararg cards: Card) : this(name) {
        accept(cards.toList())
    }

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

    fun getPlayersProfits(playerResults: Map<Player, Result>): Map<Player, Int> {
        val playerProfits: Map<Player, Int> =
            playerResults.mapValues { (player, result) ->
                Math.round(player.bet * result.profitRate).toInt()
            }
        return playerProfits
    }

    companion object {
        const val HIT_THRESHOLD = 16

        private const val DEFAULT_NAME = "딜러"
        private const val INITIAL_VISIBLE_CARD_COUNT = 1
    }
}
