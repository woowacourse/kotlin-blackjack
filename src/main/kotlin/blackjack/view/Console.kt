package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantProfit
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.ParticipantScore
import blackjack.domain.data.PlayerResult
import blackjack.domain.result.GameResult

class Console : InputView, OutputView {
    override fun inputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    override tailrec fun inputBettingMoney(name: String): Int {
        println("\n${name}의 베팅 금액은?")
        val money = readln().toIntOrNull()
        if (money == null || money < 500) {
            println("베팅 금액은 500원 이상이어야 합니다. 베팅 금액은 '$money' 일 수 없습니다.")
            return inputBettingMoney(name)
        }
        return money
    }

    override fun inputDrawCommand(name: String): Boolean = runCatching {
        println("${name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val command = readln().trim().lowercase()
        require(command in listOf("y", "n")) { "입력값은 y 또는 n 이어야 합니다. (현재 입력값 : $command)" }
        return@runCatching command == "y"
    }.getOrElse {
        println(it.message)
        inputDrawCommand(name)
    }

    override fun printFirstOpenCards(participantsCards: List<ParticipantCards>) {
        println(
            "\n${participantsCards.first().name}와 ${
            participantsCards.drop(1).joinToString(", ") { it.name }
            }에게 2장의 카드를 나누었습니다."
        )
        participantsCards.forEach { (name, cards) ->
            printCards(name, cards)
        }
        println()
    }

    override fun printCards(name: String, cards: List<Card>) {
        println("$name 카드: ${cards.joinToString(", ") { it.toText() }}")
    }

    override fun printDealerHit(name: String) {
        println("\n${name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    override fun printResult(
        cards: List<ParticipantCards>,
        totalScores: List<ParticipantScore>,
        results: ParticipantResults
    ) {
        println()
        printScores(cards, totalScores)
        printResults(results.dealerResult, results.playerResults)
        printProfits(results.profits)
    }

    private fun printScores(participantsCards: List<ParticipantCards>, scores: List<ParticipantScore>) {
        println(
            scores
                .mapIndexed { index, (name, score) -> getScoreMessage(name, participantsCards[index].cards, score) }
                .joinToString("\n")
        )
    }

    private fun getScoreMessage(name: String, cards: List<Card>, score: Int): String =
        "$name 카드: ${cards.joinToString(", ") { it.toText() }} - 결과: $score"

    private fun printResults(dealerResult: DealerResult, playerResults: List<PlayerResult>) {
        println(
            """
            |${"\n"}## 최종 승패
            |${dealerResult.name}: ${dealerResult.win}승 ${dealerResult.draw}무 ${dealerResult.lose}패
            |${playerResults.joinToString("\n") { getPlayerResultMessage(it.name, it.result) }}
            """.trimMargin()
        )
    }

    private fun getPlayerResultMessage(name: String, result: GameResult): String = when (result) {
        GameResult.BLACKJACK -> "$name: 블랙잭"
        GameResult.WIN -> "$name: 승"
        GameResult.DRAW -> "$name: 무"
        GameResult.LOSE -> "$name: 패"
    }

    private fun Card.toText(): String = "${number.toText()}${suit.toText()}"

    private fun CardNumber.toText(): String = when (this) {
        CardNumber.ACE -> "A"
        CardNumber.TWO -> "2"
        CardNumber.THREE -> "3"
        CardNumber.FOUR -> "4"
        CardNumber.FIVE -> "5"
        CardNumber.SIX -> "6"
        CardNumber.SEVEN -> "7"
        CardNumber.EIGHT -> "8"
        CardNumber.NINE -> "9"
        CardNumber.TEN -> "10"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
    }

    private fun Suit.toText(): String = when (this) {
        Suit.SPADE -> "스페이드"
        Suit.HEART -> "하트"
        Suit.DIAMOND -> "다이아몬드"
        Suit.CLOVER -> "클로버"
    }

    private fun printProfits(profits: List<ParticipantProfit>) {
        println(
            """
            |${"\n"}## 최종 수익
            |${profits.joinToString("\n") { (name, profit) -> "$name: $profit" }}
            """.trimMargin()
        )
    }
}
