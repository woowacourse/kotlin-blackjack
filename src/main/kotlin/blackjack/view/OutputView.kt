package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.state.ResultState

class OutputView {
    fun printEnterPlayerNamesMessage() {
        println(ENTER_PLAYER_NAMES_MESSAGE)
    }

    fun printInitialDrawMessage(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println()
        val nameList = players.joinToString(DELIMITER) { it.name }
        println(FIRST_DRAW_MESSAGE.format(nameList))
        printDrawStatus(DEALER, dealer.cards.first().toUiString() + "\n")
        players.forEach { player ->
            printDrawStatus(player.name, player.cards.toUiString() + "\n")
        }
        println()
    }

    fun printAskForDrawCardMessage(name: String) {
        println(ASK_DRAW_CARD_MESSAGE.format(name))
    }

    fun printPlayerDrawStatus(player: Player) {
        printDrawStatus(player.name, player.cards.toUiString())
        println()
    }

    fun printDealerDrawMessage() {
        println()
        println(DEALER_DRAW_MESSAGE)
    }

    fun printDealerResult(dealer: Dealer) {
        println()
        printGameResult(DEALER, dealer.cards, dealer.score)
    }

    fun printPlayerResult(player: Player) {
        printGameResult(player.name, player.cards, player.score)
    }

    private fun printGameResult(
        name: String,
        cards: List<Card>,
        score: Int,
    ) {
        printDrawStatus(name, cards.toUiString())
        println(SCORE_RESULT_MESSAGE.format(score))
    }

    fun printGameResults(result: GameResult) {
        println("\n" + RESULT_HEADLINE_MESSAGE)
        printDealerWinInfo(result.countByResultState())
        result.winStatus.forEach {
            println(PLAYER_RESULT_MESSAGE.format(it.key.name, it.value.toUiString()))
        }
    }

    private fun printDealerWinInfo(countByResultState: Map<ResultState, Int>) {
        println(
            DEALER_RESULT_MESSAGE.format(
                countByResultState[ResultState.LOSE] ?: 0,
                countByResultState[ResultState.DRAW] ?: 0,
                countByResultState[ResultState.WIN] ?: 0,
            ),
        )
    }

    private fun printDrawStatus(
        name: String,
        cards: String,
    ) {
        print(DRAW_STATUS_MESSAGE.format(name, cards))
    }

    private fun ResultState.toUiString() =
        when (this) {
            ResultState.WIN -> WIN
            ResultState.DRAW -> DRAW
            else -> LOSE
        }

    private fun List<Card>.toUiString(): String = joinToString(DELIMITER) { it.toUiString() }

    private fun Card.toUiString(): String = "${number.toUiString()}${pattern.toUiString()}"

    private fun CardNumber.toUiString(): String =
        when (this) {
            CardNumber.ACE -> "A"
            CardNumber.KING -> "K"
            CardNumber.QUEEN -> "Q"
            CardNumber.JACK -> "J"
            else -> this.value.toString()
        }

    private fun CardPattern.toUiString(): String =
        when (this) {
            CardPattern.HEART -> "하트"
            CardPattern.SPADE -> "스페이드"
            CardPattern.DIAMOND -> "다이아몬드"
            CardPattern.CLOVER -> "클로버"
        }

    companion object {
        private const val DEALER = "딜러"
        private const val ENTER_PLAYER_NAMES_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val FIRST_DRAW_MESSAGE = "${DEALER}와 %s에게 2장을 나누었습니다."
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
