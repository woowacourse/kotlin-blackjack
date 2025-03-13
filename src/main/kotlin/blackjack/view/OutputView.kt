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
import blackjack.model.participant.Name
import blackjack.model.participant.Player.Companion.PLAYER_DEFAULT_MONEY

class OutputView {
    fun displayInitialMoney() {
        println("\n초기 플레이어의 잔액은 $PLAYER_DEFAULT_MONEY 입니다.")
    }

    fun displayFirstDrawEnd(players: List<Name>) {
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
        name: Name,
        cards: List<Card>,
    ) {
        println("$name 카드: ${cards.toText()}")
    }

    fun displayParticipantInfo(
        name: Name,
        cards: List<Card>,
        score: Int,
    ) {
        println("$name 카드: ${cards.toText()} - 결과: $score")
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

    fun displayProfitTitle() {
        println("\n## 최종 수익")
    }

    fun displayProfit(
        name: String,
        profit: Double,
    ) {
        println("$name: $profit")
    }
}
