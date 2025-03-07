package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val shuffledCards: List<Card> = Card.getAllCard().shuffled()
    private val deck = Deck(shuffledCards)

    fun run() {
        val dealer = Dealer()
        val players: List<Player> = getPlayers()

        dealer.getCard(deck)
        setInitialPlayerCards(players)
        outputView.showInitialCards(dealer, players)

        askPlayerHit(players)

        if (dealer.hasAdditionalCard()) {
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

    private fun setInitialPlayerCards(players: List<Player>) {
        players.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.addCard(deck.draw())
            }
        }
    }

    private fun askPlayerHit(players: List<Player>) {
        players.forEach { player ->
            handlePlayerHit(player)
        }
    }

    private fun handlePlayerHit(player: Player) {
        while (player.canHit()) {
            val result = inputView.askPlayerHit(player.name)
            if (result) {
                player.addCard(deck.draw())
                outputView.printPlayerCards(player)
            } else {
                break
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

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
