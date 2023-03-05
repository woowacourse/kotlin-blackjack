package view

import domain.constant.Decision

object RequestView {
    private const val ERROR_INPUT_BLACK = "공백은 입력할 수 없습니다."
    private const val NAME_INPUT_SCRIPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ONE_MORE_CARD_SCRIPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun requestInputNames(): List<String> {
        println(NAME_INPUT_SCRIPT)
        val input = readln()
        if (input.isBlank()) {
            println(ERROR_INPUT_BLACK)
            return requestInputNames()
        }
        return input.split(',').map { it.trim() }
    }

    fun requestPlayerDecision(name: String): String {
        println(ONE_MORE_CARD_SCRIPT.format(name))
        val input = readln().trim()
        if (!Decision.has(input)) return requestPlayerDecision(name)
        return input
    }
}
