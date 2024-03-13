package controller

import model.participants.ParticipantState
import model.card.Deck
import model.participants.Dealer
import model.participants.Hand
import model.participants.ParticipantName
import model.participants.Participants
import model.participants.Player
import model.participants.Players
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(ParticipantState.Playing(Hand()))
        val players = handleException { readPlayers() }

        val participants = Participants.of(dealer, players)

        initGame(participants)
        handleException { playGame(participants) }

        showGameResult(participants)
    }

    private fun initGame(participants: Participants) {
        initDealer(participants.getDealer())
        initPlayers(participants.getPlayers())

        OutputView.showGameInit(participants)
    }

    private fun initDealer(dealer: Dealer) {
        dealer.hit(deck.pop())
        dealer.hit(deck.pop())
    }

    private fun initPlayers(players: Players) {
        players.players.forEach {
            it.hit(deck.pop())
            it.hit(deck.pop())
        }
    }

    private fun playGame(participants: Participants) {
        participants.getPlayers().players.forEach {
            playOfOnePlayer(it, deck)
        }

        val hitCount = participants.getDealer().play(deck)
        repeat(hitCount) { OutputView.showDealerHit() }
    }

    private fun readPlayers(): Players {
        return InputView.readPlayerNames().run {
            Players.ofList(this)
        }
    }

    private fun readHitDecision(participantName: ParticipantName): Boolean {
        return InputView.readHitDecision(participantName)
    }

    private fun playByDecision(
        hitDecision: Boolean,
        player: Player,
        deck: Deck,
    ): Boolean {
        if (hitDecision) player.hit(deck.pop())
        OutputView.showHumanHand(player)
        return hitDecision
    }

    private fun playOfOnePlayer(
        player: Player,
        deck: Deck,
    ) {
        while (player.participantState is ParticipantState.Playing &&
            playByDecision(readHitDecision(player.participantName), player, deck)
            ) ;
    }

    private fun showGameResult(participants: Participants) {
        OutputView.showHumanHandWithResult(participants.getDealer())
        OutputView.showPlayersHandWithResult(participants.getPlayers())

        judge(participants)
    }

    private fun judge(participants: Participants) {
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
