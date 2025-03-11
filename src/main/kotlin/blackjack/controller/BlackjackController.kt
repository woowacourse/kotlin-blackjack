package blackjack.controller

import blackjack.model.BlackjackEngine
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.WinningResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    val blackjackEngine = BlackjackEngine()
    fun run() {
        val dealer = blackjackEngine.prepareDealer()
        val players = blackjackEngine.preparePlayers(inputView.getPlayers())
        outputView.displayFirstDrawEnd(dealer.name, players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.hand.cards.take(DEALER_FIRST_SHOWN_COUNT))
        blackjackEngine.progressPlayersDraw(players,outputView,inputView)
        blackjackEngine.progressDealerDraw(dealer,outputView)
        displayParticipantsInfo(players)
        displayResults(dealer, players)
    }

    private fun displayParticipantsInfo(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards, player.hand.score(), player.hand.isBust())
        }
    }

    private fun displayResults(
        dealer: Dealer,
        players: Players,
    ) {
        outputView.displayResultTitle()

        val dealerResult = dealer.getWinDrawLossResult(players)
        outputView.displayDealerResult(dealer.name, dealerResult)

        val playerResults: Map<String, WinningResult> = players.results(dealer)
        playerResults.forEach { (name, winningResult) ->
            outputView.displayPlayerResult(name, winningResult)
        }
    }

    companion object {
        private const val DEALER_FIRST_SHOWN_COUNT = 1
    }
}
