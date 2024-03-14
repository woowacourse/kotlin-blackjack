package blackjack.model

class Dealer(val hand: Hand) : Participant() {
    override fun hit(card: Card) {
        hand.add(card)
    }

    override fun initialSetHand(deck: Deck) {
        repeat(INITIAL_HAND_COUNT) {
            hit(deck.pull())
        }
    }

    override fun hitIfConditionTrue(
        deck: Deck,
        condition: () -> Boolean,
        view: () -> Unit,
    ) {
        while (condition()) {
            hit(deck.pull())
            view()
        }
    }

    fun hitIfConditionTrue(
        deck: Deck,
        view: () -> Unit,
    ) {
        while (canHit()) {
            hit(deck.pull())
            view()
        }
    }

    fun canHit(): Boolean {
        if (hand.isBust()) return false
        return hand.sumOptimized() < HIT_CONDITION
    }

    fun createScoreBoard(players: List<Player>): ScoreBoard {
        val results = comparePoints(players)
        return ScoreBoard.from(results)
    }

    private fun comparePoints(players: List<Player>): List<PlayerResult> {
        val results: List<PlayerResult> =
            players.map { player ->
                comparePointsEachPlayer(player)
            }
        return results
    }

    private fun comparePointsEachPlayer(player: Player): PlayerResult {
        val dealerCards = this.hand
        val playerCards = player.hand
        val compared = playerCards.sumOptimized() compareTo dealerCards.sumOptimized()
        if (playerCards.isBust()) return PlayerResult(player.name, WinningState.LOSS)
        if (dealerCards.isBust()) return PlayerResult(player.name, WinningState.WIN)
        return PlayerResult(player.name, WinningState.from(compared))
    }

    companion object {
        fun createDealer(deck: Deck): Dealer {
            val dealer = Dealer(Hand(listOf()))
            dealer.initialSetHand(deck)
            return dealer
        }

        const val HIT_CONDITION = 17
        const val BLACKJACK_NUMBER = 21
    }
}
