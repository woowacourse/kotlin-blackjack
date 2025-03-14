package blackjack.controller

import blackjack.model.BlackjackEngine
import blackjack.model.Dealer
import blackjack.model.Money
import blackjack.model.Participant
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val blackjackEngine = BlackjackEngine()
    private val dealer = blackjackEngine.prepareDealer()
    private val players = blackjackEngine.preparePlayers(inputView.getNames())

    tailrec fun run() {
        blackjackEngine.getPlayersBet(players, inputView)
        outputView.displayFirstDrawEnd(dealer.name, players.value.map { player -> player.name })
        outputView.displayParticipantCards(
            dealer.name,
            dealer.items.hand.cards
                .take(DEALER_FIRST_SHOWN_COUNT),
        )
        progressDraw()
        displayParticipantsInfo()
        displayResult()
        if (!inputView.moreGame()) return
        blackjackEngine.setParticipantCard(dealer, players)
        return run()
    }

    private fun displayResult() {
        val currentResult = blackjackEngine.progressCalculateResult(dealer, players)
        displayCurrentResult(currentResult, dealer, players)
        blackjackEngine.progressCalculateFullResult(currentResult)
        displayFullResult(dealer, players)
    }

    private fun progressDraw() {
        blackjackEngine.progressPlayersDraw(players, outputView, inputView)
        blackjackEngine.progressDealerDraw(dealer, outputView)
    }

    private fun displayFullResult(
        dealer: Dealer,
        players: Players,
    ) {
        outputView.displayResultTitle()
        outputView.displayResultMoney(dealer.name, dealer.items.money.getValue())
        players.value.forEach { player ->
            outputView.displayResultMoney(player.name, player.items.money.getValue())
        }
    }

    private fun displayCurrentResult(
        currentResult: Map<Participant, Money>,
        dealer: Dealer,
        players: Players,
    ) {
        outputView.displayCurrentResultTitle()
        outputView.displayResultMoney(
            dealer.name,
            currentResult[dealer]?.getValue() ?: throw IllegalArgumentException("[ERROR] 딜러를 찾을 수 없습니다."),
        )
        players.value.forEach { player ->
            outputView.displayResultMoney(
                player.name,
                currentResult[player]?.getValue() ?: throw IllegalArgumentException("[ERROR] 플레이어를 찾을 수 없습니다."),
            )
        }
    }

    private fun displayParticipantsInfo() {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(
                player.name,
                player.items.hand.cards,
                player.items.hand.score(),
                player.items.hand.isBust(),
            )
        }
    }

    companion object {
        private const val DEALER_FIRST_SHOWN_COUNT = 1
    }
}
