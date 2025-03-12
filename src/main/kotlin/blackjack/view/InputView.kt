package blackjack.view

import blackjack.domain.Ace
import blackjack.domain.Card
import blackjack.domain.Character
import blackjack.domain.Number
import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Suit

class InputView {
    fun readPlayers(): List<Player> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players: List<Player> = readln().split(",").map { name: String -> Player(name.trim()) }
        return players
    }

    fun askMoreCards(): Boolean {
        players.forEach { player ->
            while (player.canGetCard()) {
                println("\n${player.name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                var input = readln()
                if (input == "n") {
                    break
                } else if (input == "y") {
                    dealer.giveCard(player)
                    println("${player.name}카드: ${player.cards.joinToString { card -> card.prettyString }}")
                } else {
                    println("y 또는 n을 입력하세요")
                }
            }
        }
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
