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
        val allCards = cardsGenerator.generateCards()
        val initialDealerCards = allCards.initialCards()
        val players = Players(createPlayers(inputView.inputPlayers(), allCards))
        val dealer = Dealer(initialDealerCards)

        showInitialGameState(
            players.names,
            initialDealerCards,
            players.getPlayerCardNames
        )
        handlePlayerTurns(players, allCards)
        handleDealerTurn(dealer, allCards)
        showTotalResult(initialDealerCards, dealer, players)
    }

    private fun showTotalResult(
        initialDealerCards: Cards,
        dealer: Dealer,
        players: Players,
    ) {
        outputView.printDealerResult(initialDealerCards.names, dealer.currentScore())
        showPlayerResult(players, players.names)
        showGameResult(dealer, players)
    }

    private fun showPlayerResult(
        players: Players,
        playersNames: List<String>,
    ) {
        val updatedPlayerCardsNames = players.getPlayerCardNames
        val playersTotalScore = players.scores
        outputView.printPlayerResult(playersNames, updatedPlayerCardsNames, playersTotalScore)
    }

    private fun showInitialGameState(
        playersNames: List<String>,
        initialDealerCards: Cards,
        playerCardsNames: List<List<Pair<String, String>>>,
    ) {
        val dealerCardNames = initialDealerCards.names
        outputView.printDealerAndPlayers(playersNames)
        outputView.printInitialCards(dealerCardNames, playersNames, playerCardsNames)
    }

    private fun handleDealerTurn(
        dealer: Dealer,
        allCards: Cards,
    ) {
        if (dealer.canHit()) {
            val dealerAddCount = dealer.drawCount(allCards.drawCard())
            outputView.printDealerHit(dealerAddCount)
        }
    }

    private fun handlePlayerTurns(
        players: Players,
        allCards: Cards,
    ) {
        players.forEach { player ->
            while (player.canHit() && inputView.readHitOrStand(player.name)) {
                val drawnCard = allCards.drawCard()
                player.turn(drawnCard)
                outputView.printPlayerCards(player.name, player.getPlayerCardNames)
            }
        }
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

    private fun createPlayers(
        playersNames: List<String>,
        allCards: Cards,
    ): List<Player> =
        playersNames.map { name ->
            Player(name, allCards.initialCards())
        }
}
