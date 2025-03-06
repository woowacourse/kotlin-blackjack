package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Deck.INITIAL_HAND_OUT_CARD_COUNT
import blackjack.model.DrawChoice
import blackjack.model.GameManager
import blackjack.model.Player
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
