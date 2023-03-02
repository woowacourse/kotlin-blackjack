package blackjack.view

object InputView {
    private const val INSERT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ANSWER_YES = "y"
    private const val ANSWER_NO = "n"
    private const val INSERT_GET_ONE_MORE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 $ANSWER_YES, 아니오는 $ANSWER_NO)"

    fun getNames(): List<String> {
        println(INSERT_PLAYERS_NAME)
        val input = readln()
        val names = input.split(",").map { it.trim() }
        return if (names.all { it.isNotBlank() }) names else getNames()
    }

    fun doesPlayerWantHit(name: String): Boolean {
        println(INSERT_GET_ONE_MORE_CARD.format(name))
        return when (readln().lowercase()) {
            "y" -> true
            "n" -> false
            else -> doesPlayerWantHit(name)
        }
    }
}
