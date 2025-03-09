package blackjack.controller

import blackjack.domain.GameResult
import blackjack.domain.ScoreCalculator
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
        dealer.draw(deck)
        players.forEach { player -> player.draw(deck) }
        outputView.printInitialDrawMessage(dealer, players)
    }

    private fun processPlayerTurns(players: List<Player>) {
        players.forEach { player -> playPlayerTurn(player) }
    }

    private fun playPlayerTurn(player: Player) {
        while (player.canDraw()) {
            outputView.printAskForDrawCardMessage(player.name)
            letPlayerDrawCard(player)
        }
    }

    private fun letPlayerDrawCard(player: Player) {
        if (inputView.getFlag()) {
            player.draw(deck)
            outputView.printPlayerDrawStatus(player)
            return
        }
        player.changeToStay()
    }

    private fun processDealerTurns(dealer: Dealer) {
        while (dealer.canDraw()) {
            outputView.printDealerDrawMessage()
            dealer.draw(deck)
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printDealerResult(dealer, ScoreCalculator.calculate(dealer.cards()))
        players.forEach { player ->
            outputView.printPlayerResult(player, ScoreCalculator.calculate(player.cards()))
        }

        val gameResult = GameResult(dealer, players)
        outputView.printGameResults(gameResult)
    }
}
