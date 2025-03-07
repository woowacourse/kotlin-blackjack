package controller

import model.Cards
import model.CardsGenerator
import model.Dealer
import model.GameResultDecider
import model.Player
import model.Players
import view.InputView
import view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator,
) {
    fun run() {
        val playersNames = inputView.inputPlayers()
        val allCards = cardsGenerator.generateCards()
        val dealerCards = allCards.getInitialCards()

        val players = Players(getPlayers(playersNames, allCards))
        val dealer = Dealer(dealerCards)

        val dealerCardNames = dealerCards.getCardNames()
        showInitialGameState(playersNames, dealerCardNames, players.getPlayerCardNames())
        handlePlayerTurns(players, allCards)
        handleDealerTurn(dealer, allCards)

        outputView.printDealerResult(dealerCards.getCardNames(), dealer.getScore())
        showPlayerResult(players, playersNames)
        showGameResult(dealer, players)
    }

    private fun showPlayerResult(
        players: Players,
        playersNames: List<String>,
    ) {
        val updatedPlayerCardsNames = players.getPlayerCardNames()
        val playersTotalScore = players.getPlayersScores()
        outputView.printPlayerResult(playersNames, updatedPlayerCardsNames, playersTotalScore)
    }

    private fun showInitialGameState(
        playersNames: List<String>,
        dealerCardNames: List<String>,
        playerCardsNames: List<List<String>>,
    ) {
        outputView.printDealerAndPlayers(playersNames)
        outputView.printInitialCards(dealerCardNames, playersNames, playerCardsNames)
    }

    private fun handleDealerTurn(
        dealer: Dealer,
        allCards: Cards,
    ) {
        if (dealer.isHit()) {
            val dealerAddCount = dealer.getDrawCount(allCards)
            outputView.printDealerHit(dealerAddCount)
        }
    }

    private fun handlePlayerTurns(
        players: Players,
        allCards: Cards,
    ) {
        players.forEach { player ->
            while (player.isHit() && inputView.readHitOrStand(player.name)) {
                player.turn(allCards)
                outputView.printPlayerCards(player.name, player.getPlayerCardNames())
            }
        }
    }

    private fun getPlayers(
        playersNames: List<String>,
        allCards: Cards,
    ): List<Player> =
        playersNames.map { name ->
            Player(name, allCards.getInitialCards())
        }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        val gameResultOutput = GameResultDecider(dealer, players).compareWinOrLose()
        outputView.printResult(
            gameResultOutput.dealerWins,
            gameResultOutput.dealerLosses,
            gameResultOutput.playerResults,
        )
    }
}
