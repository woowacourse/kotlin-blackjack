package controller

import domain.CardGame
import domain.CardPackGenerator
import model.Bet
import model.BetInfos
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
    private val cardPack = CardPackGenerator().createCardPack().shuffled()

    fun run() {
        val cardGame = CardGame(cardPack)
        val players = cardGame.initPlayers(initNames())
        val dealer = cardGame.initDealer()
        val betInfos = initBetInfo(players)
        val participants = Participants.of(dealer, players)
        printParticipants(participants)
        askGetMorePlayersCard(players)
        getMoreDealerCard(dealer)
        outputView.printAllPlayerStatusResult(participants)
        outputView.printFinalResult(GameResult.of(dealer, betInfos))
    }

    private fun initNames(): Names {
        outputView.printInputPlayerNames()
        return Names(inputView.readName().map(::Name))
    }

    private fun initBetInfo(players: Players): BetInfos =
        BetInfos(
            buildMap {
                players.toList().forEach {
                    put(it, askHowMuchBet(it.name))
                }
            }
        )

    private fun askHowMuchBet(name: Name): Bet {
        outputView.printHowMuchBet(name)
        return Bet(inputView.readBet())
    }

    private fun printParticipants(participants: Participants) {
        outputView.printNoticeDistributeCards(
            Names(participants.toList().filter { it.name.value != DEALER }.map { it.name })
        )
        outputView.printPlayersStatus(participants)
    }

    private fun askGetMorePlayersCard(players: Players) {
        players.toList().filter { it.isHit() }.forEach {
            outputView.printGetCardMore(it.name)
            askGetMorePlayerCard(it)
        }
    }

    private fun askGetMorePlayerCard(player: Player) {
        while (player.isHit() && inputView.readYesOrNo()) {
            player.pick(cardPack)
            outputView.printPlayerStatus(player)
            if (player.isHit()) outputView.printGetCardMore(player.name)
        }
    }

    private fun getMoreDealerCard(dealer: Dealer) {
        while (dealer.isHit()) {
            outputView.printDealerGetCard()
            dealer.pick(cardPack)
        }
    }
}
