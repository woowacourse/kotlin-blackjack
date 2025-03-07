package blackjack.view

import blackjack.uiModel.PersonUiModel
import blackjack.uiModel.ResultUiModel

class OutputView {
    fun printNameMessage() {
        println(ENTER_PLAYER_NAMES_MESSAGE)
    }

    fun printDrawMessage(personUiModels: List<PersonUiModel>) {
        println()
        val nameList = personUiModels.map { it.name }.joinToString(DELIMITER)
        println(FIRST_DRAW_MESSAGE.format(nameList))
        personUiModels.forEach { person ->
            println(
                DRAW_STATUS_MESSAGE.format(
                    person.name,
                    if (person.name == DEALER) person.cards.first() else person.cards.joinToString(DELIMITER),
                ),
            )
        }
        println()
    }

    fun printFlagMessage(name: String) {
        println(ASK_DRAW_CARD_MESSAGE.format(name))
    }

    fun printDrawStatus(personUiModel: PersonUiModel) {
        println(DRAW_STATUS_MESSAGE.format(personUiModel.name, personUiModel.cards.joinToString(DELIMITER)))
    }

    fun printDealerDrawMessage() {
        println(DEALER_DRAW_MESSAGE)
        println()
    }

    fun printGameResult(personUiModels: List<PersonUiModel>) {
        personUiModels.forEach { person ->
            print(DRAW_STATUS_MESSAGE.format(person.name, person.cards.joinToString(DELIMITER)))
            println(SCORE_RESULT_MESSAGE.format(person.score))
        }
        println()
    }

    fun printResult(resultUiModels: List<ResultUiModel>) {
        println(RESULT_HEADLINE_MESSAGE)
        val dealerWins = resultUiModels.count { it.result == LOSE }
        val dealerLoses = resultUiModels.count { it.result == WIN }
        val dealerDraws = resultUiModels.count { it.result == DRAW }
        println(DEALER_RESULT_MESSAGE.format(dealerWins, dealerDraws, dealerLoses))
        resultUiModels.forEach { println(PLAYER_RESULT_MESSAGE.format(it.name, it.result)) }
    }

    companion object {
        private const val DEALER = "딜러"
        private const val ENTER_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val FIRST_DRAW_MESSAGE = "%s에게 2장을 나누었습니다."
        private const val ASK_DRAW_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)"
        private const val DEALER_DRAW_MESSAGE = "${DEALER}는 16이하라 한장의 카드를 더 받았습니다."
        private const val DRAW_STATUS_MESSAGE = "%s 카드: %s"
        private const val SCORE_RESULT_MESSAGE = " - 결과: %s"
        private const val RESULT_HEADLINE_MESSAGE = "## 최종 승패"
        private const val WIN = "승"
        private const val LOSE = "패"
        private const val DRAW = "무"
        private const val DEALER_RESULT_MESSAGE = "$DEALER: %s$WIN %s$DRAW %s$LOSE"
        private const val PLAYER_RESULT_MESSAGE = "%s: %s"
        private const val DELIMITER = ", "
    }
}
