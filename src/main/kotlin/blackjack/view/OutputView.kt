package blackjack.view

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.card.CardRank.ACE
import blackjack.model.card.CardRank.JACK
import blackjack.model.card.CardRank.KING
import blackjack.model.card.CardRank.QUEEN
import blackjack.model.card.CardSuit
import blackjack.model.card.CardSuit.CLUB
import blackjack.model.card.CardSuit.DIAMOND
import blackjack.model.card.CardSuit.HEART
import blackjack.model.card.CardSuit.SPADE
import blackjack.model.game.GameResult
import blackjack.model.rule.WinningResult
import blackjack.model.rule.WinningResult.LOSE
import blackjack.model.rule.WinningResult.PUSH
import blackjack.model.rule.WinningResult.WIN

class OutputView {
    fun displayFirstDrawEnd(players: List<String>) {
        println("\n딜러와 ${players.joinToString()}에게 2장을 나누었습니다.")
    }

    fun displayDealerDrawInfo(count: Int) {
        val output =
            when {
                count == 0 -> "딜러는 16초과라 카드를 더 이상 뽑지 않았습니다."
                else -> "딜러는 16이하라 $count 장의 카드를 더 받았습니다."
            }
        println("\n" + output + "\n")
    }

    fun displayParticipantCards(
        name: String,
        cards: List<Card>,
    ) {
        println("$name 카드: ${cards.toText()}")
    }

    fun displayParticipantInfo(
        name: String,
        cards: List<Card>,
        score: Int,
        isBust: Boolean,
    ) {
        val result = if (isBust) "버스트" else score
        println("$name 카드: ${cards.toText()} - 결과: $result")
    }

    private fun List<Card>.toText(): String =
        this.joinToString { card ->
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

    fun displayResult(result: GameResult) {
        println("\n## 최종 승패")
        displayDealerResult(result.dealerResult)
        displayPlayersResult(result.playerResults)
    }

    private fun displayDealerResult(result: Map<WinningResult, Int>) {
        println("딜러: ${result[WIN]}승 ${result[PUSH]}무 ${result[LOSE]}패")
    }

    private fun displayPlayersResult(result: Map<String, WinningResult>) {
        result.forEach { (name, winningResult) ->
            println("$name: ${winningResult.toText()}")
        }
    }

    private fun WinningResult.toText() =
        when (this) {
            WIN -> "승"
            LOSE -> "패"
            PUSH -> "무"
        }
}
