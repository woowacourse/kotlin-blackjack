package blackjack.view

import blackjack.model.ParticipantName

object InputView {
    private const val INVALID_INPUT = 0

    fun readNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return (readlnOrNull() ?: "").split(",")
    }

    fun readBetAmount(name: ParticipantName): Int {
        println("${name}의 배팅 금액은?")
        return readlnOrNull()?.toIntOrNull() ?: INVALID_INPUT
    }

    fun askMoreCard(name: ParticipantName): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readlnOrNull() ?: "") {
            "y" -> true
            "n" -> false
            else -> askMoreCard(name)
        }
    }
}
