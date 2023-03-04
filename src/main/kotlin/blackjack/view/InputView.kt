package blackjack.view

import blackjack.ANSWER_NO
import blackjack.ANSWER_YES
import blackjack.domain.Player

object InputView {
    private const val INSERT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INSERT_GET_ONE_MORE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 $ANSWER_YES, 아니오는 $ANSWER_NO)"

    fun getNames(): List<String> {
        println(INSERT_PLAYERS_NAME)
        val input = readln()
        val names = input.split(",").map { it.trim() }
        val allNamesAreNotBlank = names.all { it.isNotBlank() }
        return if (allNamesAreNotBlank) names else getNames()
    }

    fun getAnswerOf(player: Player): String {
        println(INSERT_GET_ONE_MORE_CARD.format(player.name))
        val input = readln().lowercase()
        return if (input == ANSWER_YES || input == ANSWER_NO) input else getAnswerOf(player)
    }
}
