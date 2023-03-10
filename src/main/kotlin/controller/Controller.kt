package controller

import domain.CardGame
import model.CardDeck
import model.Dealer
import model.Name
import model.Names
import model.Participants
import model.Player
import model.Players
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    private val cardDeck = CardDeck.createCardDeck().shuffled()
    fun run() {
        val participants = Participants(Dealer(), readPlayersBettingMoney(readPlayersName()))
        val cardGame = CardGame(cardDeck, participants)
        cardGame.readyToStart()
        outputView.printNoticeDistributeCards(participants)
        participants.players.forEach { cardGame.drawCard(it, outputView::printPlayerStatus, inputView::readYesOrNo) }
        cardGame.drawCard(participants.dealer, outputView::printDealerGetCard) { true }
        outputView.printAllPlayerStatusResult(participants.all)
        outputView.printGameResult(participants)
    }

    private fun readPlayersName(): Names {
        return Names(inputView.readName().map(::Name))
    }

    private fun readPlayersBettingMoney(names: Names): Players {
        return Players(names.map { Player.of(it, inputView.readBettingMoney(it.value)) })
    }
}
