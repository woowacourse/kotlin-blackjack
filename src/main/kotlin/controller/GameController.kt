package controller

import model.card.Deck
import model.participants.*
import view.InputView
import view.OutputView

class GameController(private val deck: Deck) {
    fun start() {
        val dealer = Dealer(ParticipantState.Playing(Hand()))
        val players = handleException { readPlayers() }

        val participants = Participants.of(dealer, players, deck)

        handOut(participants)
        handleException { playGame(participants) }

        showGameResult(participants)
    }

    private fun handOut(participants: Participants) {
        participants.handOut()
        OutputView.showGameInit(participants)
    }

    private fun playGame(participants: Participants) {
        participants.playPlayers(
            { player -> InputView.readHitDecision(player) },
            { player -> OutputView.showParticipantHand(player) }
        )
        participants.playDealer {
            dealer -> OutputView.showDealerHit(dealer)
        }
    }

    private fun readPlayers(): Players {
        return InputView.readPlayerNames().run {
            Players.ofList(this)
        }
    }

    private fun showGameResult(participants: Participants) {
        OutputView.showParticipantsHandWithResult(participants)
        judgeResult(participants)
    }

    private fun judgeResult(participants: Participants) {
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
