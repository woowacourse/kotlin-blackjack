package blackjack.model

import blackjack.model.GameDeck.Companion.CARD_DRAW_DEFAULT_INDEX

class Participants(
    val dealer: Dealer,
    val playerGroup: PlayerGroup,
) {
    fun startGame(
        inputPlayerBettingAmount: (nickname: Nickname) -> Int,
        printGameSetting: (participants: Participants) -> Unit,
        askHitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (cardHolder: CardHolder) -> Unit,
        printDealerDrawCard: (dealer: Dealer) -> Unit,
        printEveryCards: (participants: Participants) -> Unit,
    ): Result<ProfitResults> {
        return runCatching {
            val gameDeck = GameDeck()
            initBetting(inputPlayerBettingAmount)
            initSetting(gameDeck, printGameSetting)
            runPlayersTurn(gameDeck, askHitOrStay, showPlayerCards)
            runDealerTurn(printDealerDrawCard, gameDeck)
            finish(printEveryCards)
        }
    }

    private fun initBetting(inputPlayerBettingAmount: (nickname: Nickname) -> Int) {
        playerGroup.startBetting { player ->
            inputPlayerBettingAmount(player.userInfo.nickname)
        }
    }

    private fun initSetting(
        gameDeck: GameDeck,
        printGameSetting: (participants: Participants) -> Unit,
    ) {
        repeat(INITIAL_CARD_COUNTS) { initParticipantsDeck(gameDeck) }
        printGameSetting(this)
    }

    private fun runPlayersTurn(
        gameDeck: GameDeck,
        askHitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (cardHolder: CardHolder) -> Unit,
    ) {
        playerGroup.drawPlayerCard(
            gameDeck = gameDeck,
            shouldDrawCard = { player -> askHitOrStay(player.userInfo.nickname) },
            showPlayerCards = showPlayerCards,
        )
    }

    private fun runDealerTurn(
        printDealerDrawCard: (dealer: Dealer) -> Unit,
        gameDeck: GameDeck,
    ) {
        dealer.drawCard(
            card = { gameDeck.drawCard(CARD_DRAW_DEFAULT_INDEX) },
            shouldDrawCard = { dealer.shouldDrawCard() },
            showPlayerCards = { printDealerDrawCard(it as Dealer) },
        )
    }

    private fun finish(printEveryCards: (participants: Participants) -> Unit): ProfitResults {
        printEveryCards(this)
        return calculateResult()
    }

    fun initParticipantsDeck(gameDeck: GameDeck) {
        dealer.addCard(card = gameDeck.drawCard(CARD_DRAW_DEFAULT_INDEX))
        playerGroup.players.forEach { player ->
            player.addCard(card = gameDeck.drawCard(CARD_DRAW_DEFAULT_INDEX))
        }
    }

    fun resetHand() {
        playerGroup.players.forEach { player -> player.state.hand().reset() }
        dealer.state.hand().reset()
    }

    private fun calculateResult(): ProfitResults {
        val result = mutableListOf<ProfitResult>()
        var totalProfit = 0.0

        playerGroup.players.forEach { player ->
            val profit = player.calculateProfit(dealer.state)
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
