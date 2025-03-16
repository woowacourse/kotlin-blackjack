package controller

import model.BettingManager
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
        val bettingManager = BettingManager()

        val playerNames = inputView.inputPlayers()

        val inputBettingAmount = inputView.inputBettingAmount(playerNames)
        gameManager.startGame(playerNames, inputBettingAmount, bettingManager)

        showInitialGameState(gameManager)

        gameManager.dealerPlay()
        if (gameManager.getDrawCount() > 0) {
            outputView.printDealerHit(gameManager.getDrawCount())
        }

        showPlayerResult(gameManager)
        showGameResult(gameManager, bettingManager)
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

        gameManager.getPlayers().forEach { player ->
            player.decisionMaker = {inputView.readHitOrStand(player.name)}
        }

        gameManager.playersPlay(
            showCards = { player ->
                outputView.printPlayerCards(player.name, player.cards.displayNames())
            }
        )
    }

    private fun showPlayerResult(gameManager: GameManager) {
        val dealer = gameManager.getDealer()
        val players = gameManager.getPlayers()

        outputView.printDealerResult(dealer.cards.displayNames(), dealer.getTotalScore())
        val updatedPlayerCardsNames = players.getPlayersCard().map { it.displayNames() }
        val playersTotalScore = players.getPlayersScores()
        outputView.printPlayerResult(players.getPlayersNames(), updatedPlayerCardsNames, playersTotalScore)
    }

    private fun showGameResult(
        gameManager: GameManager,
        bettingManager: BettingManager,
    ) {
        val playersProfit = gameManager.determinePlayersProfit(bettingManager)
        outputView.printResult(
            gameManager.determineDealerProfit(playersProfit),
            playersProfit,
        )
    }
}
