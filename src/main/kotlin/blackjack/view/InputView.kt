package blackjack.view

import java.util.Locale

class InputView {

    fun readParticipantsName(): List<String> {
        println(PROMPT_PARTICIPANTS_NAME)
        return readln().split(",")
    }

    fun readParticipantBetAmount(name: String): Int {
        println(PROMPT_PARTICIPANT_BET_AMOUNT.format(name))
        return readln().toIntOrNull() ?: run {
            println(ERROR_NOT_NUMBER)
            readParticipantBetAmount(name)
        }
    }

    fun readMoreCard(name: String): Boolean {
        println(ASK_MORE_CARD.format(name))
        return when (readln().lowercase(Locale.getDefault())) {
            ANSWER_MORE_CARD -> true
            ANSWER_STOP_CARD -> false
            else -> run {
                println(ERROR_MORE_CARD_INVALIDATE_ANSWER)
                readMoreCard(name)
            }
        }
    }

    companion object {
        private const val PROMPT_PARTICIPANT_BET_AMOUNT = "%s의 베팅 금액은?"
        private const val ASK_MORE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val PROMPT_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val ANSWER_MORE_CARD = "y"
        private const val ANSWER_STOP_CARD = "n"
        private const val ERROR_MORE_CARD_INVALIDATE_ANSWER = "[ERROR] y 또는 n 을 입력해주세요"
        private const val ERROR_NOT_NUMBER = "[ERROR] 숫자만 입력해주세요"
    }
}
