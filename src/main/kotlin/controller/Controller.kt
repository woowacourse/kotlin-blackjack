package controller

import domain.CardGame
import domain.CardPackGenerator
import domain.CardPicker
import model.Cards
import model.Dealer
import model.Dealer.Companion.DEALER
import model.GameResult
import model.Name
import model.Participant
import model.Player
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    private val picker = CardPicker(Cards(CardPackGenerator().createCards().cards.shuffled()))

    fun run() {
        val cardGame = CardGame(picker)
        val players = cardGame.initPlayers(initNames())
        val dealer = cardGame.initDealer()
        val participants = listOf(dealer as Participant) + (players as List<Participant>)
        printParticipants(participants)
        askGetMorePlayersCard(players)
        getMoreDealerCard(dealer)
        outputView.printAllPlayerStatusResult(participants)
        outputView.printFinalResult(GameResult.of(dealer, players))
    }

    private fun initNames(): List<Name> {
        outputView.printInputPlayerNames()
        return inputView.readName()
    }

    private fun printParticipants(participants: List<Participant>): List<Participant> {
        outputView.printNoticeDistributeCards(participants.filter { it.name.value != DEALER }.map { it.name })
        outputView.printPlayersStatus(participants)
        return participants
    }

    private fun askGetMorePlayersCard(players: List<Player>) {
        players.filter { it.isHit() }.forEach {
            outputView.printGetCardMore(it.name)
            askGetMorePlayerCard(it)
        }
    }

    private fun askGetMorePlayerCard(player: Player) {
        while (player.isHit() && inputView.readYesOrNo()) {
            player.cards.add(picker.pick())
            outputView.printPlayerStatus(player)
            if (player.isHit()) outputView.printGetCardMore(player.name)
        }
    }

    private fun getMoreDealerCard(dealer: Dealer) {
        while (dealer.isHit()) {
            outputView.printDealerGetCard()
            dealer.cards.add(picker.pick())
        }
    }
}
