package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.player.Participants

class InputView {

    fun readParticipants(): Participants? {
        println(PROMPT_PARTICIPANTS_NAME)

        return kotlin.runCatching {
            val participantsName = readln().split(",")
            val participants = participantsName.map { Participant(it) }.toList()
            println()
            Participants(participants)
        }.getOrNull()
    }

    fun readHitOrNot(name: String): Boolean? {
        println(ASK_MORE_CARD.format(name))

        return when (readln().lowercase()) {
            ANSWER_HIT -> true
            ANSWER_NOT_HIT -> false
            else -> null
        }
    }

    companion object {
        private const val ANSWER_HIT = "y"
        private const val ANSWER_NOT_HIT = "n"

        private const val ASK_MORE_CARD = "%s은(는) 한장의 카드를 더 받겠습니까?(예는 $ANSWER_HIT, 아니오는 $ANSWER_NOT_HIT)"
        private const val PROMPT_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    }
}
