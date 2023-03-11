package blackjack.controller

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.blackjack.BlackJackGame
import blackjack.domain.blackjack.blackJack
import blackjack.domain.participants.UsersBettingMoney
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    private lateinit var bettingMoney: UsersBettingMoney

    fun run() {
        val blackJack = setUpBlackJack()
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
        bettingMoney = initBetting(inputView::inputBettingMoney)
        initDraw()
    }

    private fun startBlackJack(blackJack: BlackJack) =
        BlackJackGame().apply {
            getCommand = inputView::inputDrawMore
            guestsTurn(blackJack.guests, blackJack.cardDeck, outputView::outputCard)
            dealerTurn(blackJack.dealer, blackJack.cardDeck, outputView::outputDealerDraw)
        }
}
