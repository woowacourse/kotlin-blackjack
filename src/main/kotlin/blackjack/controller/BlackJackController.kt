package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.GameInfo
import blackjack.model.Judge
import blackjack.model.Participant
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackController {
    fun startGame() {
        val playerNames = getPlayerNames()
        val dealer = Dealer(playerNames.toSet())
        val players =
            List(playerNames.size) { Player(GameInfo(playerNames[it]), onInputDecision = { askPlayerHit(playerNames[it]) }) }
        dealer.drawCard { CardDeck.pick() }
        initializePlayerCards(players)
        displayInitializedCards(dealer.gameInfo, players.map { it.gameInfo })

        players.forEach { player ->
            player.drawForSingleParticipant(OutputView::printSinglePlayerCards)
        }
        dealer.drawForSingleParticipant(OutputView::printDealerHit)

        val dealerStat = dealer.gameInfo
        val playerStat = players.map { it.gameInfo }

        val judge = Judge(dealerStat, playerStat)

        OutputView.printFinalCards(dealerStat, playerStat)
        OutputView.printResult(judge.getDealerResult(), judge.getPlayerResults(), playerStat, dealerStat)
    }

    private fun getPlayerNames(): List<String> {
        return runCatching {
            InputView.readPlayerNames() ?: getPlayerNames()
        }.onFailure {
            println(it.message)
            return getPlayerNames()
        }.getOrThrow()
    }

    private fun Participant.drawForSingleParticipant(printCards: (GameInfo) -> Unit) {
        drawUntilSatisfaction(CardDeck::pick, printCards)
    }

    private fun askPlayerHit(playerName: String): String = InputView.readContinueInput(playerName) ?: askPlayerHit(playerName)

    private fun displayInitializedCards(
        dealerInfo: GameInfo,
        playersInfo: List<GameInfo>,
    ) {
        OutputView.printInitialStats(dealerInfo, playersInfo)
    }

    private fun initializePlayerCards(players: List<Player>) {
        players.forEach { player ->
            player.initializeCards { CardDeck.pick() }
        }
    }
}
