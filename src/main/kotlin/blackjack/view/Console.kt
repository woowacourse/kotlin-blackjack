package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantProfit
import blackjack.domain.data.ParticipantScore
import blackjack.domain.result.GameResult
import blackjack.domain.result.PlayerResults

class Console : InputView, OutputView {
    override fun inputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(",").map { it.trim() }
        println()
        return names
    }

    override fun inputBettingMoney(name: String): Int {
        println("${name}의 베팅 금액은?")
        return checkBettingMoney(readln())
            .onSuccess { println() }
            .onFailure { println(it.message) }
            .getOrElse { inputBettingMoney(name) }
    }

    private fun checkBettingMoney(input: String): Result<Int> {
        return runCatching {
            val money = input.toIntOrNull()
            requireNotNull(money) { "베팅 금액은 숫자여야 합니다. 베팅 금액은 '$input' 일 수 없습니다." }
            require(money >= 500) { "베팅 금액은 500원 이상이어야 합니다. 베팅 금액은 '$input' 일 수 없습니다." }
            money
        }
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
            "${participantsCards.first().name}와 ${
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

    override fun printDealerHit() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    override fun printResult(
        cards: List<ParticipantCards>,
        totalScores: List<ParticipantScore>,
        results: PlayerResults,
        calculateProfits: List<ParticipantProfit>
    ) {
        println()
        printScores(cards, totalScores)
        printResults(results)
        printProfits(calculateProfits)
    }

    private fun printScores(participantsCards: List<ParticipantCards>, scores: List<ParticipantScore>) {
        scores.forEachIndexed { index, (name, score) ->
            printScore(name, participantsCards[index].cards, score)
        }
        println()
    }

    private fun printScore(name: String, cards: List<Card>, score: Int) {
        println("$name 카드: ${cards.joinToString(", ") { it.toText() }} - 결과: $score")
    }

    private fun printResults(results: PlayerResults) {
        println("## 최종 승패")
        printDealerResult(results.getDealerResult())
        results.get().forEach { playerResult -> printPlayerResult(playerResult.name, playerResult.result) }
        println()
    }

    private fun printDealerResult(result: DealerResult) {
        println("딜러: ${result.win}승 ${result.draw}무 ${result.lose}패")
    }

    private fun printPlayerResult(name: String, result: GameResult) {
        when (result) {
            GameResult.BLACKJACK -> println("$name: 블랙잭")
            GameResult.WIN -> println("$name: 승")
            GameResult.DRAW -> println("$name: 무")
            GameResult.LOSE -> println("$name: 패")
        }
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
        println("## 최종 수익")
        profits.forEach { (name, profit) ->
            println("$name: $profit")
        }
    }
}
