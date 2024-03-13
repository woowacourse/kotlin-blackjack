package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameManager
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.user.UserDecision
import kotlin.system.exitProcess

class BlackJackController {
    private lateinit var gameManager: GameManager

    fun startGameFlow() {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        val participants =
            makeParticipants(dealer, players) ?: exitProcess(1)
        gameManager =
            GameManager(
                participants = participants,
            )
        gameManager.setGame()
        OutputView.outputParticipantsName(
            dealerName = dealer.getName(),
            players = participants.getPlayers(),
        )
        dealer.openFirstCard()?.let { firstCard ->
            OutputView.outputDealerCurrentHandCard(
                name = dealer.getName(),
                firstCard = firstCard,
            )
        } ?: println(ERROR_CARD_INDEX)
        OutputView.outputPlayersCurrentHandCard(participants.getPlayers())
    }

    fun playGame() {
        gameManager.getAlivePlayers().forEach { participant ->
            playPlayer(participant)
        }
        playDealer()
        gameManager.judgeBlackJackScores()
    }

    private fun playPlayer(participant: Participant) {
        while (participant.checkShouldDrawCard()) {
            processPlayerDecision(participant)
        }
    }

    private fun processPlayerDecision(participant: Participant) {
        when (InputView.inputPlayerDecision(participant.getName())) {
            UserDecision.YES -> {
                if (!setUserDecision(participant)) return
                OutputView.outputPlayerCurrentHandCard(participant)
            }

            UserDecision.NO -> participant.transitionToStayState()
        }
    }

    private fun playDealer() {
        val dealer = gameManager.getDealer()
        while (dealer.checkShouldDrawCard()) {
            OutputView.outputDealerRule()
            if (!setUserDecision(dealer)) return
        }
    }

    fun showResult() {
        OutputView.outputParticipantsHandCard(gameManager.getParticipants())
        OutputView.outputBlackResult()
        OutputView.outputDealerResult(
            dealerName = gameManager.getDealer().getName(),
            dealerResults = gameManager.getDealerResults(),
        )
        OutputView.outputPlayersResult(
            playersResult = gameManager.getPlayerResults(),
        )
    }

    private fun setUserDecision(participant: Participant): Boolean {
        return try {
            gameManager.drawCardForParticipant(participant)
            true
        } catch (e: IllegalArgumentException) {
            println(e.message)
            false
        }
    }

    private fun makeParticipants(
        dealer: Dealer,
        players: List<Player>,
    ): Participants? {
        return try {
            Participants(
                participants = listOf(dealer) + players,
            )
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    companion object {
        const val ERROR_CARD_INDEX = "딜러가 가지고 있는 카드가 없습니다."
    }
}
