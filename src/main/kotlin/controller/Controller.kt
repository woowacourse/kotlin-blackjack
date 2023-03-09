package controller

import domain.CardGame
import model.CardDeck
import model.Dealer
import model.Participants
import model.Players
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    private val cardDeck = CardDeck.createCardDeck().shuffled()
    fun run() {
        val participants = Participants(listOf(Dealer()) + Players.from(inputView.readName()))
        val cardGame = CardGame(cardDeck, participants)
        cardGame.readyToStart()
        outputView.printNoticeDistributeCards(participants)
        participants.players.forEach { cardGame.drawCard(it, outputView::printPlayerStatus, inputView::readYesOrNo) }
        cardGame.drawCard(participants.dealer, outputView::printDealerGetCard) { true }
        outputView.printAllPlayerStatusResult(participants.participants)
        outputView.printGameResult(participants)
    }
}
