package blackjack.model

class Dealer(
    hand: Hand,
    profit: Amount = Amount(INITIAL_PROFIT),
) : Participant(hand, profit) {
    override fun hitWhileConditionTrue(
        deck: Deck,
        condition: () -> Boolean,
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
        val playerWinningResults = comparePoints(players)
        return ScoreBoard.from(playerWinningResults)
    }

    fun makeAmountsToPlayer(
        playersResult: List<PlayerResult>,
        playersBetAmounts: List<Int>,
    ): PlayerAmounts {
        return PlayerAmounts(
            playersResult.mapIndexed { ind, playerResult ->
                makeAmountsAccordingToWinning(playerResult, playersBetAmounts, ind)
            },
        )
    }

    private fun makeAmountsAccordingToWinning(
        playerResult: PlayerResult,
        playersBetAmounts: List<Int>,
        ind: Int,
    ): Amount {
        return when (playerResult.winningState) {
            WinningState.WIN -> giveAmountsWhenPlayerWin(playerResult, playersBetAmounts, ind)
            WinningState.DRAW -> Amount(0)
            WinningState.LOSS -> Amount(-playersBetAmounts[ind])
        }
    }

    private fun giveAmountsWhenPlayerWin(
        playerResult: PlayerResult,
        playersBetAmounts: List<Int>,
        ind: Int,
    ): Amount {
        if (playerResult.player.hand.isBlackjack()) {
            return Amount((BLACKJACK_BONUS_RATE * playersBetAmounts[ind]).toInt())
        } else {
            return Amount(playersBetAmounts[ind])
        }
    }

    fun updateProfit(players: List<Player>) {
        players.forEach { player ->
            profit -= player.profit
        }
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
        if (playerHand.isBust()) return PlayerResult(player, WinningState.LOSS)
        if (dealerHand.isBust()) return PlayerResult(player, WinningState.WIN)
        if (playerHand.isBlackjack() && dealerHand.isBlackjack()) {
            val playerWinningState = compareIfBlackJackNum(dealerHand, playerHand)
            return PlayerResult(player, playerWinningState)
        } else {
            val playerWinningState = compareIfNotBlackJackNum(dealerHand, playerHand)
            return PlayerResult(player, playerWinningState)
        }
    }

    private fun compareIfNotBlackJackNum(
        dealerHand: Hand,
        playerHand: Hand,
    ): WinningState {
        val dealerSum = dealerHand.sumOptimized()
        val playerSum = playerHand.sumOptimized()
        return when {
            dealerSum == playerSum -> WinningState.DRAW
            dealerSum < playerSum -> WinningState.WIN
            dealerSum > playerSum -> WinningState.LOSS
            else -> throw IllegalArgumentException()
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

    companion object {
        fun createDealer(deck: Deck): Dealer {
            val dealer = Dealer(Hand(listOf()))
            dealer.setInitialHand(deck)
            return dealer
        }

        const val HIT_CONDITION = 17
        const val BLACKJACK_NUMBER = 21
        const val BLACKJACK_CARD_SIZE = 2
        const val BLACKJACK_BONUS_RATE = 1.5
    }
}
