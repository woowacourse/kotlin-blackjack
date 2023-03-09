package blackjack.controller

import blackjack.domain.card.CardsGenerator
import blackjack.domain.card.RandomCardsGenerator
import blackjack.domain.player.BlackjackManager
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val cardsGenerator: CardsGenerator = RandomCardsGenerator()
) {

    fun run() {
        val blackjackManager = BlackjackManager(cardsGenerator)
        blackjackManager.setup(inputView::readParticipantsName)
        outputView.printSettingCard(blackjackManager.dealer, blackjackManager.participants)
        blackjackManager.provideParticipantsMoreCard(inputView::readMoreCard, outputView::printParticipantCards)
        blackjackManager.provideDealerMoreCard(outputView::printDealerHitCardMent)
        outputView.printSumResult(blackjackManager.dealer, blackjackManager.participants)
        blackjackManager.calculatePlayersResult(outputView::printFinalResult)
    }
}
