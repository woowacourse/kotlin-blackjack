package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameManager
import blackjack.model.GameResult
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.user.UserDecision
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    private val gameManager = GameManager()
    private lateinit var participants: Participants
    private lateinit var gameResult: GameResult

    fun startGameFlow() {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        participants =
            Participants(
                participants = listOf(dealer) + players,
            )
        gameManager.setGame(participants)
        OutputView.outputParticipantsName(
            dealerName = dealer.getName(),
            players = participants.getPlayers(),
        )
        OutputView.outputDealerCurrentHandCard(
            name = dealer.getName(),
            firstCard = dealer.openFirstCard(),
        )
        OutputView.outputPlayersCurrentHandCard(participants.getPlayers())
    }

    fun playGame() {
        participants.getAlivePlayers().forEach { player ->
            playPlayer(player as Player)
        }
        playDealer()
    }

    private fun playPlayer(player: Player) {
        while (player.checkHitState() && InputView.inputPlayerDecision(player.getName()) == UserDecision.YES) {
            gameManager.applyUserDrawDecision(player)
            OutputView.outputPlayerCurrentHandCard(player)
        }
    }

    private fun playDealer() {
        val dealer = participants.getDealer()
        while (dealer.checkDealerScoreCondition()) {
            OutputView.outputDealerRule()
            gameManager.applyUserDrawDecision(dealer)
        }
    }

    fun calculateResult() {
        gameResult =
            GameResult(
                dealer = participants.getDealer(),
                players = participants.getPlayers(),
            )
        gameResult.judgeBlackJackScores()
    }

    fun showResult() {
        OutputView.outputParticipantsHandCard(participants.getParticipants())
        OutputView.outputBlackResult()
        OutputView.outputDealerResult(
            dealerName = participants.getDealer().getName(),
            dealerResults = gameResult.getDealerResults(),
        )
        OutputView.outputPlayersResult(
            playersResult = gameResult.getPlayerResults(),
        )
    }
}
