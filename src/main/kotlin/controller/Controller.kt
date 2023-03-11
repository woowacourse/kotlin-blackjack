package controller

import domain.CardGame
import model.CardDeck
import model.Dealer
import model.Money
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
        val participants = Participants(Dealer(), readPlayers())
        val cardGame = CardGame(cardDeck, participants)
        cardGame.readyToStart()
        outputView.printNoticeDistributeCards(participants)
        participants.players.forEach { cardGame.drawCard(it, outputView::printPlayerStatus, inputView::readYesOrNo) }
        cardGame.drawCard(participants.dealer, outputView::printDealerGetCard) { true }
        outputView.printAllPlayerStatusResult(participants)
    }

    private fun readPlayers(): Players {
        val names = Names(inputView.readName().map(::Name))
        return Players(names.map { Player(it, readPlayerBettingMoney(it)) })
    }

    private fun readPlayerBettingMoney(name: Name): Money {
        return Money(inputView.readBettingMoney(name.value))
    }
}
