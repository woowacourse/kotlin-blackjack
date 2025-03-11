package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit

class OutputView {
    fun requestPlayers() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun showCardDealing(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println()
        println("${players.joinToString { player -> player.name }}에게 2장씩 나누었습니다.")
        println("딜러가 한 장을 오픈했습니다.")
        println("딜러: ${dealer.cards.joinToString { card -> card.prettyString }}")
        players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}")
        }
    }

    fun askWantToHit(player: Player) {
        println("${player.name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun showPlayersCard(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}, 점수 : ${player.score}")
    }

    fun endDealerTurn(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("\n딜러 카드: ${dealer.cards.joinToString { card -> card.prettyString }} - 결과: ${dealer.handState.score}")
        players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }} - 결과: ${player.handState.score}")
        }
        println()
    }

    fun showResult(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("## 최종 승패")
        println("딜러: ${dealer.dealerResults.joinToString()}")
        players.forEach { player ->
            println("${player.name}: ${player.state}")
        }
    }

    fun showDealerHit() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    val Card.prettyString: String
        get() = rank.prettyString + suit.prettyString

    val Rank.prettyString: String
        get() =
            when (this) {
                Rank.AceRank -> "A"
                is Rank.FaceRank -> value.toString()
                is Rank.NumberRank -> value.toString()
            }

    val Suit.prettyString: String
        get() =
            when (this) {
                Suit.SPADE -> "스페이드"
                Suit.HEART -> "하트"
                Suit.DIAMOND -> "다이아몬드"
                Suit.CLOVER -> "클로버"
            }
}
