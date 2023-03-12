package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView
import java.lang.StringBuilder

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

    fun showDividingCards(printCardDividing: (Dealer, List<Player>) -> Unit) = printCardDividing(dealer, players)

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
        val drawState = player.drawCard(cardPack.draw())

        OutputView.printCardResults(player)

        return drawState
    }

    fun drawAdditionalCardForDealer() {
        val drawResult = dealer.drawCard(cardPack.draw())

        OutputView.printIsDealerReceivedCard(drawResult)
    }

    fun showFinalCards() = OutputView.printFinalCards(dealer, players)

    fun judgeGameResults() {
        val playersGameResult = players.map { player ->
            PlayerGameResult(player, BlackJackReferee.judgeGameResult(player, dealer))
        }
        val dealerGameResult = playersGameResult.map { playerGameResult ->
            !playerGameResult.gameResult
        }

        OutputView.printGameResults(playersGameResult, dealerGameResult)
        judgeBetResults(playersGameResult)
    }

    private fun judgeBetResults(playersGameResult: List<PlayerGameResult>) {
        var dealerDividend = BetAmount(0)
        val playersDividend = StringBuilder()

        playersGameResult.forEach { playerGameResult ->
            val playerDividend = playerGameResult.player.betAmount * playerGameResult.gameResult.dividendRate
            playersDividend.append(playerGameResult.player.name.value + ": " + (playerGameResult.player.betAmount * playerGameResult.gameResult.dividendRate).money + "\n")
            dealerDividend += (playerDividend * -1.0)
        }

        OutputView.printBetResults(dealerDividend, playersDividend)
    }
}
