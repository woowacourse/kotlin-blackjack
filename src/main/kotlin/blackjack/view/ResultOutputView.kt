package blackjack.view

import blackjack.model.game.GameResult
import blackjack.model.game.Score
import blackjack.model.user.Participant
import blackjack.model.user.Participant.Dealer
import blackjack.model.user.Participant.Player

object ResultOutputView {
    private const val MESSAGE_PARTICIPANT_GAME_SCORE = "%s 카드: %s - 결과: %d"
    private const val MESSAGE_TOTAL_REVENUE = "\n## 최종 수익"
    private const val MESSAGE_REVENUE = "%s: %s"

    fun outputGameScores(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputGameScore(dealer)
        players.forEach { player ->
            outputGameScore(player)
        }
    }

    fun outputGameResult(gameResult: GameResult) {
        println(MESSAGE_TOTAL_REVENUE)
        outputDealerRevenue(gameResult)
        outputPlayersRevenue(gameResult)
    }

    private fun outputGameScore(participant: Participant) {
        println(
            MESSAGE_PARTICIPANT_GAME_SCORE.format(
                participant.participantInformation.name,
                participant.hand.cards.joinToString(separator = ", "),
                Score(participant.hand.cards).point,
            ),
        )
    }

    private fun outputDealerRevenue(gameResult: GameResult) {
        println(
            MESSAGE_REVENUE.format(
                gameResult.dealer.participantInformation.name,
                gameResult.gameRevenue.calculateDealerRevenue(),
            ),
        )
    }

    private fun outputPlayersRevenue(gameResult: GameResult) {
        gameResult.gameRevenue.playersRevenue.withIndex().map { (index, playerRevenue) ->
            println(
                MESSAGE_REVENUE.format(
                    gameResult.players[index].participantInformation.name,
                    playerRevenue,
                ),
            )
        }
    }
}
