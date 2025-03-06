package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(val inputView: InputView, val outputView: OutputView) {
    private val shuffledCards: List<Card> = Card.getAllCard().shuffled()
    private val deck = Deck(shuffledCards)

    fun run() {
        val dealer = Dealer()
        val players: List<Player> = getPlayers()

        dealer.getCard(deck)
        setPlayerCards(players)
        outputView.showInitialCards(dealer, players)

        askPlayerHit(players)

        if (dealer.haveAdditionalCard()) {
            outputView.printDealerHaveAdditionalCard()
        }

        outputView.printFinalCards(dealer, players)

        showResult(dealer, players)
    }

    private fun getPlayers(): List<Player> {
        return inputView.getPlayerNames().map { playerName ->
            Player(playerName)
        }
    }

    private fun setPlayerCards(players: List<Player>) {
        players.forEach { player ->
            repeat(2) {
                player.addCard(deck.draw())
            }
        }
    }

    private fun askPlayerHit(players: List<Player>) {
        players.forEach { player ->
            while (player.canHit()) {
                val result = inputView.askPlayerHit(player.name)
                if (result) {
                    player.addCard(deck.draw())
                    outputView.printPlayerCards(player)
                }
                if (!result) break
            }
        }
    }

    private fun showResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val result = GameResult(dealer, players).getResult()
        outputView.printGameResult(result)
    }
}
