package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.participants.Dealer
import blackjack.model.participants.Judge
import blackjack.model.participants.Players
import blackjack.view.GameRoundView
import blackjack.view.InitializeView
import blackjack.view.ResultView

object BlackJackController {
    private lateinit var players: Players
    private val dealer: Dealer by lazy {
        Dealer.of(CardDeck::pick)
    }

    fun startGame() {
        initializePlayers()
        displayInitializedCards()
        playRound()
        displayResult()
    }

    private fun initializePlayers() {
        runCatching {
            val playerNames = getPlayerNames()
            val playersMoneyAmount = getPlayersMoneyAmount(playerNames)
            players = Players.of(playerNames, playersMoneyAmount, ::askPlayerHit, CardDeck::pick)
        }.onFailure {
            println(it.message)
            initializePlayers()
        }
    }

    private fun displayInitializedCards() {
        GameRoundView.printInitialStats(dealer.gameInfo, players.value.map { it.gameInfo })
    }

    private fun playRound() {
        runCatching {
            players.value.forEach { player ->
                player.drawCardsUntilStand(CardDeck::pick, GameRoundView::printSinglePlayerCards)
            }
            GameRoundView.printNewLine()
            dealer.drawCardsUntilStand(CardDeck::pick, GameRoundView::printDealerHit)
        }.onFailure {
            println(it.message)
        }
    }

    private fun displayResult() {
        val dealerInfo = dealer.gameInfo
        val playersInfo = players.value.map { player -> player.gameInfo }
        val judge = Judge(dealerInfo, playersInfo)
        with(ResultView) {
            printFinalCards(dealerInfo, playersInfo)
            printResult(judge)
        }
    }

    private fun getPlayerNames(): List<String> {
        return runCatching {
            InitializeView.readPlayerNames()
        }.onFailure {
            println(it.message)
            return getPlayerNames()
        }.getOrThrow()
    }

    private fun getPlayersMoneyAmount(playerNames: List<String>): List<Int> =
        InitializeView.readPlayersBettingAmount(names = playerNames) ?: getPlayersMoneyAmount(playerNames)

    private fun askPlayerHit(playerName: String): String =
        runCatching {
            InitializeView.readContinueInput(playerName)
        }.onFailure {
            return askPlayerHit(playerName)
        }.getOrThrow()
}
