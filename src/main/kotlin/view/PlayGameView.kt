package view

import model.domain.player.User
import model.tools.Participant
import view.tools.Answer
import view.tools.UserNameContainer
import view.util.toCardInfo

object PlayGameView {
    private const val ERROR_EMPTY_USER_NAME = "[ERROR] 유저의 이름이 비어있습니다."
    private const val REQUEST_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val REQUEST_BETTING_MONEY = "%s의 배팅 금액은?"
    private const val NOTICE_DEALER_PICK_NEW_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
    private const val NOTICE_SPLIT_CARD = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val USER_RESULT_FORMAT = "%s: %s"
    private const val SEPARATOR = ","
    private const val REQUEST_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val ERROR_REQUEST_MORE_CARD = "[ERROR] 커맨드를 입력해주세요"

    fun requestPlayerName(): List<String> {
        println(REQUEST_PLAYER_NAME)
        val userNames = readln().split(SEPARATOR).map { it.trim() }

        return runCatching { UserNameContainer(userNames).names }
            .onFailure { println(ERROR_EMPTY_USER_NAME) }
            .getOrElse { requestPlayerName() }
    }

    fun requestPlayerBettingMoney(user: User): Int {
        println(REQUEST_BETTING_MONEY.format(user.name))

        return readln().toIntOrNull() ?: return requestPlayerBettingMoney(user)
    }

    fun requestMoreCardIfWant(user: User): Answer {
        println(REQUEST_MORE_CARD.format(user.name))

        return runCatching { Answer(readln()) }
            .onFailure { println(ERROR_REQUEST_MORE_CARD) }
            .getOrElse { requestMoreCardIfWant(user) }
    }

    fun noticeUserHandCard(user: User) {
        println(formatPlayerCard(user))
    }

    fun printNoticeSplitCard(participant: Participant) {
        val names = participant.user.map { it.name }.joinToString(SEPARATOR)

        println(NOTICE_SPLIT_CARD.format(names))
        printPlayerCard(participant)
    }

    private fun printPlayerCard(participant: Participant) {
        with(participant) {
            println(USER_RESULT_FORMAT.format(dealer.name, dealer.state.hand.cards.first().toCardInfo()))
            user.forEach { user ->
                println(formatPlayerCard(user))
            }
        }
    }

    private fun formatPlayerCard(user: User) =
        USER_RESULT_FORMAT.format(
            user.name,
            user.state.hand.cards.joinToString(SEPARATOR) { card ->
                card.toCardInfo()
            },
        )

    fun printDealerPickNewCard() = println(NOTICE_DEALER_PICK_NEW_CARD)
}
