package blackjack.controller

import blackjack.domain.GameResult
import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    private lateinit var deck: Deck

    fun play() {
        val players = initializePlayers()
        val dealer = Dealer()
        deck = Deck()

        dealCards(dealer, players)
        processPlayerTurns(players)
        processDealerTurns(dealer)

        showGameResult(dealer, players)
    }

    private fun initializePlayers(): List<Player> {
        outputView.printEnterPlayerNamesMessage()
        return inputView.getNames().map { Player(it) }
    }

    private fun dealCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        repeat(FIRST_TURN_DRAW_AMOUNT) {
            dealer.draw(deck)
            players.forEach { player -> player.draw(deck) }
        }
        outputView.printInitialDrawMessage(dealer, players)
    }

    private fun processPlayerTurns(players: List<Player>) {
        players.forEach { player ->
            val playerTurn = PlayerTurn(player, deck)
            playPlayerTurn(playerTurn)
        }
    }

    private fun playPlayerTurn(playerTurn: PlayerTurn) {
        playerTurn.play(
            askDraw = { outputView.printAskForDrawCardMessage(it) },
            getFlag = { inputView.getFlag() },
            printDrawStatus = { outputView.printPlayerDrawStatus(it) },
        )
    }

    private fun processDealerTurns(dealer: Dealer) {
        val dealerTurn = DealerTurn(dealer, deck)
        dealerTurn.play(
            printDealerDraw = { outputView.printDealerDrawMessage() },
        )
    }

    private fun showGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printDealerResult(dealer)
        players.forEach { player -> outputView.printPlayerResult(player) }

        val gameResult = GameResult(dealer, players)
        outputView.printGameResults(gameResult)
    }

    companion object {
        private const val FIRST_TURN_DRAW_AMOUNT = 2
    }
}
