package controller

import domain.CardGame
import domain.CardPackGenerator
import model.GameResult
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        CardGame(CardPackGenerator().createCardPack().shuffled(), outputView::printError).apply {
            outputView.printInputPlayerNames()
            val participants = setUp(inputView::readName)
            val betInfos = setBets(participants.players, outputView::printHowMuchBet, inputView::readBet)
            outputView.printNoticeDistributeCards(participants)
            askGetMorePlayersCard(participants.players, outputView::printGetCardMore, inputView::readYesOrNo, outputView::printParticipantStatus)
            getMoreDealerCard(participants.dealer, outputView::printDealerGetCard)
            outputView.printResult(participants, GameResult.of(participants.dealer, betInfos))
        }
    }
}
