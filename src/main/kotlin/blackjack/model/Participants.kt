package blackjack.model

class Participants(
    val dealer: Dealer,
    val playerGroup: PlayerGroup,
) {
    fun startGame(
        inputPlayerBettingAmount: (nickname: Nickname) -> String,
        printGameSetting: (participants: Participants) -> Unit,
        askHitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
        printDealerDrawCard: (dealer: Dealer) -> Unit,
        printEveryCards: (participants: Participants) -> Unit,
    ): Result<ProfitResults> {
        return runCatching {
            initBetting(inputPlayerBettingAmount)
            initSetting(printGameSetting)
            runPlayersTurn(askHitOrStay, showPlayerCards)
            runDealerTurn(printDealerDrawCard)
            finish(printEveryCards)
        }
    }

    private fun initBetting(inputPlayerBettingAmount: (nickname: Nickname) -> String) {
        playerGroup.startBetting { player ->
            inputPlayerBettingAmount(player.userInfo.nickname)
        }
    }

    private fun initSetting(printGameSetting: (participants: Participants) -> Unit) {
        repeat(INITIAL_CARD_COUNTS) { initParticipantsDeck() }
        printGameSetting(this)
    }

    private fun runPlayersTurn(
        askHitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        playerGroup.drawPlayerCard(
            shouldDrawCard = { player -> askHitOrStay(player.userInfo.nickname) },
            newPlayer = { player -> showPlayerCards(player) },
        )
    }

    private fun runDealerTurn(printDealerDrawCard: (dealer: Dealer) -> Unit) {
        dealer.drawCard(
            shouldDrawCard = { dealer.shouldDrawCard() },
            newCardHolder = { printDealerDrawCard(it as Dealer) },
        )
    }

    private fun finish(printEveryCards: (participants: Participants) -> Unit): ProfitResults {
        printEveryCards(this)
        return calculateResult()
    }

    fun initParticipantsDeck() {
        dealer.addCard(card = GameDeck.drawCard())
        playerGroup.players.forEach { player ->
            player.addCard(card = GameDeck.drawCard())
        }
    }

    fun resetHand() {
        playerGroup.players.forEach { player -> player.getState().hand().reset() }
        dealer.getState().hand().reset()
    }

    private fun calculateResult(): ProfitResults {
        val result = mutableListOf<ProfitResult>()
        var totalProfit = 0.0

        playerGroup.players.forEach { player ->
            val profit = player.calculateProfit(dealer)
            result.add(ProfitResult(player, Profit((profit))))
            totalProfit += profit
        }
        result.add(0, ProfitResult(dealer, Profit((-totalProfit))))

        return ProfitResults(result.toList())
    }

    companion object {
        const val INITIAL_CARD_COUNTS = 2
    }
}
