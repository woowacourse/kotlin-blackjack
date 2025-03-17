package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun gameStart() {
        val deck = Deck()

        val playerNames = inputView.readPlayersName()
        val players =
            Players.from(
                names = playerNames,
                getMoreCard = inputView::askDrawCard,
                battingAmountProvider = inputView::readPlayersBattingAmount,
            )

        val dealer = Dealer()

        dealer.drawCard(deck::drawCard)
        players.initializeCards(deck::drawCard)
        outputView.printInitialCardsState(dealer, players)

        players.drawMoreCards(deck::drawCard) { name, hand ->
            outputView.printPlayerHand(name, hand)
        }

        dealer.drawMoreCard(deck::drawCard) { participant ->
            outputView.printDealerHand(dealer)
        }

        outputView.printFinalResult(dealer, players)
    }
}
