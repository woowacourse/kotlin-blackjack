package blackjack.controller

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.blackjack.BlackJackGame
import blackjack.domain.blackjack.blackJack
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun run() {
        val blackJack = setUpBlackJack()
        val bettingMoney = blackJack.betMoney(inputView::inputBettingMoney)
        outputView.outputInitState(blackJack)
        startBlackJack(blackJack)
        outputView.outputResult(blackJack)
        outputView.outputProfits(blackJack, bettingMoney)
    }

    private fun setUpBlackJack(): BlackJack = blackJack {
        participants {
            dealer()
            guests(inputView.inputParticipants())
        }
        initDraw()
    }

    private fun startBlackJack(blackJack: BlackJack) =
        BlackJackGame().apply {
            getCommand = inputView::inputDrawMore
            guestsTurn(blackJack.guests, blackJack.cardDeck, outputView::outputCard)
            dealerTurn(blackJack.dealer, blackJack.cardDeck, outputView::outputDealerDraw)
        }
}
