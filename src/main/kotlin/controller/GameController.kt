package controller

import ExceptionHandler
import model.Answer
import model.Hand
import model.ResultType
import model.card.Deck
import model.human.Dealer
import model.human.Human
import model.human.HumanName
import model.human.Player
import model.human.Players
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(Hand())
        val players = readPlayers()
        setBet(players)

        initGame(dealer = dealer, players = players)
        playGame(dealer = dealer, players = players)

        showGameResult(dealer = dealer, players = players)
    }

    private fun readPlayers(): Players {
        return ExceptionHandler.handleInputValue {
            InputView.readPlayerNames().run {
                Players.ofList(this)
            }
        }
    }

    private fun setBet(players: Players) {
        return ExceptionHandler.handleInputValue {
            players.players.forEach {
                it.humanInfo.changeMoney(readBettingAmount(it))
            }
        }
    }

    private fun readBettingAmount(player: Player): Int {
        return ExceptionHandler.handleInputValue {
            InputView.readBettingAmount(player)
        } ?: readBettingAmount(player)
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
        initPlayer(dealer)
    }

    private fun initPlayers(players: Players) {
        players.players.forEach { player ->
            initPlayer(player)
        }
    }

    private fun initPlayer(human: Human) {
        repeat(2) {
            human.hand.draw(deck.pop())
        }
    }

    private fun playGame(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach(::playOfOnePlayer)
        dealer.play(deck)

        repeat(dealer.getCountOfAdditionalDraw()) {
            OutputView.drawCardForDealer()
        }
    }

    private fun playOfOnePlayer(player: Player) {
        while (playByAnswer(readAnswer(player.humanInfo.humanName), player));
    }

    private fun playByAnswer(
        answer: Answer,
        player: Player,
    ): Boolean {
        val isBusted =
            when (answer) {
                Answer.YES -> {
                    player.hand.draw(deck.pop())
                    player.isPossible()
                }

                Answer.NO -> false
            }
        OutputView.showPlayerHand(player)
        return isBusted
    }

    private fun readAnswer(humanName: HumanName): Answer {
        return ExceptionHandler.handleInputValue {
            InputView.readAnswer(humanName) ?: readAnswer(humanName)
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        OutputView.showDealerHandWithResult(dealer)
        OutputView.showPlayersHandWithResult(players)

        totalJudge(dealer = dealer, players = players)
        OutputView.showResultHeader()
        OutputView.showTotalResult(dealer = dealer, players = players)
    }

    private fun totalJudge(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach { player ->
            judge(dealer, player)
        }
    }

    private fun judge(
        dealer: Dealer,
        player: Player,
    ) {
        when (dealer.getCompareResult(player)) {
            ResultType.WIN -> {
                dealer.humanInfo.exchangeMoney(player.humanInfo, -1.0)
            }

            ResultType.LOSE -> {
                if (player.isBlackJack()) {
                    dealer.humanInfo.exchangeMoney(player.humanInfo, 1.5)
                } else {
                    dealer.humanInfo.exchangeMoney(player.humanInfo, 1.0)
                }
            }

            ResultType.DRAW -> {
                dealer.humanInfo.exchangeMoney(player.humanInfo, 0.0)
            }
        }
    }
}
