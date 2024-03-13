package controller

import model.ParticipantState
import model.card.Deck
import model.card.DeckRandomGeneration
import model.participants.Answer
import model.participants.Dealer
import model.participants.Hand
import model.participants.ParticipantName
import model.participants.Player
import model.participants.Players
import model.result.Judge
import view.InputView
import view.OutputView

class GameController() {
    fun start() {
        val deck = Deck.create(DeckRandomGeneration())

        val dealer = Dealer(ParticipantState.Playing(Hand()))
        val players = handleException { readPlayers() }

        initGame(dealer = dealer, players = players, deck)
        handleException { playGame(dealer = dealer, players = players, deck) }

        showGameResult(dealer = dealer, players = players)
    }

    private fun initGame(
        dealer: Dealer,
        players: Players,
        deck: Deck,
    ) {
        initDealer(dealer, deck)
        initPlayers(players, deck)
        OutputView.showGameInit(dealer, players)
    }

    private fun initDealer(
        dealer: Dealer,
        deck: Deck,
    ) {
        dealer.hit(deck.pop())
        dealer.hit(deck.pop())
    }

    private fun initPlayers(
        players: Players,
        deck: Deck,
    ) {
        players.players.forEach {
            it.hit(deck.pop())
            it.hit(deck.pop())
        }
    }

    private fun playGame(
        dealer: Dealer,
        players: Players,
        deck: Deck,
    ) {
        players.players.forEach {
            playOfOnePlayer(it, deck)
        }

        val hitCount = dealer.play(deck)
        repeat(hitCount) { OutputView.showDealerHit() }
    }

    private fun readPlayers(): Players {
        return InputView.readPlayerNames().run {
            Players.ofList(this)
        }
    }

    private fun readAnswer(participantName: ParticipantName): Answer {
        return InputView.readAnswer(participantName).run {
            Answer.fromInput(this)
        }
    }

    private fun playByAnswer(
        answer: Answer,
        player: Player,
        deck: Deck,
    ): Boolean {
        when (answer) {
            Answer.YES -> {
                player.hit(deck.pop())
            }
            Answer.NO -> {
            }
        }
        OutputView.showHumanHand(player)
        return answer == Answer.YES
    }

    private fun playOfOnePlayer(
        player: Player,
        deck: Deck,
    ) {
        while (player.participantState is ParticipantState.Playing && playByAnswer(readAnswer(player.participantName), player, deck)) ;
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
