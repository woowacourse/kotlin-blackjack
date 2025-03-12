package controller

import model.CardsGenerator
import model.GameManager
import view.InputView
import view.OutputView

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
            showCards = { player -> outputView.printPlayerCards(player.name, player.getCardNames()) },
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
        outputView.printInitialCards(dealer.getCardNames(), players.getPlayersNames(), players.getPlayersCardNames())
    }

    private fun showPlayerResult(gameManager: GameManager) {
        val dealer = gameManager.getDealer()
        val players = gameManager.getPlayers()

        outputView.printDealerResult(dealer.getCardNames(), dealer.getScore())
        val updatedPlayerCardsNames = players.getPlayersCardNames()
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
