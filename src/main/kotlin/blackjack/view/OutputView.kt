package blackjack.view

import blackjack.model.card.Card
import blackjack.model.participant.GameResult

class OutputView {
    fun showFirstDraw(
        dealer: ParticipantUiModel,
        dealerFirstCard: Card = dealer.cards.first(),
        players: List<ParticipantUiModel>,
    ) {
        println()
        println(FORMAT_DIVIDED_CARDS.format(players.joinToString { it.name }))
        println(FORMAT_PLAYER_HAND_CARDS.format(dealer.name, dealerFirstCard.format()))
        players.forEach(::showPlayerHandCards)
    }

    fun showPlayerHandCards(player: ParticipantUiModel) {
        val cards: List<Card> = player.cards
        println(FORMAT_PLAYER_HAND_CARDS.format(player.name, cards.format()))
    }

    fun showDealerHitCard() {
        println(MESSAGE_DEALER_HIT)
    }

    fun showParticipantsScore(participants: List<ParticipantUiModel>) {
        println()
        participants.forEach(::showParticipantScore)
    }

    private fun showParticipantScore(participant: ParticipantUiModel) {
        val (name, cards, score) = participant
        println(
            FORMAT_PARTICIPANT_SCORE.format(
                name,
                cards.format(),
                score,
            ),
        )
    }

    fun showGameResult(
        dealerResult: DealerResultUiModel,
        playerResult: List<PlayerResultUiModel>,
    ) {
        println(MESSAGE_FINAL_RESULT)
        showDealerResult(dealerResult)
        playerResult.forEach(::showPlayerResult)
    }

    private fun showDealerResult(gameResult: DealerResultUiModel) {
        val (name, winCount, drawCount, loseCount) = gameResult
        println(FORMAT_PARTICIPANT_RESULT.format(name, dealerScoreFormat(winCount, drawCount, loseCount)))
    }

    private fun showPlayerResult(gameResult: PlayerResultUiModel) {
        val (name, result) = gameResult
        println(FORMAT_PARTICIPANT_RESULT.format(name, playerScoreFormat(result)))
    }

    private fun dealerScoreFormat(
        winCount: Int,
        drawCount: Int,
        loseCount: Int,
    ): String =
        buildString {
            if (winCount > CONDITION_COUNT) append(FORMAT_WIN.format(winCount))
            if (drawCount > CONDITION_COUNT) append(FORMAT_DRAW.format(drawCount))
            if (loseCount > CONDITION_COUNT) append(FORMAT_LOSE.format(loseCount))
        }

    private fun playerScoreFormat(result: GameResult) =
        when (result) {
            GameResult.WIN -> "승"
            GameResult.LOSE -> "패"
            GameResult.DRAW -> "무"
        }

    private fun Card.format(): String {
        return "${rank.label}${suit.label} "
    }

    private fun List<Card>.format(): String {
        return buildString {
            this@format.forEach {
                append(it.format())
            }
        }
    }

    companion object {
        private const val CONDITION_COUNT = 0
        private const val MESSAGE_WIN = "승"
        private const val MESSAGE_DRAW = "무"
        private const val MESSAGE_LOSE = "패"
        private const val MESSAGE_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_FINAL_RESULT = "\n## 최종 승패"
        private const val FORMAT_WIN = "%d$MESSAGE_WIN "
        private const val FORMAT_DRAW = "%d$MESSAGE_DRAW "
        private const val FORMAT_LOSE = "%d$MESSAGE_LOSE"
        private const val FORMAT_PLAYER_HAND_CARDS = "%s 카드: %s"
        private const val FORMAT_PARTICIPANT_SCORE = "%s 카드: %s- 결과 : %d"
        private const val FORMAT_PARTICIPANT_RESULT = "%s : %s"
        private const val FORMAT_DIVIDED_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다."
    }
}
