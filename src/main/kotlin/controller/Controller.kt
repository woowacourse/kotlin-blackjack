package controller

import domain.CardGame
import model.CardDeck
import model.Dealer
import model.Participants
import model.Player
import model.Players
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        val participants = Participants(listOf(Dealer()) + readPlayers())
        val cardGame = CardGame(CardDeck.createCardDeck().shuffled(), participants)
        cardGame.readyToStart()
        outputView.printNoticeDistributeCards(participants)
        participants.players.forEach { cardGame.drawCard(it, outputView::printParticipantStatus) }
        cardGame.drawCard(participants.dealer, outputView::printParticipantStatus)
        outputView.printParticipantsStatus(participants)
        outputView.printGameResult(cardGame.getGameResult())
    }

    private fun readPlayers(): Players {
        val names = inputView.readName()
        return Players(names.map { Player.of(it, inputView.readBettingMoney(it), inputView::readYesOrNo) })
    }
}
