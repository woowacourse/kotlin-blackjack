package controller

import model.CardsGenerator
import model.GameManager
import view.InputView
import view.OutputView
import view.displayNames

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator,
) {
    fun run() {
        val gameManager = GameManager(cardsGenerator.generateCards())
        gameManager.startGame(inputView.inputPlayers())

        showInitialGameState(gameManager)

        gameManager.playersPlay(
            shouldHit = { player -> inputView.readHitOrStand(player.name) },
            showCards = { player -> outputView.printPlayerCards(player.name, player.cards.displayNames()) },
        )

        val dealerDrawCount = gameManager.dealerPlay()
        if (dealerDrawCount > 0) {
            outputView.printDealerHit(dealerDrawCount)
        }

        showPlayerResult(gameManager)
        showGameResult(gameManager)
    }

    private fun showInitialGameState(gameManager: GameManager) {
        val dealer = gameManager.getDealer()
        val players = gameManager.getPlayers()

        outputView.printDealerAndPlayers(players.getPlayersNames())
        outputView.printInitialCards(
            dealer.cards.displayNames(),
            players.getPlayersNames(),
            players.getPlayersCard().map {
                it.displayNames()
            },
        )
    }

    private fun showPlayerResult(gameManager: GameManager) {
        val dealer = gameManager.getDealer()
        val players = gameManager.getPlayers()

        outputView.printDealerResult(dealer.cards.displayNames(), dealer.getScore())
        val updatedPlayerCardsNames = players.getPlayersCard().map { it.displayNames() }
        val playersTotalScore = players.getPlayersScores()
        outputView.printPlayerResult(players.getPlayersNames(), updatedPlayerCardsNames, playersTotalScore)
    }

    private fun showGameResult(gameManager: GameManager) {
        val gameResultOutput = gameManager.getGameResult()
        outputView.printResult(
            gameResultOutput.dealerWins,
            gameResultOutput.dealerLosses,
            gameResultOutput.playerResults,
        )
    }
}
