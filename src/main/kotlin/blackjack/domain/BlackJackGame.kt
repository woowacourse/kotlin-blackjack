package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.result.GameResult
import blackjack.view.BlackJackInputView
import blackjack.view.BlackJackOutputView

class BlackJackGame(
    private val inputView: BlackJackInputView,
    private val outputView: BlackJackOutputView,
) {
    private lateinit var deck: Deck
    private lateinit var dealer: Dealer
    private lateinit var players: List<Player>

    fun play() {
        prepareGame()
        runGame()
        finishGame()
    }

    private fun prepareGame() {
        deck = Deck()
        dealer = Dealer()
        players = generatePlayers()
    }

    private fun runGame() {
        dealCards()
        playPlayersTurns()
        playDealerTurns()
    }

    private fun finishGame() {
        showPersonFinalCardStatus()
        showGameResult()
    }

    private fun generatePlayers(): List<Player> {
        val names = readPlayerNames()
        return names.map { name ->
            val betAmount = readPlayerBetAmount(name)
            Player(name, betAmount)
        }
    }

    private fun dealCards() {
        repeat(FIRST_TURN_DRAW_AMOUNT) {
            dealer.draw(deck)
            players.forEach { player -> player.draw(deck) }
        }

        outputView.printInitialDrawMessage(dealer, players)
    }

    private fun playPlayersTurns() {
        players.forEach { player -> playPlayerTurns(player) }
    }

    private fun playDealerTurns() {
        while (dealer.isDrawable()) {
            outputView.printDealerDrawNotice()
            dealer.draw(deck)
        }
    }

    private fun showPersonFinalCardStatus() {
        outputView.printPersonResult(dealer)
        players.forEach { player -> outputView.printPersonResult(player) }
    }

    private fun showGameResult() {
        val gameResult = GameResult(dealer, players)
        outputView.printGameResult(gameResult)
    }

    private fun readPlayerNames(): List<String> =
        retryWhenException {
            inputView.getNames()
        }

    private fun readPlayerBetAmount(name: String): BetAmount =
        retryWhenException {
            BetAmount(inputView.getBetAmount(name))
        }

    private fun playPlayerTurns(player: Player) {
        while (player.isDrawable()) {
            playPlayerTurn(player)
        }
    }

    private fun playPlayerTurn(player: Player) {
        if (isHit(player.name)) {
            player.draw(deck)
            outputView.printPlayerDrawStatus(player)
            return
        }

        player.changeToStay()
    }

    private fun isHit(name: String): Boolean =
        retryWhenException {
            inputView.getIsHit(name)
        }

    private fun <T> retryWhenException(action: () -> T) =
        blackjack.utils.retryWhenException(
            action = action,
            onFailure = { e -> outputView.printMessage(e.message) },
        )

    companion object {
        private const val FIRST_TURN_DRAW_AMOUNT = 2
    }
}
