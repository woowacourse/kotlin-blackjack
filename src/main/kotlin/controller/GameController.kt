package controller

import model.card.Deck
import model.participants.Answer
import model.participants.Dealer
import model.participants.Hand
import model.participants.HumanName
import model.participants.Player
import model.participants.Players
import model.result.Judge
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(Hand(deck))
        val players = handleException { readPlayers() }

        initGame(dealer = dealer, players = players)
        handleException { playGame(dealer = dealer, players = players) }

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
        dealer.hit()
    }

    private fun initPlayers(players: Players) {
        players.players.forEach {
            it.hit()
            it.hit()
        }
    }

    private fun playGame(
        dealer: Dealer,
        players: Players,
    ) {
        players.players.forEach(::playOfOnePlayer)

        val hitCount = dealer.play()
        repeat(hitCount) { OutputView.showDealerHit() }
    }

    private fun readPlayers(): Players {
        return InputView.readPlayerNames().run {
            Players.ofList(this, deck)
        }
    }

    private fun readAnswer(humanName: HumanName): Answer {
        return InputView.readAnswer(humanName).run {
            Answer.fromInput(this)
        }
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
        OutputView.showHumanHand(player)
        return isBusted
    }

    private fun playOfOnePlayer(player: Player) {
        while (playByAnswer(readAnswer(player.humanName), player)) ;
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        OutputView.showHumanHandWithResult(dealer)
        OutputView.showPlayersHandWithResult(players)

        judge(players = players, dealer = dealer)
    }

    private fun judge(
        dealer: Dealer,
        players: Players,
    ) {
        val playersResult = Judge.getPlayersResult(players, dealer)
        val dealerResult = Judge.getDealerResult(playersResult)

        OutputView.showResultHeader()
        OutputView.showDealerResult(dealerResult)
        OutputView.showPlayersResult(players, playersResult)
    }

    private fun <T> handleException(block: () -> T): T =
        runCatching { block() }.getOrElse {
            OutputView.showThrowable(it)
            handleException(block)
        }
}
