package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
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

        setInitialHands(dealer, players)
        outputView.showInitialCards(dealer, players)

        askPlayerHit(players)

        handleDealerHit(dealer)

        showResult(dealer, players)
    }

    private fun handleDealerHit(dealer: Dealer) {
        if (dealer.canHit()) {
            outputView.printDealerHaveAdditionalCard()
            while (dealer.canHit()) {
                dealer.addCard(deck.draw())
            }
        }
    }

    private fun getPlayers(): List<Player> {
        return inputView.getPlayerNames().map { playerName ->
            Player(playerName)
        }
    }

    private fun setInitialHands(
        dealer: Dealer,
        players: List<Player>,
    ) {
        repeat(INITIAL_CARD_COUNT) {
            dealer.addCard(deck.draw())
        }
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
        val gameResult = GameResult(dealer, players)
        gameResult.updateGameResult()
        outputView.printFinalCards(dealer, players)
        outputView.printGameResult(dealer, gameResult.playersGameResult)
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
