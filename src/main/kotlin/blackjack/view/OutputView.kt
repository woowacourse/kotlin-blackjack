package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.DrawResult
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerGameResult

object OutputView {

    private const val CARD_DIVIDING_MSG = "딜러와 %s에게 2장의 카드를 나누었습니다."
    private const val SEPERATOR = ","
    private const val SHOW_DEALER_CARD = "딜러 카드: %s"
    private const val SHOW_PLAYER_CARDS = "%s 카드: %s"
    private const val DEALER_RECEIVED_CARD_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_RECEIVED_NOTHING_MSG = "딜러는 17이상이라 한장의 카드를 더 받지 않았습니다."
    private const val FINAL_SCORE = " - 결과: %d"
    private const val GAME_RESULTS = "###최종 승패"
    private const val DEALER_GAME_RESULTS = "딜러: %d승 %d패 %d무"
    private const val PLAYER_GAME_RESULT = "%s: %s"

    fun printCardDividingMessage(dealer: Dealer, players: List<Player>) {
        println(CARD_DIVIDING_MSG.format(players.joinToString(SEPERATOR) { player -> player.name.value }))
        println(SHOW_DEALER_CARD.format(dealer.cards.cards.first()))
        players.forEach { player -> printCardResults(player) }
    }

    fun printCardResults(player: Player) {
        println(SHOW_PLAYER_CARDS.format(player.name, player.cards.cards.joinToString(SEPERATOR)))
    }

    fun printIsDealerReceivedCard(drawResult: DrawResult) {
        when (drawResult) {
            is DrawResult.Success -> println(DEALER_RECEIVED_CARD_MSG)
            is DrawResult.Failure -> println(DEALER_RECEIVED_NOTHING_MSG)
        }
    }

    fun printFinalCards(dealer: Dealer, players: List<Player>) {
        println(SHOW_DEALER_CARD.format(dealer.cards.cards.joinToString(SEPERATOR)) + FINAL_SCORE.format(dealer.cards.getTotalCardsValue()))
        players.forEach { player ->
            println(SHOW_PLAYER_CARDS.format(player.name, player.cards.cards.joinToString(SEPERATOR)) + FINAL_SCORE.format(player.cards.getTotalCardsValue()))
        }
    }

    fun printGameResults(playerGameResults: List<PlayerGameResult>, dealerGameResult: List<GameResult>) {
        println(GAME_RESULTS)
        println(
            DEALER_GAME_RESULTS.format(
                dealerGameResult.count { gameResult -> gameResult == GameResult.WIN },
                dealerGameResult.count { gameResult -> gameResult == GameResult.LOSE },
                dealerGameResult.count { gameResult -> gameResult == GameResult.DRAW }
            )
        )
        playerGameResults.forEach { playerGameResult ->
            println(PLAYER_GAME_RESULT.format(playerGameResult.playerName, playerGameResult.gameResult.description))
        }
    }

    fun printErrorMessage(exception: Throwable) {
        println(exception.message)
    }
}
