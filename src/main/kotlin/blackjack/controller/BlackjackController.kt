package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        with(initBlackjack()) {
            setBetMoney(this, inputBetMoney = InputView::inputBetMoney)
            setUpCard(this)

            val result = start(onDrawn = OutputView::printDrawn)
            OutputView.printBlackjackResult(result)
        }
    }

    private fun initBlackjack(): Blackjack = Blackjack(CardDeck(), enrollPlayers())

    private fun enrollPlayers(): List<Player> {
        return InputView.inputNames().map { name ->
            Player(name, needToDraw = { InputView.inputDrawCommand(name) })
        }
    }

    private fun setBetMoney(blackJack: Blackjack, inputBetMoney: (String) -> Int) =
        blackJack.setBetMoney(inputBetMoney)

    private fun setUpCard(blackJack: Blackjack) {
        drawInitialCards(blackJack)
        OutputView.printFirstOpenCards(blackJack.getFirstOpenCards())
        OutputView.printInterval()
    }

    private fun drawInitialCards(blackJack: Blackjack) {
        blackJack.readyToStart()
    }
}
