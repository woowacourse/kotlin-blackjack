package view.onboarding

import domain.person.Dealer
import domain.person.Persons
import domain.person.Player
import view.ViewCommon.toText

object OnboardingOutput {
    private const val ERROR_INPUT_BLACK = "공백은 입력할 수 없습니다. 다시 입력하세요."
    private const val NAME_INPUT_SCRIPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val SHARE_TWO_CARDS_SCRIPT = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s"

    fun printRequestInputName() {
        println(NAME_INPUT_SCRIPT)
    }

    fun printBlankError() {
        println(ERROR_INPUT_BLACK)
    }

    fun printShareTwoCards(persons: Persons) {
        println(
            SHARE_TWO_CARDS_SCRIPT.format(
                persons.dealer.name,
                persons.players.joinToString(", ") { it.name },
            ),
        )
    }

    fun printDealerCards(dealer: Dealer) {
        println(
            INITIAL_CARDS_SCRIPT.format(
                dealer.name,
                dealer.showFirstCard().joinToString { it.toText() },
            ),
        )
    }

    fun printPlayerCards(players: List<Player>) {
        players.forEach { player ->
            println(
                INITIAL_CARDS_SCRIPT.format(
                    player.name,
                    player.showHandOfCards().joinToString(",") { it.toText() },
                ),
            )
        }
    }
}
