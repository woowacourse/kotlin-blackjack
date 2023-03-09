package controller

import domain.CardGame
import domain.CardPackGenerator
import model.GameResult
import model.Names
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        val cardPack = CardPackGenerator().createCardPack().shuffled()
        CardGame(cardPack, outputView::printError).apply {
            outputView.printInputPlayerNames()
            val names = initName(inputView::readName)
            val participants = setUp(names)
            val betInfos = setBets(participants.players, outputView::printHowMuchBet, inputView::readBet)
            outputView.printNoticeDistributeCards(Names(participants.players.toList().map { it.name }))
            outputView.printParticipantsStatus(participants)
            askGetMorePlayersCard(participants.players, outputView::printGetCardMore, inputView::readYesOrNo, outputView::printParticipantStatus)
            getMoreDealerCard(participants.dealer, outputView::printDealerGetCard)
            val result = GameResult.of(participants.dealer, betInfos)
            outputView.printAllPlayerStatusResult(participants)
            outputView.printFinalResult(result)
        }
    }
}
