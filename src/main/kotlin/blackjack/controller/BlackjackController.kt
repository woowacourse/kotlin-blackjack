package blackjack.controller

import blackjack.model.Amount
import blackjack.model.BlackjackEngine
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    val blackjackEngine = BlackjackEngine()
    fun run() {
        val dealer = blackjackEngine.prepareDealer()
        val players = blackjackEngine.preparePlayers(inputView.getNames())
        blackjackEngine.getPlayersBet(players,inputView)
        outputView.displayFirstDrawEnd(dealer.name, players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.hand.cards.take(DEALER_FIRST_SHOWN_COUNT))
        blackjackEngine.progressPlayersDraw(players,outputView,inputView)
        blackjackEngine.progressDealerDraw(dealer,outputView)
        displayParticipantsInfo(players)
        displayResult(dealer,players,outputView)
    }

    private fun displayResult(dealer: Dealer, players: Players, outputView: OutputView){
        outputView.displayResultTitle()
        val playersResult = blackjackEngine.getPlayerMoneyResults(dealer,players)
        val dealerResult = blackjackEngine.getDealerMoneyResults(dealer,playersResult)
        outputView.displayResultMoney(dealerResult.first.name,dealerResult.second.getValue())
        playersResult.forEach { playerResult->
            outputView.displayResultMoney(playerResult.key.name,playerResult.value.getValue())
        }
    }

    private fun displayParticipantsInfo(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards, player.hand.score(), player.hand.isBust())
        }
    }

    companion object {
        private const val DEALER_FIRST_SHOWN_COUNT = 1
    }
}
