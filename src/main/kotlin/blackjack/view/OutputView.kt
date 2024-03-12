package blackjack.view

import blackjack.model.GameResult
import blackjack.model.Participant
import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player
import blackjack.model.Participants
import blackjack.model.Result

object OutputView {
    private const val MESSAGE_CARD_DISTRIBUTION = "\n%s와 %s에게 2장의 카드를 나누었습니다."
    private const val MESSAGE_DEALER_CARD_INFORMATION = "%s: %s"
    private const val MESSAGE_PARTICIPANT_CARD_INFORMATION = "%s 카드: %s"
    private const val MESSAGE_DEALER_DRAW = "%s는 16이하라 한장의 카드를 더 받았습니다.\n"
    private const val MESSAGE_PARTICIPANT_GAME_SCORE = "%s 카드: %s - 결과: %d"
    private const val MESSAGE_GAME_RESULT = "\n## 최종 승패"
    private const val MESSAGE_DEALER_RESULT = "%s: %d승 %d패 %d무"
    private const val MESSAGE_PLAYER_RESULT = "%s: %s"
    private const val COMMA = ", "

    fun outputCardDistribution(participants: Participants) {
        val dealerName = participants.getDealer().name
        val playerNames = participants.getPlayers().map { player -> player.name }.joinToString(COMMA)
        println(MESSAGE_CARD_DISTRIBUTION.format(dealerName, playerNames))
        outputInitialDealerCard(participants.getDealer())
        outputPlayersCards(participants.getPlayers())
    }

    fun outputParticipantCard(participant: Participant) {
        println(
            MESSAGE_PARTICIPANT_CARD_INFORMATION.format(
                participant.name,
                participant.gameInformation.cards.joinToString(separator = ", ") { card ->
                    card.convertCard()
                },
            ),
        )
    }

    fun outputDealerDraw(dealer: Dealer) {
        println(MESSAGE_DEALER_DRAW.format(dealer.name))
    }

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
                    playerResult.convertResult(),
                ),
            )
        }
    }

    private fun outputInitialDealerCard(dealer: Dealer) {
        println(
            MESSAGE_DEALER_CARD_INFORMATION.format(
                dealer.name,
                dealer.gameInformation.cards.elementAt(0),
            ),
        )
    }

    private fun outputPlayersCards(players: List<Player>) {
        players.forEach { player ->
            outputParticipantCard(player)
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
