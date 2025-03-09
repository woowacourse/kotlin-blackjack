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
        val players = blackjackEngine.preparePlayers(Players.from(inputView.getPlayers()))
        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(cards = dealer.hand.cards.take(DEALER_FIRST_SHOWN_COUNT))
        progressPlayersDraw(players)
        progressDealerDraw(dealer)
        displayParticipantsInfo(players)
        displayResults(dealer, players)
    }


    private fun progressPlayersDraw(
        players: Players,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.hand.cards)
        }
        players.value.forEach { player ->
            progressPlayerDrawUntilFinished(player)
        }
    }

    private fun progressPlayerDrawUntilFinished(
        player: Player,
    ) {
        while (inputView.getIsDrawMore(player.name)) {
            blackjackEngine.playerDraw(player)
            outputView.displayParticipantCards(player.name, player.hand.cards)
            if (player.hand.isBust()) return
        }
    }

    private fun progressDealerDraw(
        dealer: Dealer,
    ) {
        val dealerDrawCount = blackjackEngine.dealerDraw(dealer)
        outputView.displayDealerDrawInfo(dealerDrawCount)

        outputView.displayParticipantInfo(
            cards = dealer.hand.cards,
            score = dealer.hand.score(),
            isBust = dealer.hand.isBust(),
        )
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

        val dealerResult = dealer.result(players.scores())
        outputView.displayDealerResult(dealerResult)

        val playerResults: Map<String, WinningResult> = players.results(dealer.hand.score())
        playerResults.forEach { (name, winningResult) ->
            outputView.displayPlayerResult(name, winningResult)
        }
    }

    companion object {
        private const val DEALER_FIRST_SHOWN_COUNT = 1
    }
}
