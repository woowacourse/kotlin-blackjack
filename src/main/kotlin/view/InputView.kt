package view

import model.Answer
import model.human.HumanName
import model.human.Player

object InputView {
    private const val HEADER_READ_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val HEADER_READ_BETTING_AMOUNT = "\n%s의 배팅 금액은?"
    private const val HEADER_READ_ANSWER = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    private val POSITIVE = listOf("y", "yes", "o", "예")
    private val NEGATIVE = listOf("n", "no", "x", "아니오")

    fun readPlayerNames(): List<String> {
        println(HEADER_READ_PLAYER_NAMES)
        return readln().split(",").map { it.trim() }.toList()
    }

    fun readBettingAmount(player: Player): Int? {
        println(HEADER_READ_BETTING_AMOUNT.format(player.getName()))
        val amount = readln().toIntOrNull()
        if (amount == null || amount <= 0) return null
        return amount
    }

    fun readAnswer(humanName: HumanName): Answer? {
        println(HEADER_READ_ANSWER.format(humanName.name))
        return when (readln().lowercase()) {
            in POSITIVE -> return Answer.YES
            in NEGATIVE -> return Answer.NO
            else -> null
        }
    }
}
