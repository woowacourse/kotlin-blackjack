package blackjack.view

import blackjack.domain.Ace
import blackjack.domain.Card
import blackjack.domain.Character
import blackjack.domain.Dealer
import blackjack.domain.Number
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit

class OutputView {
    fun printInitialCardsState(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("${players.joinToString { player -> player.name }}에게 2장씩 나누었습니다.")
        println("딜러가 한 장을 오픈했습니다.")
        println("딜러: ${dealer.cards.joinToString { card -> card.prettyString }}")
        players.forEach { player ->
            println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}")
        }
        println()
    }

    val Card.prettyString: String
        get() = rank.prettyString + suit.prettyString

    val Rank.prettyString: String
        get() =
            when (this) {
                is Ace -> "A"
                Character.JACK -> "J"
                Character.QUEEN -> "Q"
                Character.KING -> "K"
                is Number -> value.toString()
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
