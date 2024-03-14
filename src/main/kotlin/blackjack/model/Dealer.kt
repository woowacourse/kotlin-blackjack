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

    override fun hitWhileConditionTrue(
        deck: Deck,
        condition: () -> Boolean,
        view: () -> Unit,
    ) {
        while (condition()) {
            hit(deck.pull())
            view()
        }
    }

    fun hitWhileConditionTrue(
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
        val dealerHand = this.hand
        val playerHand = player.hand
        if (playerHand.isBust()) return PlayerResult(player.name, WinningState.LOSS)
        if (dealerHand.isBust()) return PlayerResult(player.name, WinningState.WIN)
        if (playerHand.sumOptimized() == BLACKJACK_NUMBER && dealerHand.sumOptimized() == BLACKJACK_NUMBER) {
            val playerWinningState = compareIfBlackJackNum(dealerHand, playerHand)
            return PlayerResult(player.name, playerWinningState)
        } else {
            val playerWinningState = compareIfNotBlackJackNum(dealerHand, playerHand)
            return PlayerResult(player.name, playerWinningState)
        }
    }

    private fun compareIfBlackJackNum(
        dealerHand: Hand,
        playerHand: Hand,
    ): WinningState {
        return when {
            playerHand.cards.size == dealerHand.cards.size -> WinningState.DRAW
            playerHand.cards.size == BLACKJACK_CARD_SIZE -> WinningState.WIN
            dealerHand.cards.size == BLACKJACK_CARD_SIZE -> WinningState.LOSS
            else -> WinningState.DRAW
        }
    }

    private fun compareIfNotBlackJackNum(
        dealerHand: Hand,
        playerHand: Hand,
    ): WinningState {
        return when {
            dealerHand.sumOptimized() == playerHand.sumOptimized() -> WinningState.DRAW
            dealerHand.sumOptimized() < playerHand.sumOptimized() -> WinningState.WIN
            dealerHand.sumOptimized() > playerHand.sumOptimized() -> WinningState.LOSS
            else -> throw IllegalArgumentException()
        }
    }

    companion object {
        fun createDealer(deck: Deck): Dealer {
            val dealer = Dealer(Hand(listOf()))
            dealer.initialSetHand(deck)
            return dealer
        }

        const val HIT_CONDITION = 17
        const val BLACKJACK_NUMBER = 21
        const val BLACKJACK_CARD_SIZE = 2
    }
}
