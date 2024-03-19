package controller

import ExceptionHandler
import model.Answer
import model.BlackjackGame
import model.Hand
import model.card.Deck
import model.human.Dealer
import model.human.HumanName
import model.human.Player
import model.human.Players
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    private val blackjackGame = BlackjackGame(deck)

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
                it.humanInfo.setMoney(readBettingAmount(it))
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
        blackjackGame.initGame(dealer = dealer, players = players, OutputView::showGameInit)
    }

    private fun playGame(
        dealer: Dealer,
        players: Players,
    ) {
        blackjackGame.play(dealer = dealer, players = players, ::playOfOnePlayer)
        printDealerCardDraw(dealer)
    }

    private fun playOfOnePlayer(player: Player) {
        if (player.isHittable()) {
            while (blackjackGame.playByAnswer(
                    readAnswer(player.humanInfo.humanName),
                    player,
                    OutputView::showPlayerHand,
                )
                );
        }
    }

    private fun printDealerCardDraw(dealer: Dealer) {
        repeat(dealer.getCountOfAdditionalDraw()) {
            OutputView.drawCardForDealer()
        }
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

        OutputView.showResultHeader()
        val gameResult = blackjackGame.judgeWinningResult(dealer = dealer, players = players)
        OutputView.showTotalResult(dealer = dealer, gameResult)
    }
}
