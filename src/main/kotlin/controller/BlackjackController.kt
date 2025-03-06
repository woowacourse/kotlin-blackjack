package controller

import model.Cards
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
                Player(name, allCards.drawCards(2))
            }

        val playerCardsNames = players.map { it.playerCards.getCardNames() }
        val dealerInitialCards = allCards.drawCards(2)
        val dealerCardNames = dealerInitialCards.getCardNames()

        val dealer = Dealer(dealerInitialCards)
        outputView.printDealerAndPlayers(playersNames)
        outputView.printInitialCards(dealerCardNames, playersNames, playerCardsNames)

        players.forEach { player ->
            while (true) {
                val score = player.getScore()
                if (score > 21) break
                val decision = inputView.readHitOrStand(player.name)
                if (!decision) break
                showHitOrStandQuestion(score, player, allCards)
            }
        }

        val dealerTotalScore = dealer.getScore()
        if (dealerTotalScore <= 16) {
            outputView.printDealerHit()
            while (true) {
                if (dealer.turn(allCards)) break
            }
        }
        val updatedDealerTotalScore = dealer.getScore()

        val playersTotalScore = players.map { it.getScore() }
        outputView.printDealerResult(dealerInitialCards.getCardNames(), updatedDealerTotalScore)

        val updatedPlayerCardsNames = players.map { it.playerCards.getCardNames() }
        outputView.printPlayerResult(playersNames, updatedPlayerCardsNames, playersTotalScore)

        decideGameResult(dealerTotalScore, players)
    }

    private fun showHitOrStandQuestion(
        score: Int,
        player: Player,
        allCards: Cards,
    ) {
        if (score <= 21) {
            player.turn(allCards)
        }
        outputView.printPlayerCards(player.name, player.playerCards.getCardNames())
    }

    private fun decideGameResult(
        dealerTotalScore: Int,
        players: List<Player>,
    ) {
        val gameResultOutput = GameResultDecider(dealerTotalScore, players).compareWinOrLose()
        outputView.printResult(
            gameResultOutput.dealerWins,
            gameResultOutput.dealerLosses,
            gameResultOutput.playerResults,
        )
    }
}
