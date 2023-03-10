package blackjack.view

class InputView {

    fun readParticipantsName(): List<String> {
        println(PROMPT_PARTICIPANTS_NAME)
        return readln().split(",")
    }

    fun readMoreCard(name: String): Boolean {
        println(ASK_MORE_CARD.format(name))
        val input = readln()
        return (input == ANSWER_MORE_CARD)
    }

    companion object {
        private const val ASK_MORE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val PROMPT_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ANSWER_MORE_CARD = "y"
    }
}
