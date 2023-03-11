package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Suit
import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantCards
import blackjack.domain.data.ParticipantResult
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.PlayerResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.result.GameResult
import blackjack.model.CardNumberModel
import blackjack.model.SuitModel

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

    override fun printFirstDraw(participantsCards: List<ParticipantCards>) {
        println(
            """
            |
            |${participantsCards.first().name}와 ${
            participantsCards.drop(1).joinToString(", ") { it.name }
            }에게 2장의 카드를 나누었습니다.
            |${participantsCards.joinToString("\n") { (name, cards) -> getCardsMessage(name, cards) }}
            |
            """.trimMargin()
        )
    }

    override fun printDraw(participant: Participant) {
        when (participant) {
            is Dealer -> println("\n${participant.name}는 16이하라 한장의 카드를 더 받았습니다.")
            is Player -> println(getCardsMessage(participant.info.name, participant.getCards()))
        }
    }

    private fun getCardsMessage(name: String, cards: List<Card>): String =
        "$name 카드: ${cards.joinToString(", ") { it.toPresentation() }}"

    override fun printResult(results: ParticipantResults) {
        printScores(results)
        printGameResults(results.dealerResult, results.playerResults)
        printProfits(results)
    }

    private fun printScores(results: ParticipantResults) {
        println(
            """
            |
            |${
            (listOf(results.dealerResult) + results.playerResults)
                .joinToString("\n", transform = ::getScoreMessage)
            }
            """.trimMargin()
        )
    }

    private fun getScoreMessage(result: ParticipantResult): String =
        "${result.name} 카드: ${result.cards.joinToString(", ") { it.toPresentation() }} - 결과: ${result.score}"

    private fun printGameResults(dealerResult: DealerResult, playerResults: List<PlayerResult>) {
        println(
            """
            |
            |## 최종 승패
            |${dealerResult.name}: ${dealerResult.win}승 ${dealerResult.draw}무 ${dealerResult.lose}패
            |${playerResults.joinToString("\n", transform = ::getPlayerResultMessage)}
            """.trimMargin()
        )
    }

    private fun getPlayerResultMessage(result: PlayerResult): String = when (result.gameResult) {
        GameResult.BLACKJACK -> "${result.name}: 블랙잭"
        GameResult.WIN -> "${result.name}: 승"
        GameResult.DRAW -> "${result.name}: 무"
        GameResult.LOSE -> "${result.name}: 패"
    }

    private fun Card.toPresentation(): String = "${number.toPresentation()}${suit.toPresentation()}"

    private fun CardNumber.toPresentation(): String = when (this) {
        CardNumber.ACE -> CardNumberModel.ACE
        CardNumber.TWO -> CardNumberModel.TWO
        CardNumber.THREE -> CardNumberModel.THREE
        CardNumber.FOUR -> CardNumberModel.FOUR
        CardNumber.FIVE -> CardNumberModel.FIVE
        CardNumber.SIX -> CardNumberModel.SIX
        CardNumber.SEVEN -> CardNumberModel.SEVEN
        CardNumber.EIGHT -> CardNumberModel.EIGHT
        CardNumber.NINE -> CardNumberModel.NINE
        CardNumber.TEN -> CardNumberModel.TEN
        CardNumber.JACK -> CardNumberModel.JACK
        CardNumber.QUEEN -> CardNumberModel.QUEEN
        CardNumber.KING -> CardNumberModel.KING
    }.text

    private fun Suit.toPresentation(): String = when (this) {
        Suit.SPADE -> SuitModel.SPADE
        Suit.HEART -> SuitModel.HEART
        Suit.DIAMOND -> SuitModel.DIAMOND
        Suit.CLOVER -> SuitModel.CLOVER
    }.text

    private fun printProfits(results: ParticipantResults) {
        println(
            """
            |
            |## 최종 수익
            |${
            (listOf(results.dealerResult) + results.playerResults)
                .joinToString("\n", transform = ::getProfitMessage)
            }
            """.trimMargin()
        )
    }

    private fun getProfitMessage(result: ParticipantResult) = "${result.name}: ${result.profit}"
}
