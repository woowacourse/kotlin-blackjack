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
        outputView.outputInitState(blackJack.dealer, blackJack.users)
        runBlackJack(blackJack)
        outputView.outputResult(blackJack.dealer, blackJack.users, blackJack.result)
    }

    private fun setBlackJack(): BlackJack = BlackJackBuilder.init {
        cardDeck(Card.all().shuffled())
        participants {
            dealer("딜러")
            users(inputView.inputParticipants())
        }
        draw()
    }

    private fun runBlackJack(blackJack: BlackJack) = BlackJackGame().apply {
        input(inputView::inputDrawMore)
        output(outputView::outputCard)
        dealerOutput(outputView::outputDealerDraw)
        blackJack.run()
    }
}
