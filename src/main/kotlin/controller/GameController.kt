package controller

import ExceptionHandler
import model.Answer
import model.Hand
import model.Judge
import model.card.Deck
import model.human.Dealer
import model.human.HumanName
import model.human.Player
import model.human.Players
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(Hand(deck))
        val players = readPlayers()

        initGame(dealer = dealer, players = players)
        playGame(dealer = dealer, players = players)

        showGameResult(dealer = dealer, players = players)
    }

    private fun initGame(
        dealer: Dealer,
        players: Players,
    ) {
        initDealer(dealer)
        initPlayers(players)
        OutputView.showGameInit(dealer, players)
    }

    private fun initDealer(dealer: Dealer) {
        dealer.hits(1)
    }

    private fun initPlayers(players: Players) {
        players.players.forEach {
            it.hits(2)
        }
    }

    private fun playGame(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach (::playOfOnePlayer)
        dealer.hit()

        while (dealer.getPointIncludingAce().amount < 17) {
            OutputView.drawCardForDealer()
            dealer.hit()
        }
    }

    private fun readPlayers(): Players {
        return ExceptionHandler.handleInputValue {
            InputView.readPlayerNames().run {
                Players.ofList(this, deck)
            }
        }
    }

    private fun readAnswer(humanName: HumanName): Answer {
        return ExceptionHandler.handleInputValue {
            InputView.readAnswer(humanName).run {
                Answer.fromInput(this)
            }
        }
    }

    private fun playOfOnePlayer(player: Player) {
        while (playByAnswer(readAnswer(player.humanName), player));
    }

    private fun playByAnswer(
        answer: Answer,
        player: Player,
    ): Boolean {
        val isBusted =
            when (answer) {
                Answer.YES -> player.hit()
                Answer.NO -> false
            }
        OutputView.showPlayerHand(player)
        return isBusted
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        OutputView.showDealerHandWithResult(dealer)
        OutputView.showPlayersHandWithResult(players)

        judge(players = players, dealer = dealer)
    }

    private fun judge(
        players: Players,
        dealer: Dealer,
    ) {
        val playersResult = Judge.getPlayersResult(players, dealer)
        val dealerResult = Judge.getDealerResult(playersResult)

        OutputView.showResultHeader()
        OutputView.showDealerResult(dealerResult)
        OutputView.showPlayersResult(players, playersResult)
    }
}
