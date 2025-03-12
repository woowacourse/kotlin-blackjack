package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.card.Card
import blackjack.domain.person.Dealer
import blackjack.domain.person.Person
import blackjack.domain.person.Player

class OutputView : BlackJackOutputView {
    override fun printMessage(message: String?) {
        println(ERROR_MESSAGE_PREFIX.format(message))
    }

    override fun printInitialDrawMessage(
        dealer: Dealer,
        players: List<Player>,
    ) {
        printGameStartMessage(players)
        printFirstTurnDrawStatus(dealer, players)
        println()
    }

    override fun printPlayerDrawStatus(player: Player) {
        printDrawStatus(player.name, player.cards().toUiString())
        println()
    }

    override fun printDealerDrawNotice() {
        println()
        println(DEALER_DRAW_MESSAGE)
    }

    override fun printPersonResult(person: Person) {
        val name = (person as? Player)?.name ?: DEALER
        if (name == DEALER) println()
        printFinishCardStatus(name, person.cards(), person.isBlackJackString())
    }

    override fun printGameResult(result: GameResult) {
        println("\n" + RESULT_HEADLINE_MESSAGE)
        println(PLAYER_RESULT_MESSAGE.format(DEALER, result.dealerProfit.formatAmount()))
        result.playerPayouts.forEach { (player, payout) ->
            println(PLAYER_RESULT_MESSAGE.format(player.name, payout.formatAmount()))
        }
    }

    private fun printGameStartMessage(players: List<Player>) {
        println()
        val nameList = players.joinToString(DELIMITER) { it.name }
        println(FIRST_DRAW_MESSAGE.format(nameList))
    }

    private fun printFirstTurnDrawStatus(
        dealer: Dealer,
        players: List<Player>,
    ) {
        printDrawStatus(DEALER, dealer.cards().first().toUiString() + "\n")
        players.forEach { player ->
            printDrawStatus(player.name, player.cards().toUiString() + "\n")
        }
    }

    private fun printFinishCardStatus(
        name: String,
        cards: List<Card>,
        score: String,
    ) {
        printDrawStatus(name, cards.toUiString())
        println(SCORE_RESULT_MESSAGE.format(score))
    }

    private fun printDrawStatus(
        name: String,
        cards: String,
    ) {
        print(DRAW_STATUS_MESSAGE.format(name, cards))
    }

    companion object {
        private const val DEALER = "딜러"
        private const val FIRST_DRAW_MESSAGE = "${DEALER}와 %s에게 2장을 나누었습니다."
        private const val DEALER_DRAW_MESSAGE = "${DEALER}는 16이하라 한장의 카드를 더 받았습니다."
        private const val DRAW_STATUS_MESSAGE = "%s 카드: %s"
        private const val SCORE_RESULT_MESSAGE = " - 결과: %s"
        private const val RESULT_HEADLINE_MESSAGE = "## 최종 수익"
        private const val PLAYER_RESULT_MESSAGE = "%s: %s"
        private const val DELIMITER = ", "
        private const val ERROR_MESSAGE_PREFIX = "[ERROR] %s"
    }
}
