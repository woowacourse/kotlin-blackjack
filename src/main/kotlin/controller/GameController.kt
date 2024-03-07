package controller

import model.Answer
import model.Dealer
import model.Deck
import model.Hand
import model.Judge
import model.Name
import model.Player
import model.Players
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(Hand(deck))
        val players = readPlayers()

        initGame(dealer = dealer, players = players)
        playGame(dealer = dealer, players = players)

        showFinalResult(dealer = dealer, players = players)
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
        players.players.forEach {
            playOfOnePlayer(it)
            OutputView.getHand(it.hand)
        }
        dealer.hit()

        while (dealer.getPointIncludingAce().amount < 17) {
            OutputView.drawCardForDealer()
            dealer.hit()
        }
    }

    private fun readPlayers(): Players {
        return InputView.readPlayerNames().run {
            Players.from(this, deck)
        }
    }

    private fun readAnswer(name: Name): Answer {
        return InputView.readAnswer(name).run {
            Answer.fromInput(this)
        }
    }

    fun switchAnswer(
        answer: Answer,
        player: Player,
    ): Boolean {
        var flag = false
        if (answer == Answer.YES) {
            flag = player.hit()
        }
        OutputView.showPlayerHand(player)
        return flag
    }

    private fun playOfOnePlayer(player: Player) {
        while (switchAnswer(readAnswer(player.name), player));
    }

    fun showFinalResult(
        dealer: Dealer,
        players: Players,
    ) {
        OutputView.showDealerHandWithResult(dealer)
        OutputView.showPlayersHandWithResult(players)

        judge(players = players, dealer = dealer)
    }

    fun judge(
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
