package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.card.CardProvider
import blackjack.model.participant.BattingAmount
import blackjack.model.participant.Dealer
import blackjack.model.participant.Players
import blackjack.util.retryWhileNotException
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardProvider: CardProvider,
) {
    fun run() {
        val dealer = Dealer()
        val players = retryWhileNotException { Players.from(inputView.readPlayersName()) }
        players.playerGroup.forEach {
            it.battingAmount = BattingAmount(inputView.readPlayerBattingAmount(it))
        }
        val blackjackGame = BlackjackGame(dealer, players, cardProvider)
        outputView.printInitCard(dealer, players)

        val gameResult =
            blackjackGame.start(
                { inputView.readMoreCardDecision(it) },
                { outputView.printPlayerCardsMessage(it) },
                { outputView.printDealerAdditionalCardMessage() },
            )

        gameResult.playersResult.results.forEach { name, result ->
            players.playerGroup.first { it.name == name }.calculateProfit(result)
        }

        dealer.calculateProfit(players)

        outputView.printPlayersCardResult(dealer, players)
        outputView.printFinalGameResult(players, gameResult)
        outputView.printFinalProfitResult(dealer, players)
    }
}
