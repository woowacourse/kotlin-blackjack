package blackjack.view

import blackjack.domain.BettingAmount
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import java.lang.IllegalArgumentException

class InputView {

    fun readParticipants(): Participants {
        println(PROMPT_MESSAGE_PARTICIPANTS_NAME)

        return kotlin.runCatching {
            val participantsName = readln().split(",")
            println()
            val participants = participantsName.map {
                Participant(name = it, bettingAmount = readParticipantBattingAmount(it))
            }.toList()
            Participants(participants)
        }.getOrElse { readParticipants() }
    }

    private fun readParticipantBattingAmount(participantName: String): BettingAmount {
        return kotlin.runCatching {
            println(PROMPT_FORMAT_MESSAGE_BETTING_AMOUNT.format(participantName))
            val input = readln().toInt()
            println()
            BettingAmount(input)
        }.getOrElse { readParticipantBattingAmount(participantName) }
    }

    fun readHitOrNot(name: String): Boolean {
        println(PROMPT_FORMAT_MESSAGE_HIT_CARD.format(name))

        return kotlin.runCatching {
            val input = readln().lowercase()
            if (input == ANSWER_HIT) return true
            if (input == ANSWER_NOT_HIT) return false
            throw IllegalArgumentException()
        }.getOrElse { readHitOrNot(name) }
    }

    companion object {
        private const val ANSWER_HIT = "y"
        private const val ANSWER_NOT_HIT = "n"

        private const val PROMPT_FORMAT_MESSAGE_HIT_CARD =
            "%s은(는) 한장의 카드를 더 받겠습니까?(예는 $ANSWER_HIT, 아니오는 $ANSWER_NOT_HIT)"
        private const val PROMPT_MESSAGE_PARTICIPANTS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val PROMPT_FORMAT_MESSAGE_BETTING_AMOUNT = "%s의 배팅 금액은?"
    }
}
