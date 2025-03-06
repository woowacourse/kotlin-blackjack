package blackjack.controller

import blackjack.Dealer
import blackjack.Deck
import blackjack.Deck.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.DrawChoice
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
        outputView.printInitialHandOutCardMessage(players)
        val gameManager = GameManager(dealer, players)
        gameManager.dealInitialCardWithCount(INITIAL_HAND_OUT_CARD_COUNT)
        outputView.printAllPlayerHands(dealer, players)

        players.forEach { player ->
            while (true) {
                val condition: DrawChoice = inputView.readMoreCardCondition(player)
                if (condition == DrawChoice.NO) {
                    outputView.printPlayerHands(player)
                    break
                }
                player.addCard(Deck.draw())
                outputView.printPlayerHands(player)
                if (player.isBurst()) break
            }
        }

        val moreCard = dealer.isMoreCard()
        if (moreCard) {
            dealer.addCard(Deck.draw())
        }
        outputView.printDealerHandStatus(moreCard)

        outputView.printFinalHandStatus(dealer, players)
    }
}
