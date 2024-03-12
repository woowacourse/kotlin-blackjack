package blackjack.view

import blackjack.model.deck.Card
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

    private fun getFirstCardString(dealer: Dealer) = "${dealer.getFirstCard().cardNumber}${dealer.getFirstCard().shape}"

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

    fun printGameResult(result: Map<String, CompetitionResult>) {
        println("\n## 최종 승패")

        val counts = result.values.groupingBy { it }.eachCount()
        val wins = counts.getOrDefault(CompetitionResult.WIN, DEFAULT_COMPETITION_RESULT)
        val losses = counts.getOrDefault(CompetitionResult.LOSE, DEFAULT_COMPETITION_RESULT)
        val draws = counts.getOrDefault(CompetitionResult.SAME, DEFAULT_COMPETITION_RESULT)

        println("딜러: ${losses}승 ${wins}패 ${draws}무")
        result.forEach { (playerName, competitionResult) ->
            println("$playerName: ${competitionResult.result}")
        }
    }

    private fun List<Card>.printCards() = joinToString(SPLIT_DELIMITER) { "${it.cardNumber.value}${it.shape.value}" }

    companion object {
        private const val SPLIT_DELIMITER = ", "
        private const val DEFAULT_COMPETITION_RESULT = 0
    }
}
