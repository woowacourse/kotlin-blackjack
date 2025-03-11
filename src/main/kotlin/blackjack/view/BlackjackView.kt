package blackjack.view

import blackjack.view.model.DealerResult
import blackjack.view.model.DealerSummary
import blackjack.view.model.PlayerResult
import blackjack.view.model.PlayerSummary

class BlackjackView(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun readPlayers(): List<String> {
        outputView.requestPlayers()
        return inputView.readPlayers()
    }

    fun dealCards(
        playersName: List<String>,
        dealerCards: List<String>,
        playersCards: List<List<String>>,
    ) {
        outputView.showCardDealing(
            playersName,
            dealerCards,
            playersCards,
        )
    }

    fun showResult(
        dealerResults: DealerResult,
        playersResults: List<PlayerResult>,
    ) {
        outputView.showResult(dealerResults, playersResults)
    }

    fun showPlayerCard(
        name: String,
        cards: List<String>,
        score: Int,
    ) {
        outputView.showPlayerCards(
            name,
            cards,
            score,
        )
    }

    fun askWantToHit(name: String): Boolean {
        outputView.askWantToHit(name)
        val wantToHit = inputView.readWantToHit()
        return wantToHit
    }

    fun showDealerHit() {
        outputView.showDealerHit()
    }

    fun showParticipantsSummary(
        dealerSummary: DealerSummary,
        playerSummaries: List<PlayerSummary>,
    ) {
        outputView.showParticipantsSummary(dealerSummary, playerSummaries)
    }
}
