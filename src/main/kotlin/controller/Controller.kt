package controller

import domain.CardGame
import domain.CardPackGenerator
import domain.CardPicker
import model.Cards
import model.Dealer
import model.GameResult
import model.Name
import model.Participant
import model.Player
import view.InputView
import view.OutputView

class Controller(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        val names = initNames()
        val cardPack = Cards(CardPackGenerator().createCards().cards.shuffled())
        val cardPicker = CardPicker(cardPack)
        val cardGame = CardGame(cardPicker)
        val players = initPlayers(names, cardGame)
        val dealer = initDealer(cardGame)
        val participants = initParticipants(dealer, players)
        getMorePlayersCard(players, cardPicker)
        getMoreDealerCard(dealer, cardPicker)
        allPlayerStatusResult(participants)
        makeCardGameResult(dealer, players)
    }

    fun initNames(): List<Name> {
        outputView.printInputPlayerNames()
        return inputView.readName()
    }

    fun initPlayers(names: List<Name>, game: CardGame): List<Player> {
        return names.map { Player(game.pickTwice(), it) }
    }

    fun initDealer(game: CardGame): Dealer {
        return Dealer(game.pickTwice())
    }

    fun initParticipants(dealer: Dealer, player: List<Player>): List<Participant> {
        val participants = buildList {
            add(dealer as Participant)
            addAll(player as List<Participant>)
        }
        outputView.printDistributeCards(player)
        outputView.printPlayersStatus(participants)
        return participants
    }

    fun getMorePlayersCard(players: List<Player>, picker: CardPicker) {
        for (player in players) {
            if (!player.isHit()) continue
            outputView.printGetCardMore(player.name)
            while (player.isHit() && inputView.readYesOrNo()) {
                player.cards.add(picker.pick())
                outputView.printPlayerStatus(player)
                if (player.isHit()) outputView.printGetCardMore(player.name)
            }
        }
    }

    fun getMoreDealerCard(dealer: Dealer, picker: CardPicker) {
        while (dealer.isHit()) {
            outputView.printDealerGetCard()
            dealer.cards.add(picker.pick())
        }
    }

    fun allPlayerStatusResult(participants: List<Participant>) {
        outputView.printAllPlayerStatusResult(participants)
    }

    fun makeCardGameResult(dealer: Dealer, players: List<Player>) {
        outputView.printFinalResult(GameResult.of(dealer, players))
    }
}
