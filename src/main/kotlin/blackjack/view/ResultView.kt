package blackjack.view

import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.Result
import blackjack.model.ScoreCalculator

object ResultView {
    private const val MESSAGE_PARTICIPANT_GAME_SCORE = "%s 카드: %s - 결과: %d"
    private const val MESSAGE_GAME_RESULT = "\n## 최종 승패"
    private const val MESSAGE_DEALER_RESULT = "%s: %d승 %d패 %d무"
    private const val MESSAGE_PLAYER_RESULT = "%s: %s"

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
        println(MESSAGE_GAME_RESULT)
        println(
            MESSAGE_DEALER_RESULT.format(
                gameResult.dealer.userInformation.name,
                gameResult.dealerResult[Result.DEALER_WIN],
                gameResult.dealerResult[Result.PLAYER_WIN],
                gameResult.dealerResult[Result.TIE],
            ),
        )
        gameResult.playerResults.withIndex().map { (index, playerResult) ->
            println(
                MESSAGE_PLAYER_RESULT.format(
                    gameResult.players[index].userInformation.name,
                    playerResult.label,
                ),
            )
        }
    }

    private fun outputGameScore(participant: Participant) {
        println(
            MESSAGE_PARTICIPANT_GAME_SCORE.format(
                participant.userInformation.name,
                participant.gameInformation.cards.joinToString(separator = ", ") { card ->
                    card.convertCard()
                },
                ScoreCalculator.calculateScore(participant.gameInformation.cards),
            ),
        )
    }
}
