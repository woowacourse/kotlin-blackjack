package controller

import domain.CardGame
import model.CardDeck
import model.Cards
import model.Dealer
import model.Participants
import model.Players
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    private val cardDeck = CardDeck.createCardDeck().shuffled()
    fun run() {
        val players = Players.from(inputView.readName())
        val dealer = Dealer(Cards(setOf()))
        val participants = Participants(listOf(dealer) + players)
        val cardGame = CardGame(cardDeck, participants)
        cardGame.readyToStart()
        outputView.printNoticeDistributeCards(participants)
        cardGame.drawPlayersCard(players, inputView::readYesOrNo, outputView::printPlayerStatus)
        cardGame.drawDealerCard(dealer) { outputView.printDealerGetCard() }
        outputView.printAllPlayerStatusResult(participants.participants)
        outputView.printGameResult(participants)
    }
}
