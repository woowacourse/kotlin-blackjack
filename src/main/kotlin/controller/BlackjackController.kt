package controller

import model.CardsGenerator
import model.Dealer
import model.GameResultDecider
import model.Player
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

        val players =
            playersNames.map { name ->
                Player(name, allCards.getInitialCards())
            }

        val playerCardsNames = players.map { it.playerCards.getCardNames() }
        val dealerInitialCards = allCards.getInitialCards()
        val dealerCardNames = dealerInitialCards.getCardNames()

        val dealer = Dealer(dealerInitialCards)
        outputView.printDealerAndPlayers(playersNames)
        outputView.printInitialCards(dealerCardNames, playersNames, playerCardsNames)

        players.forEach { player ->
            while (player.isHit() && inputView.readHitOrStand(player.name)) {
                player.turn(allCards)
                outputView.printPlayerCards(player.name, player.playerCards.getCardNames())
            }
        }

        if (dealer.isHit()) {
            val dealerAddCount = dealer.getDrawCount(allCards)
            outputView.printDealerHit(dealerAddCount)
        }
        val updatedDealerTotalScore = dealer.getScore()

        val playersTotalScore = players.map { it.getScore() }
        outputView.printDealerResult(dealerInitialCards.getCardNames(), updatedDealerTotalScore)

        val updatedPlayerCardsNames = players.map { it.playerCards.getCardNames() }
        outputView.printPlayerResult(playersNames, updatedPlayerCardsNames, playersTotalScore)

        showGameResult(dealer, players)
    }

    private fun showGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val gameResultOutput = GameResultDecider(dealer, players).compareWinOrLose()
        outputView.printResult(
            gameResultOutput.dealerWins,
            gameResultOutput.dealerLosses,
            gameResultOutput.playerResults,
        )
    }
}
