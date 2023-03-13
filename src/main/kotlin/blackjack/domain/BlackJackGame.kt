package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame(
    private val cardPack: CardPack,
    private val dealer: Dealer = Dealer(CardHand(listOf(cardPack.draw(), cardPack.draw()))),
    private var players: List<Player> = emptyList()
) {
    fun entryPlayers(requestPlayersName: List<String>) {
        players = requestPlayersName.map { name ->
            Player(
                name = PlayerName(name),
                cardHand = CardHand(listOf(cardPack.draw(), cardPack.draw())),
                betAmount = BetAmount(InputView.requestBetAmount(name))
            )
        }
    }

    fun showParticipants() = Pair(dealer, players)

    fun drawAdditionalCards() = players.forEach { player ->
        askToDrawAdditionalCardForPlayer(player)
    }

    private fun askToDrawAdditionalCardForPlayer(player: Player) {
        do {
            val drawFlag = InputView.requestAdditionalDraw(player)
        } while (drawFlag && drawAdditionalCardForPlayer(player) == DrawState.POSSIBLE)

        if (player.cardHand.size == CardHand.INITIAL_CARDS_SIZE) {
            OutputView.printCardResults(player)
        }
    }

    private fun drawAdditionalCardForPlayer(player: Player): DrawState {
        val drawState = player.drawCard(cardPack)
        if (cardPack.isEmpty()) {
            cardPack.addCardDeck()
        }
        OutputView.printCardResults(player)

        return drawState
    }

    fun drawAdditionalCardForDealer(): DrawResult {
        val drawResult = dealer.drawCard(cardPack)
        if (cardPack.isEmpty()) {
            cardPack.addCardDeck()
        }
        return drawResult
    }

    fun judgeGameResults(): Pair<List<PlayerGameResult>, List<GameResult>> {
        val playersGameResult = players.map { player ->
            PlayerGameResult(player, BlackJackReferee.judgeGameResult(player, dealer))
        }
        val dealerGameResult = playersGameResult.map { playerGameResult ->
            !playerGameResult.gameResult
        }

        return Pair(playersGameResult, dealerGameResult)
    }

    fun judgeBetResults(playersGameResult: List<PlayerGameResult>): Pair<BetAmount, Map<PlayerName, BetAmount>> {
        var dealerDividend = BetAmount(0)
        val playersDividend = mutableMapOf<PlayerName, BetAmount>()

        playersGameResult.forEach { playerGameResult ->
            val playerDividend = playerGameResult.player.betAmount * playerGameResult.gameResult.dividendRate
            playersDividend[playerGameResult.player.name] = playerDividend
            dealerDividend += (playerDividend * -1.0)
        }

        return Pair(dealerDividend, playersDividend)
    }
}
