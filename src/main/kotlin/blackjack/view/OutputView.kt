package blackjack.view

import blackjack.model.deck.Card
import blackjack.model.deck.CardNumber
import blackjack.model.deck.Shape
import blackjack.model.participant.CompetitionResult
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players

class OutputView {
    fun printInitCard(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n딜러와 ${players.gamePlayers.joinToString(SPLIT_DELIMITER) { it.name }}에게 플레이어에게 2장의 카드를 나누었습니다.")
        println("딜러: ${getFirstCardString(dealer)}")
        players.gamePlayers.forEach { player ->
            println("${player.name}카드: ${player.getAllCards().printCards()}")
        }
    }

    private fun getFirstCardString(dealer: Dealer) = "${dealer.getFirstCard().cardNumber.toPrint()}${dealer.getFirstCard().shape.toPrint()}"

    fun printPlayerCard(player: Player) {
        println("${player.name} 카드: ${player.getAllCards().printCards()}")
    }

    fun printBustMessage() {
        println("Bust! 더이상 카드를 받을 수 없습니다.")
    }

    fun printDealerAddCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printCardResult(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n딜러 카드: ${dealer.getAllCards().printCards()} - 결과: ${dealer.getScore()}")
        players.gamePlayers.forEach { player ->
            println("${player.name}카드: ${player.getAllCards().printCards()} - 결과: ${player.getScore()}")
        }
    }

    fun printGameResult(result: Map<String, Double>) {
        println("\n## 최종 수익")

        println("딜러: ${result.values.sum().unaryMinus()}")

        result.forEach { (playerName, profit) ->
            println("$playerName: $profit")
        }
    }

    private fun List<Card>.printCards() = joinToString(SPLIT_DELIMITER) { "${it.cardNumber.toPrint()}${it.shape.toPrint()}" }

    private fun CardNumber.toPrint() =
        when (this) {
            CardNumber.TWO -> "2"
            CardNumber.THIRD -> "3"
            CardNumber.FOURTH -> "4"
            CardNumber.FIFTH -> "5"
            CardNumber.SIXTH -> "6"
            CardNumber.SEVENTH -> "7"
            CardNumber.EIGHTH -> "8"
            CardNumber.NINTH -> "9"
            CardNumber.TEN -> "10"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            CardNumber.KING -> "K"
            CardNumber.ACE -> "A"
        }

    private fun Shape.toPrint() =
        when (this) {
            Shape.SPADE -> "스페이드"
            Shape.HEART -> "하트"
            Shape.DIAMOND -> "다이아몬드"
            Shape.CLOVER -> "클로버"
        }

    private fun CompetitionResult.toPrint() =
        when (this) {
            CompetitionResult.WIN -> "승"
            CompetitionResult.LOSE -> "패"
            CompetitionResult.SAME -> "무"
            CompetitionResult.BLACKJACK -> "블랙잭"
        }

    companion object {
        private const val SPLIT_DELIMITER = ", "
        private const val DEFAULT_COMPETITION_RESULT = 0
    }
}
