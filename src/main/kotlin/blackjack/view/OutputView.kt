package blackjack.view

import blackjack.model.Card
import blackjack.model.DealerResult
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.ScoreBoard

class OutputView {
    fun showDivided(
        dealerFirstCard: Card,
        players: List<Player>,
    ) {
        println()
        println(MESSAGE_DIVIDED_CARDS.format(players.joinToString { it.name }))
        println(MESSAGE_PLAYER_HAND_CARDS.format(DEALER_NAME, dealerFirstCard.format()))
        players.forEach(::showPlayerHandCards)
    }

    fun showPlayerHandCards(player: Player) {
        val cards: List<Card> = player.hand.cards
        println(MESSAGE_PLAYER_HAND_CARDS.format(player.name, cards.format()))
    }

    fun showDealerHitCard() {
        println(MESSAGE_DEALER_HIT)
    }

    fun showDealerScore(
        cards: List<Card>,
        score: Int,
    ) {
        println()
        showPlayerScore(DEALER_NAME, cards, score)
    }

    fun showPlayerScore(
        name: String,
        cards: List<Card>,
        score: Int,
    ) {
        println(
            MESSAGE_PARTICIPANT_RESULT.format(
                name,
                cards.format(),
                score,
            ),
        )
    }

    fun showScoreBoard(scoreBoard: ScoreBoard) {
        println(MESSAGE_FINAL_RESULT)
        val (playersResult, dealerResult) = scoreBoard
        println(dealerResult.format())
        playersResult.forEach {
            println("${it.name}: ${it.state.label}")
        }
    }

    private fun DealerResult.format() =
        buildString {
            append("${DEALER_NAME}: ")
            GameResult.State.entries.forEach {
                append(map[it] ?: INIT_SCORE)
                append(it.label)
                append(" ")
            }
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
        private const val INIT_SCORE = 0
        private const val DEALER_NAME = "딜러"
        private const val MESSAGE_PLAYER_HAND_CARDS = "%s 카드: %s"
        private const val MESSAGE_PARTICIPANT_RESULT = "%s 카드: %s- 결과 : %d"
        private const val MESSAGE_DIVIDED_CARDS = "딜러와 %s에게 2장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HIT = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_FINAL_RESULT = "\n## 최종 승패"
    }
}
