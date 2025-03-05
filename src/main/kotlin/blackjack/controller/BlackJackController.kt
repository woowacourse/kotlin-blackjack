package blackjack.controller

import blackjack.domain.GameResult
import blackjack.domain.GameState
import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player
import blackjack.domain.score.ScoreCalculator
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    private lateinit var deck: Deck

    fun play() {
        val players = generatePlayers()
        val dealer = Dealer()
        deck = Deck()

        startGame(dealer, players)
        processPlayerTurns(players)
        processDealerTurns(dealer)

        outputView.printGameResult(dealer, players)
        val gameResult = GameResult(dealer).calculateWin(players)
        outputView.printResult(gameResult)
    }

    private fun generatePlayers(): List<Player> {
        outputView.printNameMessage()
        return inputView.getNames().map { Player(it) }
    }

    private fun startGame(
        dealer: Dealer,
        players: List<Player>,
    ) {
        dealer.draw(deck, 2)
        players.forEach { person -> person.draw(deck, 2) }
        outputView.printDrawMessage(dealer, players)
    }

    private fun processPlayerTurns(players: List<Player>) {
        players.forEach { player ->
            while (true) {
                if (player.checkState()) break

                outputView.printFlagMessage(player.name)
                if (inputView.getFlag()) {
                    player.draw(deck, 1)
                    outputView.printDrawStatus(player)
                    player.gameState = player.checkScore()
                }
            }
        }
    }

    private fun processDealerTurns(dealer: Dealer) {
        while (true) {
            if (dealer.checkState() || ScoreCalculator.calculate(dealer.hand) > 16) break

            outputView.printDealerDrawMessage()
            dealer.draw(deck, 1)
            dealer.gameState = dealer.checkScore()
        }
    }

    private fun Person.checkScore(): GameState {
        val currentScore = ScoreCalculator.calculate(hand)
        return when {
            currentScore == 21 -> GameState.BLACKJACK
            currentScore > 21 -> GameState.BUST
            else -> GameState.HIT
        }
    }

    private fun Person.checkState(): Boolean {
        return this.gameState == GameState.STAY || this.gameState == GameState.BUST
    }
}
