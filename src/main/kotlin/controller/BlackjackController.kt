package controller

import model.Cards
import model.CardsGenerator
import model.Dealer
import model.GameResultDecider
import model.Player
import model.ScoreCalculator
import view.InputView
import view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardsGenerator: CardsGenerator
) {
    fun run() {
        val playersNames = inputView.inputPlayers()
        val allCards = cardsGenerator.generateCards()

        val players = playersNames.map { name ->
            Player(name, allCards.drawCards(2))
        }

        val playerCardsNames = players.map { it.playerCards.getCardNames() }
        val dealerInitialCards = allCards.drawCards(2)
        val dealerCardNames = dealerInitialCards.getCardNames()

        val dealer = Dealer("딜러", dealerInitialCards)
        outputView.printDealerAndPlayers(playersNames)
        outputView.printInitialCards(dealerCardNames, playersNames, playerCardsNames)

        players.forEach { player ->
            while (true) {
                val score = ScoreCalculator(player.playerCards).totalScore()
                val isDraw = showHitOrStandQuestion(score, player, allCards)
                if (isDraw) break
            }
        }

        val dealerTotalScore = ScoreCalculator(dealer.dealerCards).totalScore()
        if (dealerTotalScore <= 16) {
            outputView.printDealerHit()
            dealer.dealerTurn(allCards)
        }

        val updatedDealerTotalScore = ScoreCalculator(dealer.dealerCards).totalScore()
        val playersTotalScore = players.map { ScoreCalculator(it.playerCards).totalScore() }

        outputView.printDealerResult(dealerInitialCards.getCardNames(), updatedDealerTotalScore)

        val updatedPlayerCardsNames = players.map { it.playerCards.getCardNames() }
        outputView.printPlayerResult(playersNames, updatedPlayerCardsNames, playersTotalScore)

        // 아래 코드 합친 함수
        decideGameResult(dealerTotalScore, players)
    }

    private fun showHitOrStandQuestion(score: Int, player: Player, allCards: Cards): Boolean {
        if (score <= 21) {
            outputView.printHitOrStandQuestion(player.name)
            val decision = inputView.readHitOrStand()
            when (decision) {
                "y" -> {
                    val playerTurn = player.playerTurn(allCards)
                    outputView.printPlayerCards(player.name, player.playerCards.getCardNames())
                    return playerTurn
                }

                "n" -> return true
            }
        }
        return true
    }

    private fun decideGameResult(dealerTotalScore: Int, players: List<Player>) {
        val gameResultOutput = GameResultDecider(dealerTotalScore, players).compareWinOrLose()
        outputView.printResult(
            gameResultOutput.dealerWins,
            gameResultOutput.dealerLosses,
            gameResultOutput.playerResults
        )
    }
}

