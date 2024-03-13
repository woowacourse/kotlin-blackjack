package blackjack.view

import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Participants
import blackjack.model.Result

object ResultView {
    private const val MESSAGE_PARTICIPANT_GAME_SCORE = "%s 카드: %s - 결과: %d"
    private const val MESSAGE_GAME_RESULT = "\n## 최종 승패"
    private const val MESSAGE_DEALER_RESULT = "%s: %d승 %d패 %d무"
    private const val MESSAGE_PLAYER_RESULT = "%s: %s"

    fun outputGameScores(participants: Participants) {
        outputGameScore(participants.getDealer())
        participants.getPlayers().forEach { player ->
            outputGameScore(player)
        }
    }

    fun outputGameResult(gameResult: GameResult) {
        println(MESSAGE_GAME_RESULT)
        println(
            MESSAGE_DEALER_RESULT.format(
                gameResult.participants.getDealer().name,
                gameResult.dealerResult[Result.DEALER_WIN],
                gameResult.dealerResult[Result.PLAYER_WIN],
                gameResult.dealerResult[Result.TIE],
            ),
        )
        gameResult.playerResults.withIndex().map { (index, playerResult) ->
            println(
                MESSAGE_PLAYER_RESULT.format(
                    gameResult.participants.getPlayers()[index].name,
                    playerResult.label,
                ),
            )
        }
    }

    private fun outputGameScore(participant: Participant) {
        println(
            MESSAGE_PARTICIPANT_GAME_SCORE.format(
                participant.name,
                participant.gameInformation.cards.joinToString(separator = ", ") { card ->
                    card.convertCard()
                },
                participant.gameInformation.score,
            ),
        )
    }
}
