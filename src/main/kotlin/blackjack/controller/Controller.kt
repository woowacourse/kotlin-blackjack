package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val deck = Deck.createShuffled()
    private val dealer = Dealer()

    fun gameStart() {
        val playerNames = inputView.readPlayersName()
        playerNames.forEach {
            val bettingAmount = inputView.readPlayersBattingAmount(it)
            Player(it, bettingAmount)
        }

        players.players.forEach { it.drawInitialCards(deck) }
        dealer.drawInitialCards(deck)

        outputView.printInitialCardsState(dealer, players)

        players.players.forEach { player ->
            while (!player.state.hand.hasBust() && !player.state.hand.hasBlackjack() && inputView.askDrawCard(player.name)) {
                player.drawCard(deck.draw())
                outputView.printPlayerResult(player)
            }
            player.stay()
        }

        while (dealer.drawMoreCard()) {
            dealer.playTurn(deck)
            outputView.printDealerDrawCard()
        }

        dealer.stay()

        outputView.printFinalResults(dealer, players)

        players.calculateProfits(dealer.state)

        outputView.printFinalProfit(dealer, players)
    }
}
