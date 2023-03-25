package view

import model.tools.Participant
import view.util.toCardInfo

object GameResultView {
    private const val PLAYER_RESULT_PROFIT = "%s: %d"
    private const val PLAYER_CARD_RESULT = "%s카드: %s - 결과: %d"
    private const val SEPARATOR = ", "
    private const val FINAL_RESULT = "\n## 최종 수익"

    fun printFinalResult(participant: Participant) {
        with(participant) {
            println(
                PLAYER_CARD_RESULT.format(
                    dealer.name,
                    dealer.state.hand.cards.joinToString(SEPARATOR) { card ->
                        card.toCardInfo()
                    },
                    dealer.state.hand.getTotalScoreWithAceCard()
                )
            )
            user.forEach { user ->
                println(
                    PLAYER_CARD_RESULT.format(
                        user.name,
                        user.state.hand.cards.joinToString(SEPARATOR) { card ->
                            card.toCardInfo()
                        },
                        user.state.hand.getTotalScoreWithAceCard()
                    )
                )
            }
        }
    }

    fun printFinalProfit(participants: Participant) {
        println(FINAL_RESULT)
        with(participants) {
            println(
                PLAYER_RESULT_PROFIT.format(
                    dealer.name,
                    dealer.money.amount
                )
            )
            user.forEach { user ->
                println(
                    PLAYER_RESULT_PROFIT.format(
                        user.name,
                        user.money.amount
                    )
                )
            }
        }
    }
}
