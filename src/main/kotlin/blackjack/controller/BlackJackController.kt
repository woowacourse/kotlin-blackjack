package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameManager
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.user.UserDecision

class BlackJackController {
    private lateinit var gameManager: GameManager

    fun runBlackJackGame() {
        startGameFlow()
        playGame()
        showResult()
    }

    private fun startGameFlow() {
        val participants = makeParticipants()
        gameManager =
            GameManager(
                participants = participants,
            )
        gameManager.setGame()
        OutputView.outputParticipantsName(
            dealerName = participants.dealer.getName(),
            players = participants.players,
        )
        participants.dealer.openInitCards()?.firstOrNull()?.let { firstCard ->
            OutputView.outputDealerCurrentHandCard(
                name = participants.dealer.getName(),
                firstCard = firstCard,
            )
        } ?: println(ERROR_CARD_INDEX)
        OutputView.outputPlayersCurrentHandCard(participants.players)
    }

    private fun playGame() {
        gameManager.getAlivePlayers().forEach { participant ->
            playPlayer(participant)
        }
        playDealer()
    }

    private fun playPlayer(participant: Participant) {
        while (participant.checkShouldDrawCard()) {
            processPlayerDecision(participant)
        }
    }

    private fun processPlayerDecision(participant: Participant) {
        when (InputView.inputPlayerDecision(participant.getName())) {
            UserDecision.YES -> {
                if (!processUserDecision(participant)) return
                OutputView.outputPlayerCurrentHandCard(participant)
            }

            UserDecision.NO -> participant.transitionToStayState()
        }
    }

    private fun playDealer() {
        val dealer = gameManager.getDealer()
        while (dealer.checkShouldDrawCard()) {
            OutputView.outputDealerRule()
            if (!processUserDecision(dealer)) return
        }
    }

    private fun showResult() {
        OutputView.outputParticipantsHandCard(gameManager.getParticipants())
        OutputView.outputBlackResult()
        val gameResult = gameManager.calculateGameResult()
        OutputView.outputParticipantResult(
            dealerName = gameManager.getDealer().getName(),
            gameResult = gameResult,
        )
    }

    private fun processUserDecision(participant: Participant): Boolean {
        return try {
            participant.draw(gameManager.returnCardForParticipant())
            true
        } catch (e: IllegalArgumentException) {
            println(e.message)
            false
        }
    }

    private fun makeParticipants(): Participants {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        return checkParticipants(dealer, players) ?: makeParticipants()
    }

    private fun checkParticipants(
        dealer: Dealer,
        players: List<Player>,
    ): Participants? {
        return try {
            Participants(
                dealer = dealer,
                players = players,
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
