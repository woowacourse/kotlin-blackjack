package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.domain.card.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {
    fun start() {
        Blackjack(deck = CardDeck(), players = InputView.inputPlayers()).start(
            onStartDrawn = OutputView::printFirstDrawnMessage,
            onFirstDrawn = OutputView::printFirstOpenCards,
            onDrawnMore = OutputView::printAllCards,
            onEndGame = OutputView::printBlackjackResult
        )
    }
}
