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
        val blackjackManager =
            BlackjackManager(cardsGenerator, inputView.readParticipantsName(), inputView::readParticipantBetAmount)
        blackjackManager.setupCards(outputView::printSetupCards)
        val gameResult = blackjackManager.playGame(
            inputView::readMoreCard,
            outputView::printParticipantCards,
            outputView::printDealerHitCardMent
        )
        outputView.printScoreResult(gameResult.dealer, gameResult.participants)
        outputView.printPlayersProfit(gameResult.dealerProfit, gameResult.getParticipantsProfit())
    }
}
