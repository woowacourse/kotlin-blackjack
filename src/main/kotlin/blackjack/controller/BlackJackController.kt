package blackjack.controller

import blackjack.domain.blackjack.BlackJackGame
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val data = BlackJackGame().setUp(inputView::inputParticipants, inputView::inputBettingMoney)
        outputView.outputInitState(data)
        BlackJackGame().apply {
            onDraw = inputView::inputDrawMore
            guestsTurn(data.guests, data.cardDeck, outputView::outputCard)
            dealerTurn(data.dealer, data.cardDeck, outputView::outputDealerDraw)
        }
        outputView.outputResult(data)
    }
}
