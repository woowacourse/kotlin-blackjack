package blackjack.controller

import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        with(initBlackJack()) {
            setUpCard(this)

            val result = start(
                onDrawn = { participant -> OutputView.printDrawn(participant) }
            )
            OutputView.printBlackJackResult(result)
        }
    }

    private fun initBlackJack(): BlackJack = BlackJack(CardDeck(), Participants(listOf(Dealer()) + enrollPlayers()))

    private fun enrollPlayers(): List<Player> = InputView.inputNames().map { name ->
        Player(name, needToDraw = {
            InputView.inputDrawCommand(name)
        })
    }

    private fun setUpCard(blackJack: BlackJack) {
        drawInitialCards(blackJack)
        OutputView.printFirstOpenCards(blackJack.getFirstOpenCards())
        OutputView.printInterval()
    }

    private fun drawInitialCards(blackJack: BlackJack) {
        blackJack.readyToStart()
    }
}
