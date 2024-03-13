package controller

import model.ParticipantState
import model.card.Deck
import model.card.DeckRandomGeneration
import model.participants.*
import view.InputView
import view.OutputView

class GameController() {
    fun start() {
        val deck = Deck.create(DeckRandomGeneration())

        val dealer = Dealer(ParticipantState.Playing(Hand()))
        val players = handleException { readPlayers() }

        val participants = Participants.of(dealer, players)

        initGame(dealer = dealer, players = players, deck)
        handleException { playGame(dealer = dealer, players = players, deck) }

        showGameResult(participants)
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
        participants: Participants
    ) {
        OutputView.showHumanHandWithResult(participants.getDealer())
        OutputView.showPlayersHandWithResult(participants.getPlayers())

        judge(participants)
    }

    private fun judge(
        participants: Participants
    ) {
        val playersResult = participants.getPlayersResult()
        val dealerResult = participants.getDealerResult()

        OutputView.showResultHeader()
        OutputView.showDealerResult(dealerResult)
        OutputView.showPlayersResult(participants.getPlayers(), playersResult)
    }

    private fun <T> handleException(block: () -> T): T =
        runCatching { block() }.getOrElse {
            OutputView.showThrowable(it)
            handleException(block)
        }
}
