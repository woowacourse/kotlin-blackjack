package blackjack.controller

import blackjack.domain.card.CardsGenerator
import blackjack.domain.card.RandomCardsGenerator
import blackjack.domain.player.BlackjackManager
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    cardsGenerator: CardsGenerator = RandomCardsGenerator()
) {

    private var blackjackManager: BlackjackManager = BlackjackManager(cardsGenerator)

    fun run() {
        blackjackManager.generateParticipants(inputView::readParticipantsName)
        blackjackManager.settingPlayersCards(outputView::printSettingCard)
        provideParticipantsMoreCard()
        blackjackManager.provideDealerMoreCard(outputView::printDealerHitCardMent)
        blackjackManager.updatePlayersResult()
        printSumResult()
        printFinalResult()
    }

    private fun provideParticipantsMoreCard() {
        blackjackManager.participants.values.forEach {
            blackjackManager.provideParticipantMoreCard(it, inputView::readMoreCard, outputView::printParticipantCards)
        }
    }

    private fun printSumResult() {
        outputView.printSumResult(blackjackManager.dealer, blackjackManager.participants)
    }

    private fun printFinalResult() {
        outputView.printFinalResult(blackjackManager.dealer, blackjackManager.participants)
    }
}
