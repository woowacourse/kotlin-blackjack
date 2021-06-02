package domain

import domain.participant.Player
import java.lang.IllegalStateException

enum class ResultType(val profitRate: Int) {
    BLACKJACK(2) {
        override fun matchType(difference: Int, player: Player): Boolean {
            return difference > 0 && player.isBlackJackScore()
        }
    },
    WIN(1) {
        override fun matchType(difference: Int, player: Player): Boolean {
            return difference > 0 && !player.isBlackJackScore()
        }
       },
    TIE(0) {
        override fun matchType(difference: Int, player: Player): Boolean {
            return difference == 0 && !player.isBust()
        }
        },
    LOSE(-1) {
        override fun matchType(difference: Int, player: Player): Boolean {
            return difference <= 0 || player.isBust()
        }
    };

    abstract fun matchType(difference: Int, player: Player): Boolean

}

fun getResultType(difference: Int, player: Player): ResultType {
    return enumValues<ResultType>().find { it.matchType(difference, player) }
        ?: throw IllegalStateException()
}
