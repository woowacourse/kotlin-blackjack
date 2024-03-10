package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.GameInfo
import blackjack.model.Judge
import blackjack.model.Participant
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackController {
    fun startGame() {
        val playerNames = getPlayerNames()
        val dealer = Dealer()
        val players = Players(playerNames, ::askPlayerHit)

        initializeParticipantsCards(dealer, players)
        playRound(players, dealer)
        displayResult(dealer, players)
    }

    private fun getPlayerNames(): List<String> {
        return runCatching {
            InputView.readPlayerNames() ?: getPlayerNames()
        }.onFailure {
            println(it.message)
            return getPlayerNames()
        }.getOrThrow()
    }

    private fun initializeParticipantsCards(
        dealer: Dealer,
        players: Players,
    ) {
        dealer.drawCard { CardDeck.pick() }

        players.players.forEach { player ->
            player.initializeCards { CardDeck.pick() }
        }

        displayInitializedCards(dealer.gameInfo, players.getPlayersGameInfo())
    }

    private fun playRound(
        players: Players,
        dealer: Dealer,
    ) {
        with(OutputView) {
            players.players.forEach { player ->
                player.drawForSingleParticipant(::printSinglePlayerCards)
            }
            printNewLine()
            dealer.drawForSingleParticipant(::printDealerHit)
        }
    }

    private fun displayResult(
        dealer: Dealer,
        players: Players,
    ) {
        val dealerInfo = dealer.gameInfo
        val playersInfo = players.getPlayersGameInfo()
        val judge = Judge(dealerInfo, playersInfo)
        with(OutputView) {
            printResult(judge)
            printFinalCards(dealerInfo, playersInfo)
        }
    }

    private fun Participant.drawForSingleParticipant(printCards: (GameInfo) -> Unit) {
        drawUntilSatisfaction(CardDeck::pick, printCards)
    }

    private fun askPlayerHit(playerName: String): String =
        InputView.readContinueInput(playerName) ?: askPlayerHit(playerName)

    private fun displayInitializedCards(
        dealerInfo: GameInfo,
        playersInfo: List<GameInfo>,
    ) {
        OutputView.printInitialStats(dealerInfo, playersInfo)
    }
}
