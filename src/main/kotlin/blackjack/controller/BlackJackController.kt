package blackjack.controller

import blackjack.domain.blackjack.BlackJackGame
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val blackJack = BlackJackGame().setUp(inputView::inputParticipants, inputView::inputBettingMoney)
        outputView.outputInitState(blackJack)
        BlackJackGame().apply {
            getCommand = inputView::inputDrawMore
            guestsTurn(blackJack.guests, blackJack.cardDeck, outputView::outputCard)
            dealerTurn(blackJack.dealer, blackJack.cardDeck, outputView::outputDealerDraw)
        }
        outputView.outputResult(blackJack)
    }
}
