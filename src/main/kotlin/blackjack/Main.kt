package blackjack

import blackjack.domain.Blackjack
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.RandomShuffler
import blackjack.domain.Rank
import blackjack.domain.Suit

fun main() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val players: List<Player> = readln().split(",").map { name: String -> Player(name.trim()) }
    println()
    val dealer = Dealer(players, RandomShuffler)
    val blackjack = Blackjack(dealer, players)
    println("${players.joinToString { player -> player.name }}에게 2장씩 나누었습니다.")
    println("딜러가 한 장을 오픈했습니다.")
    blackjack.start()
    println("딜러: ${dealer.cards.joinToString { card -> card.prettyString }}")
    players.forEach { player ->
        println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}")
    }
    println()
    players.forEach { player ->
        println("${player.name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        var input = readln()
        while (input == "y") {
            dealer.giveCard(player)
            println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}")
            println("${player.name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            input = readln()
        }
        println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}")
    }
    println()
    dealer.hitOrStay()
    println()
    println("딜러 카드: ${dealer.cards.joinToString { card -> card.prettyString }} - 결과: ${dealer.getScore()}")
    players.forEach { player ->
        println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }} - 결과: ${dealer.getScore()}")
    }
    blackjack.finish()
    println()
    println("## 최종 승패")
    println("딜러: ${dealer.playerStates.joinToString()}")
    players.forEach { player ->
        println("${player.name}: ${player.playerState}")
    }
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
