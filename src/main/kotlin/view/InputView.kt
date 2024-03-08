package view

import model.human.HumanName

object InputView {
    private const val HEADER_READ_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val HEADER_READ_ANSWER = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun readPlayerNames(): List<String> {
        println(HEADER_READ_PLAYER_NAMES)
        return readln().split(",").map { it.trim() }.toList()
    }

    fun readAnswer(humanName: HumanName): String {
        println(HEADER_READ_ANSWER.format(humanName.name))
        return readln()
    }
}
