package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.person.PlayerBetInfo
import blackjack.domain.result.GameResult
import blackjack.view.BlackJackInputView
import blackjack.view.BlackJackOutputView

class BlackJackGame(
    private val inputView: BlackJackInputView,
    private val outputView: BlackJackOutputView,
) {
    private lateinit var deck: Deck
    private lateinit var dealer: Dealer
    private lateinit var playerBetInfos: List<PlayerBetInfo>

    fun play() {
        prepareGame()
        runGame()
        finishGame()
    }

    private fun prepareGame() {
        deck = Deck()
        dealer = Dealer()
        playerBetInfos = generatePlayers()
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

    private fun generatePlayers(): List<PlayerBetInfo> {
        val players = readPlayerNames()
        return players.map { player ->
            val betAmount = readPlayerBetAmount(player.name)
            PlayerBetInfo(player, betAmount)
        }
    }

    private fun dealCards() {
        repeat(FIRST_TURN_DRAW_AMOUNT) {
            dealer.draw(deck)
            playerBetInfos.forEach { info -> info.player.draw(deck) }
        }

        outputView.printInitialDrawMessage(dealer, playerBetInfos.map { info -> info.player })
    }

    private fun playPlayersTurns() {
        playerBetInfos.forEach { info -> playPlayerTurns(info.player) }
    }

    private fun playDealerTurns() {
        while (dealer.isDrawable()) {
            outputView.printDealerDrawNotice()
            dealer.draw(deck)
        }
    }

    private fun showPersonFinalCardStatus() {
        outputView.printPersonResult(dealer)
        playerBetInfos.forEach { info -> outputView.printPersonResult(info.player) }
    }

    private fun showGameResult() {
        val gameResult = GameResult(dealer, playerBetInfos)
        outputView.printGameResult(gameResult)
    }

    private fun readPlayerNames(): List<Player> =
        retryWhenException {
            inputView.getNames().map { name -> Player(name) }
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
