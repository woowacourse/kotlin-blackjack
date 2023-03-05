package controller

import domain.CardGame
import domain.CardPackGenerator
import domain.CardPicker
import model.Cards
import model.Dealer
import model.Dealer.Companion.DEALER
import model.GameResult
import model.Name
import model.Names
import model.Participants
import model.Player
import model.Players
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    private val picker = CardPicker(CardPackGenerator().createCardPack().shuffled())

    fun run() {
        val cardGame = CardGame(picker)
        val players = cardGame.initPlayers(initNames())
        val dealer = cardGame.initDealer()
        val participants = Participants.of(dealer, players)
        printParticipants(participants)
        askGetMorePlayersCard(players)
        getMoreDealerCard(dealer)
        outputView.printAllPlayerStatusResult(participants)
        outputView.printFinalResult(GameResult.of(dealer, players))
    }

    private fun initNames(): Names {
        outputView.printInputPlayerNames()
        return Names(inputView.readName().map(::Name))
    }

    private fun printParticipants(participants: Participants) {
        outputView.printNoticeDistributeCards(participants.filter { it.name.value != DEALER }.map { it.name })
        outputView.printPlayersStatus(participants)
    }

    private fun askGetMorePlayersCard(players: Players) {
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

    private fun Cards.shuffled(): Cards {
        return Cards(cards.shuffled())
    }
}
