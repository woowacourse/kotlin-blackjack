package blackjack.controller

import blackjack.domain.BlackJackBuilder
import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    val inputView: InputView,
    val outputView: OutputView,
) {
    fun run() {
        val blackJack = BlackJackBuilder.init {
            cardDeck(Card.all().shuffled())
            dealer("딜러")
            users(inputView.inputParticipants())
        }
        outputView.outputInitState(blackJack.dealer, blackJack.users)

        BlackJackGame().apply {
            input(inputView::inputDrawMore)
            output(outputView::outputCard)
            run(blackJack)
        }
        outputView.outputResult(blackJack.dealer, blackJack.users, blackJack.result)
    }
}
