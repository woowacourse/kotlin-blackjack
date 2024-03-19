package blackjack.view

import blackjack.model.gameInfo.GameInfo
import blackjack.model.participants.Judge

object ResultView {
    private const val MESSAGE_PARTICIPANT_CARD_RESULT = "%s - 결과: %d"
    private const val MESSAGE_TITLE_RESULT = "\n## 최종 금액"
    private const val MESSAGE_CARD_INFO = "%s카드: %s"
    private const val MESSAGE_INCOME_STATUS = "%s: %s"

    fun printFinalCards(
        dealerGameInfo: GameInfo,
        playersGameInfo: List<GameInfo>,
    ) {
        println()
        println(
            MESSAGE_PARTICIPANT_CARD_RESULT.format(
                MESSAGE_CARD_INFO.format(
                    dealerGameInfo.name,
                    dealerGameInfo.cards.joinToString { "${it.title}${it.shape.title}" },
                ),
                dealerGameInfo.sumOfCards,
            ),
        )

        playersGameInfo.forEach { playerStat ->
            println(
                MESSAGE_PARTICIPANT_CARD_RESULT.format(
                    MESSAGE_CARD_INFO.format(
                        playerStat.name,
                        playerStat.cards.joinToString { "${it.title}${it.shape.title}" },
                    ),
                    playerStat.sumOfCards,
                ),
            )
        }
    }

    fun printResult(judge: Judge) {
        println(MESSAGE_TITLE_RESULT)
        with(judge) {
            println(MESSAGE_INCOME_STATUS.format(dealer.name, getDealerIncome().amount))
            getPlayersIncome().zip(players) { money, stat ->
                println(MESSAGE_INCOME_STATUS.format(stat.name, money.amount))
            }
        }
    }
}
