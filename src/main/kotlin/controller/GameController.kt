package controller

import model.card.Deck
import model.participants.Dealer
import model.participants.Hand
import model.participants.ParticipantName
import model.participants.ParticipantState
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

        handOut(participants)
        handleException { playGame(participants) }

        showGameResult(participants)
    }

    private fun handOut(participants: Participants) {
        participants.handOut(deck)
        OutputView.showGameInit(participants)
    }

    private fun playGame(participants: Participants) {
        participants.getPlayers().players.forEach {
            playOfOnePlayer(it)
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
    ): Boolean {
        if (hitDecision) player.hit(deck.pop())
        OutputView.showParticipantHand(player)
        return hitDecision
    }

    private fun playOfOnePlayer(player: Player) {
        while (player.participantState is ParticipantState.Playing &&
            playByDecision(readHitDecision(player.participantName), player)
            ) ;
    }

    private fun showGameResult(participants: Participants) {
        OutputView.showParticipantsHandWithResult(participants)
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
