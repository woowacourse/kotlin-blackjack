package blackjack.controller

import blackjack.domain.Ace
import blackjack.domain.Card
import blackjack.domain.Character
import blackjack.domain.Dealer
import blackjack.domain.Number
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit

class Controller {
    fun readMoreCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
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
