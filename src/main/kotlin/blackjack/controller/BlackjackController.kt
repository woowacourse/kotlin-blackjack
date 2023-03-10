package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        initBlackjack().start(
            onStartFirstDrawn = OutputView::printFirstDrawnMessage,
            onFirstDrawn = OutputView::printFirstOpenCards,
            onDrawnMore = OutputView::printAllCards,
            onEndGame = OutputView::printBlackjackResult
        )
    }

    private fun initBlackjack(): Blackjack = Blackjack(CardDeck(), enrollPlayers())

    private fun enrollPlayers(): List<Participant> = InputView.inputPlayers()
}
