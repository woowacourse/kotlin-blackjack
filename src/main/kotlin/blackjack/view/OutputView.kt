package blackjack.view

import blackjack.model.Card
import blackjack.model.CardRank
import blackjack.model.CardRank.ACE
import blackjack.model.CardRank.JACK
import blackjack.model.CardRank.KING
import blackjack.model.CardRank.QUEEN
import blackjack.model.CardSuit
import blackjack.model.CardSuit.CLUB
import blackjack.model.CardSuit.DIAMOND
import blackjack.model.CardSuit.HEART
import blackjack.model.CardSuit.SPADE
import blackjack.model.Player
import blackjack.model.WinningResult
import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN

class OutputView {
    fun displayFirstDrawEnd(players: List<Player>) {
        println("\n딜러와 ${players.joinToString(", ") { it.name }}에게 2장을 나누었습니다.")
    }

    fun displayDealerDrawInfo(count: Int) {
        val output =
            when {
                count == 0 -> "딜러는 16초과라 카드를 더 이상 뽑지 않았습니다."
                else -> "딜러는 16이하라 $count 장의 카드를 더 받았습니다."
            }
        println("\n" + output + "\n")
    }

    fun displayParticipantInfo(
        name: String = "딜러",
        cards: List<Card>,
        score: Int? = null,
    ) {
        val result = score?.let { " - 결과: $it" } ?: ""
        println("$name 카드: ${cards.toText()}$result")
    }

    private fun List<Card>.toText(): String =
        this.joinToString(", ") { card ->
            "${card.rank.toText()}${card.suit.toText()}"
        }

    private fun CardRank.toText(): String =
        when (this) {
            ACE -> "A"
            JACK -> "J"
            QUEEN -> "Q"
            KING -> "K"
            else -> this.score.toString()
        }

    private fun CardSuit.toText(): String =
        when (this) {
            SPADE -> "스페이드"
            HEART -> "하트"
            DIAMOND -> "다이아몬드"
            CLUB -> "클로버"
        }

    fun displayResultTitle() {
        println("\n## 최종 승패")
    }

    fun displayDealerResult(winningResult: Map<WinningResult, Int>) {
        println("딜러: ${winningResult[WIN]}승 ${winningResult[LOSE]}패 ${winningResult[PUSH]}무")
    }

    fun displayPlayerResult(
        name: String,
        winningResult: WinningResult,
    ) {
        println("$name: ${winningResult.toText()}")
    }

    private fun WinningResult.toText() =
        when (this) {
            WIN -> "승"
            LOSE -> "패"
            PUSH -> "무"
        }
}
