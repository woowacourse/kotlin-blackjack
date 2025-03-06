package blackjack.controller

import blackjack.Dealer
import blackjack.Deck.Companion.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.GameManager
import blackjack.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun play(dealer: Dealer) {
        val players: List<Player> = inputView.readPlayerNames()
        outputView.printSetCardMessageWithPlayers(players)
        val gameManager = GameManager(dealer, players)
        gameManager.dealInitialCardWithCount(INITIAL_HAND_OUT_CARD_COUNT)
        outputView.printAllPlayerHands(dealer, players)
    }
}
