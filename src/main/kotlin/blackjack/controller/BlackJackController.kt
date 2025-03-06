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
        val players = generatePlayers()
        val dealer = Dealer()
        deck = Deck()

        settingInitialCards(dealer, players)
        processPlayerTurns(players)
        processDealerTurns(dealer)

        showGameResult(dealer, players)
    }

    private fun generatePlayers(): List<Player> {
        outputView.printNameMessage()
        return inputView.getNames().map { Player(it) }
    }

    private fun settingInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        dealer.draw(deck)
        players.forEach { person -> person.draw(deck, true) }
        outputView.printDrawMessage(dealer, players)
    }

    private fun processPlayerTurns(players: List<Player>) {
        players.forEach { player -> handlePlayerTurn(player) }
    }

    private fun handlePlayerTurn(player: Player) {
        while (player.isCanDraw()) {
            outputView.printFlagMessage(player.name)
            letPlayerDrawCard(player)
        }
    }

    private fun letPlayerDrawCard(player: Player) {
        val isHit = inputView.getFlag()
        player.draw(deck, isHit)
        if (isHit) {
            outputView.printDrawStatus(player)
        }
    }

    private fun processDealerTurns(dealer: Dealer) {
        while (dealer.isCanDraw()) {
            outputView.printDealerDrawMessage()
            dealer.draw(deck)
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printGameResult(dealer, players)
        val gameResult = GameResult(dealer).calculateWin(players)
        outputView.printResult(gameResult)
    }
}
