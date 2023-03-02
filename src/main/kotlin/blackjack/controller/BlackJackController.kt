package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.BlackJackBuilder
import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val blackJack = setBlackJack()
        outputView.outputInitState(blackJack)
        startBlackJack(blackJack)
        outputView.outputResult(blackJack)
    }

    private fun setBlackJack(): BlackJack = BlackJackBuilder.init {
        cardDeck(Card.all().shuffled())
        participants {
            dealer("딜러")
            guests(inputView.inputParticipants())
        }
        draw()
    }

    private fun startBlackJack(blackJack: BlackJack) =
        BlackJackGame().apply {
            input(inputView::inputDrawMore)
            blackJack.guestsTurn(outputView::outputCard)
            blackJack.dealerTurn(outputView::outputDealerDraw)
        }
}
