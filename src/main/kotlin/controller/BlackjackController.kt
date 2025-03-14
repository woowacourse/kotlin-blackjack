package controller

import model.BettingManager
import model.CardsGenerator
import model.GameManager
import model.Players
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
        gameManager.startGame(playerNames)

        setupBets(playerNames, gameManager.getPlayers(), bettingManager)

        showInitialGameState(gameManager)

        gameManager.playersPlay(
            getPlayerDecision = { player ->
                val decision = inputView.readHitOrStand(player.name)
                decision
            },
            showCards = { player ->
                outputView.printPlayerCards(player.name, player.cards.displayNames())
            },
        )

        gameManager.dealerPlay()
        if (gameManager.getDrawCount() > 0) {
            outputView.printDealerHit(gameManager.getDrawCount())
        }
        showPlayerResult(gameManager)

        showGameResult(gameManager, bettingManager)
    }

    private fun setupBets(
        playerNames: List<String>,
        players: Players,
        bettingManager: BettingManager,
    ) {
        val betAmounts: Map<String, Int> = inputView.inputBettingAmount(playerNames)

        players.forEach { player ->
            val bet = betAmounts[player.name] ?: 0
            bettingManager.placeBet(player, bet)
        }
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

    private fun showGameResult(
        gameManager: GameManager,
        bettingManager: BettingManager,
    ) {
        val gameResultOutput = gameManager.determineBettingAmounts(bettingManager)
        outputView.printResult(
            gameManager.determineDealerProfit(bettingManager),
            gameResultOutput,
        )
    }
}
