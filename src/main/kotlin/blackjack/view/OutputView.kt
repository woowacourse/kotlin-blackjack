package blackjack.view

import blackjack.domain.BetAmount
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.DrawResult
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerName
import blackjack.domain.Shape

object OutputView {

    private const val CARD_DIVIDING_MSG = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val SEPARATOR = ","
    private const val SHOW_DEALER_CARD = "딜러 카드: %s"
    private const val SHOW_PLAYER_CARDS = "%s 카드: %s"
    private const val DEALER_RECEIVED_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_RECEIVED_NOTHING_MSG = "딜러는 17이상이라 한장의 카드를 더 받지 않았습니다."
    private const val FINAL_SCORE = " - 결과: %d"
    private const val GAME_RESULTS = "### 최종 승패"
    private const val DEALER_GAME_RESULTS = "딜러: %d승 %d패 %d무"
    private const val PLAYER_GAME_RESULT = "%s: %s"
    private const val BET_RESULTS = "### 최종 수익"
    private const val DEALER_DIVIDEND_RESULT = "딜러: %d"
    private const val PLAYER_DIVIDEND_RESULT = "%s: %d"

    private const val HEART_DESCRIPTION = "하트"
    private const val DIAMOND_DESCRIPTION = "다이아몬드"
    private const val CLOVER_DESCRIPTION = "클로버"
    private const val SPADE_DESCRIPTION = "스페이드"
    private const val WIN_DESCRIPTION = "승"
    private const val LOSE_DESCRIPTION = "패"
    private const val DRAW_DESCRIPTION = "무"

    fun printCardDividingMessage(participants: Pair<Dealer, List<Player>>) {
        println()
        println(CARD_DIVIDING_MSG.format(participants.second.joinToString(SEPARATOR) { player -> player.name.value }))
        println(SHOW_DEALER_CARD.format(makeToString(participants.first.cardHand.cards.first())))
        participants.second.forEach { player -> printCardResults(player) }
        println()
    }

    fun printCardResults(player: Player) {
        println(
            SHOW_PLAYER_CARDS.format(
                player.name.value,
                player.cardHand.cards.joinToString(SEPARATOR) { card -> makeToString(card) }
            )
        )
    }

    private fun makeToString(card: Card): String {
        var numberValue = card.number.name
        if (numberValue.length != Card.SPECIAL_CARDS_NAME_LENGTH) {
            numberValue = card.number.value.toString()
        }
        return numberValue + makeShapeDescription(card.shape)
    }

    private fun makeShapeDescription(shape: Shape): String {
        return when (shape) {
            Shape.HEART -> HEART_DESCRIPTION
            Shape.DIAMOND -> DIAMOND_DESCRIPTION
            Shape.CLOVER -> CLOVER_DESCRIPTION
            Shape.SPADE -> SPADE_DESCRIPTION
        }
    }

    fun printIsDealerReceivedCard(drawResult: DrawResult) {
        println()
        when (drawResult) {
            DrawResult.Success -> println(DEALER_RECEIVED_CARD_MSG)
            DrawResult.Failure -> println(DEALER_RECEIVED_NOTHING_MSG)
        }
    }

    fun printFinalCards(participants: Pair<Dealer, List<Player>>) {
        println()
        println(
            SHOW_DEALER_CARD.format(participants.first.cardHand.cards.joinToString(SEPARATOR) { card -> makeToString(card) }) + FINAL_SCORE.format(
                participants.first.cardHand.getTotalCardsScore()
            )
        )
        participants.second.forEach { player ->
            println(
                SHOW_PLAYER_CARDS.format(
                    player.name.value,
                    player.cardHand.cards.joinToString(SEPARATOR) { card -> makeToString(card) }
                ) + FINAL_SCORE.format(
                    player.cardHand.getTotalCardsScore()
                )
            )
        }
    }

    fun printGameResults(playerGameResults: List<PlayerGameResult>, dealerGameResult: List<GameResult>) {
        println()
        println(GAME_RESULTS)
        println(
            DEALER_GAME_RESULTS.format(
                dealerGameResult.count { gameResult -> gameResult == GameResult.WIN },
                dealerGameResult.count { gameResult -> gameResult == GameResult.LOSE },
                dealerGameResult.count { gameResult -> gameResult == GameResult.DRAW }
            )
        )
        playerGameResults.forEach { playerGameResult ->
            println(
                PLAYER_GAME_RESULT.format(
                    playerGameResult.player.name.value,
                    makeGameResultDescription(playerGameResult.gameResult)
                )
            )
        }
    }

    private fun makeGameResultDescription(gameResult: GameResult): String {
        return when (gameResult) {
            GameResult.WIN -> WIN_DESCRIPTION
            GameResult.BLACKJACK -> WIN_DESCRIPTION
            GameResult.LOSE -> LOSE_DESCRIPTION
            GameResult.DRAW -> DRAW_DESCRIPTION
        }
    }

    fun printErrorMessage(exception: Throwable) {
        println(exception.message)
    }

    fun printBetResults(dividend: Pair<BetAmount, Map<PlayerName, BetAmount>>) {
        println()
        println(BET_RESULTS)
        println(DEALER_DIVIDEND_RESULT.format(dividend.first.money))
        dividend.second.forEach { player ->
            println(PLAYER_DIVIDEND_RESULT.format(player.key.value, player.value.money))
        }
    }
}
