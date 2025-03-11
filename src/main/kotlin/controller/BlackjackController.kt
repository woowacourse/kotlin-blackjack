package controller

import model.CardDistributor
import model.CardsGenerator
import model.Dealer
import model.Players
import model.displayNames
import service.GameService
import view.InputView
import view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator,
) {
    fun run() {
        val generatedCards = cardsGenerator.generateCards()
        val cardDistributor = CardDistributor(generatedCards)
        val gameService = GameService(cardDistributor)

        val (dealer, players) = gameService.startGame(inputView.inputPlayers())

        showInitialGameState(players, dealer)

        handlePlayerTurns(players, cardDistributor)

        val dealerDrawCount = gameService.playDealerTurn(dealer)
        if (dealerDrawCount > 0) outputView.printDealerHit(dealerDrawCount)

        showTotalResult(dealer, players, gameService)
    }

    private fun showInitialGameState(players: Players, dealer: Dealer) {
        val playerCardNames = players.map { it.getHand().handCards.displayNames() }
        val dealerCardNames = dealer.getHand().handCards.displayNames()
        outputView.printDealerAndPlayers(players.getPlayersNames())
        outputView.printInitialCards(dealerCardNames, players.getPlayersNames(), playerCardNames)
    }

    fun handlePlayerTurns(players: Players, cardDistributor: CardDistributor) {
        players.forEach { player ->
            while (player.decideToHit() && inputView.readHitOrStand(player.name)) {
                player.performTurn(cardDistributor)
                outputView.printPlayerCards(player.name, player.getHand().handCards.displayNames())
            }
        }
    }

    private fun showTotalResult(dealer: Dealer, players: Players, gameService: GameService) {
        outputView.printDealerResult(dealer.getHand().handCards.displayNames(), dealer.getScore())
        showPlayerResult(players)
        showGameResult(dealer, players, gameService)
    }

    private fun showPlayerResult(players: Players) {
        val updatedPlayerCardsNames = players.map { it.getHand().handCards.displayNames() }
        val playersTotalScore = players.getPlayersScores()
        outputView.printPlayerResult(players.getPlayersNames(), updatedPlayerCardsNames, playersTotalScore)
    }

    private fun showGameResult(dealer: Dealer, players: Players, gameService: GameService) {
        val gameResultOutput = gameService.getGameResult(dealer, players).compareWinOrLose()
        outputView.printResult(
            gameResultOutput.dealerWins,
            gameResultOutput.dealerLosses,
            gameResultOutput.playerResults
        )
    }
}
