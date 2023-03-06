package blackjack.view

import java.util.Locale

class InputView {

    fun readParticipantsName(): List<String> {
        println(PROMPT_PARTICIPANTS_NAME)
        return readln().split(",")
    }

    fun readHitOrNot(name: String): String {
        println(ASK_MORE_CARD.format(name))
        return readln().lowercase(Locale.getDefault())
    }

    companion object {
        const val ANSWER_HIT = "y"
        const val ANSWER_NOT_HIT = "n"

        private const val ASK_MORE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 $ANSWER_HIT, 아니오는 $ANSWER_NOT_HIT)"
        private const val PROMPT_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
