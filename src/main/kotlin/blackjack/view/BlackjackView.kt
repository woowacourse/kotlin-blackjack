package blackjack.view

import blackjack.view.model.DealerResult
import blackjack.view.model.PlayerResult

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
        dealerCardsContent: List<String>,
        playerCardsContent: List<List<String>>,
    ) {
        outputView.showCardDealing(
            playersName,
            dealerCardsContent,
            playerCardsContent,
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

    fun onEachPlayerTurn(
        name: String,
        cards: List<String>,
        score: Int,
        canHitMore: Boolean,
        draw: () -> Unit,
    ) {
    }

    fun showDealerHit() {
        outputView.showDealerHit()
    }

    fun endDealerTurn(
        dealerCards: List<String>,
        dealerScore: Int,
        playerNames: List<String>,
        playerCards: List<List<String>>,
        playerScores: List<Int>,
    ) {
        outputView.endDealerTurn(dealerCards, dealerScore, playerNames, playerCards, playerScores)
    }
}
