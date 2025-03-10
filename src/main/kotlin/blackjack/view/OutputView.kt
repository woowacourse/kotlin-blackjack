package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.GameResultStatus
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.Rank
import blackjack.domain.Suit
import java.lang.StringBuilder

object OutputView {
    fun showInitialCards(game: BlackJackGame) {
        println("딜러와 ${game.players.joinToString { it.name }}에게 2장을 나누었습니다.\n")
        println("딜러: ${game.dealer.cards.first()}")
        game.players.forEach { player ->
            printPlayerCards(player)
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards.format()}")
    }

    fun printDealerHaveAdditionalCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printFinalCards(game: BlackJackGame) {
        println("딜러 카드: ${game.dealer.cards.format()} - 결과: ${game.dealer.totalSum}")

        game.players.forEach { player ->
            println("${player.name}카드: ${player.cards.format()} - 결과: ${player.totalSum}")
        }
    }

    fun printGameResult(playerResults: List<PlayerResult>) {
        println("\n##최종 승패")
        val dealerLose = playerResults.count { it.status == GameResultStatus.PLAYER_WIN }
        val dealerWin = playerResults.count { it.status == GameResultStatus.PLAYER_LOSE }
        val draw = playerResults.count { it.status == GameResultStatus.DRAW }

        println("딜러: ${dealerWin}승 ${dealerLose}패 ${draw}무")

        playerResults.forEach {
            println("${it.player.name}: ${it.status.toDisplayName()}")
        }
    }
}

private fun List<Card>.format(): String {
    val cardStr = StringBuilder()
    this.forEach { card ->
        cardStr.append(card.rank.toDisplayName())
        cardStr.append(", ")
        cardStr.append(card.suit.toDisplayName())
    }
    return cardStr.toString()
}

private fun GameResultStatus.toDisplayName(): String {
    return when (this) {
        GameResultStatus.PLAYER_WIN -> "승"
        GameResultStatus.PLAYER_LOSE -> "패"
        GameResultStatus.DRAW -> "무"
    }
}

private fun Rank.toDisplayName(): String {
    return when (this) {
        Rank.ACE -> "A"
        Rank.TWO -> "2"
        Rank.THREE -> "3"
        Rank.FOUR -> "4"
        Rank.FIVE -> "5"
        Rank.SIX -> "6"
        Rank.SEVEN -> "7"
        Rank.EIGHT -> "8"
        Rank.NINE -> "9"
        Rank.TEN -> "10"
        Rank.JACK -> "J"
        Rank.QUEEN -> "Q"
        Rank.KING -> "K"
    }
}

private fun Suit.toDisplayName(): String {
    return when (this) {
        Suit.SPADE -> "스페이드"
        Suit.HEART -> "하트"
        Suit.DIAMOND -> "다이아몬드"
        Suit.CLUB -> "클로버"
    }
}
