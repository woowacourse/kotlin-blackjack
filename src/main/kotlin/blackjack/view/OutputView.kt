package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.Shape
import blackjack.domain.dealer.Dealer
import blackjack.domain.dealer.DrawResult
import blackjack.domain.gameResult.GameResult
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player

object OutputView {

    private const val SPECIAL_CARDS_NAME_LENGTH = 1
    private const val CARD_DIVIDING_MSG = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val SEPARATOR = ","
    private const val SHOW_DEALER_CARD = "딜러 카드: %s"
    private const val SHOW_PLAYER_CARDS = "%s 카드: %s"
    private const val DEALER_RECEIVED_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_RECEIVED_NOTHING_MSG = "딜러는 17이상이라 한장의 카드를 더 받지 않았습니다."
    private const val FINAL_SCORE = " - 결과: %d"
    private const val GAME_RESULTS = "###최종 승패"
    private const val DEALER_GAME_RESULTS = "딜러: %d"
    private const val PLAYER_GAME_RESULT = "%s: %d"
    private const val WIN_DESCRIPTION = "승"
    private const val DRAW_DESCRIPTION = "무"
    private const val LOSE_DESCRIPTION = "패"

    private fun Player.toCardsDescription(): String = SHOW_PLAYER_CARDS.format(
        name.value,
        cards.cards.joinToString(SEPARATOR) { card ->
            card.toDescription()
        }
    )

    private fun Card.toDescription(): String {
        var numberValue = number.name
        if (numberValue.length != SPECIAL_CARDS_NAME_LENGTH) {
            numberValue = number.value.toString()
        }
        return numberValue + this.shape.toDescription()
    }

    private fun Shape.toDescription(): String = when (this) {
        Shape.HEART -> "하트"
        Shape.CLOVER -> "클로버"
        Shape.SPADE -> "스페이드"
        Shape.DIAMOND -> "다이아몬드"
    }

    private fun GameResult.toDescription() = when (this) {
        GameResult.BLACKJACK_WIN -> WIN_DESCRIPTION
        GameResult.WIN -> WIN_DESCRIPTION
        GameResult.DRAW -> DRAW_DESCRIPTION
        GameResult.LOSE -> LOSE_DESCRIPTION
    }

    fun printCardDividingMessage(dealer: Dealer, players: List<Player>) {
        println()
        println(CARD_DIVIDING_MSG.format(players.joinToString(SEPARATOR) { player -> player.name.value }))
        println(SHOW_DEALER_CARD.format(dealer.cards.cards.first().toDescription()))
        players.forEach { player -> printPlayerCurrentCards(player) }
        println()
    }

    fun printPlayerCurrentCards(player: Player) {
        println(player.toCardsDescription())
    }

    fun printDealerCurrentCards(dealer: Dealer) {
        println(SHOW_DEALER_CARD.format(dealer.cards.cards.joinToString(SEPARATOR) { card -> card.toDescription() }))
    }

    fun printIsDealerReceivedCard(drawResult: DrawResult) {
        println()
        when (drawResult) {
            is DrawResult.Success -> println(DEALER_RECEIVED_CARD_MSG)
            is DrawResult.Failure -> println(DEALER_RECEIVED_NOTHING_MSG)
        }
    }

    fun printFinalCards(dealer: Dealer, players: List<Player>) {
        println()
        println(
            SHOW_DEALER_CARD.format(dealer.cards.cards.joinToString(SEPARATOR) { card -> card.toDescription() }) +
                    FINAL_SCORE.format(dealer.cards.getTotalCardsScore())
        )
        players.forEach { player ->
            print(player.toCardsDescription())
            println(FINAL_SCORE.format(player.cards.getTotalCardsScore()))
        }
    }

    fun printGameResults(totalGameResult: TotalGameResult) {
        println()
        println(GAME_RESULTS)
        println(
            DEALER_GAME_RESULTS.format(totalGameResult.dealerGameResults.value)
        )
        totalGameResult.playerGameResults.forEach { playerGameResult ->
            println(PLAYER_GAME_RESULT.format(playerGameResult.playerName, playerGameResult.profitMoney.value))
        }
    }

    fun printErrorMessage(exception: Throwable) {
        println(exception.message)
    }
}
