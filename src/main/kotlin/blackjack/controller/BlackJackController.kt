package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardDeck: CardDeck,
) {
    fun startGame() {
        val playerNames = getPlayerNames()

        val dealer = Dealer(playerNames.toSet())
        val players = List(playerNames.size) { Player(playerNames[it]) { inputView.readContinueInput(playerNames[it]) ?: "" } }

        dealer.drawCard { cardDeck.pick() }
        players.forEach { player ->
            player.initializeCards {
                cardDeck.pick()
            }
        }

        val dealerStat = dealer.getStat()
        val playersStats = players.map { it.getStat() }

        outputView.printInitialStats(dealerStat, playersStats)
    }

    private fun getPlayerNames(): List<String> {
        return runCatching {
            inputView.readPlayerNames() ?: getPlayerNames()
        }.onFailure {
            println(it.message)
            return getPlayerNames()
        }.getOrThrow()
    }
}
