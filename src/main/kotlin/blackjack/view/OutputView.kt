package blackjack.view

import blackjack.model.CardNumber
import blackjack.model.Dealer
import blackjack.model.Dealer.Companion.DEALER_CARD_DRAW_THRESHOLD
import blackjack.model.GameResult
import blackjack.model.Participants
import blackjack.model.Participants.Companion.INITIAL_CARD_COUNTS
import blackjack.model.Pattern
import blackjack.model.Player
import blackjack.model.PlayerGroup

object OutputView {
    private const val SPACE_NAME = "스페이스"
    private const val CLOVER_NAME = "클로버"
    private const val HEART_NAME = "하트"
    private const val DIAMOND_NAME = "다이아몬드"

    fun printGameSetting(participants: Participants) {
        println(
            "\n${participants.dealer.nickname}와 ${
                participants.playerGroup.players.joinToString(
                    ", ",
                ) { it.nickname.name }
            }에게 ${INITIAL_CARD_COUNTS}장의 카드를 나누었습니다.",
        )
        showDealerInitCard(participants.dealer)
        showPlayersInitCards(participants.playerGroup)
    }

    private fun showDealerInitCard(dealer: Dealer) {
        val showCard = dealer.hand.cards.first()
        println("${dealer.nickname}: ${getCardNumberName(showCard.number)}${getCardPatternName(showCard.pattern)}")
    }

    private fun showPlayersInitCards(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            showPlayerCards(player)
        }
    }

    fun showPlayerCards(player: Player) {
        println(
            "${player.nickname}: ${
                player.hand.cards.joinToString(", ") { card ->
                    getCardNumberName(card.number) + getCardPatternName(card.pattern)
                }
            }",
        )
    }

    fun printDealerDrawCard(dealer: Dealer) {
        println("\n${dealer.nickname}의 카드가 ${DEALER_CARD_DRAW_THRESHOLD}이하 이므로, 1장의 카드를 더 받습니다.")
    }

    fun printEveryCards(participants: Participants) {
        println()
        showDealerCardsResult(participants.dealer)
        showPlayersCardsResult(participants.playerGroup)
    }

    private fun showDealerCardsResult(dealer: Dealer) {
        println(
            "${dealer.nickname}: ${
                dealer.hand.cards.joinToString(", ") { card ->
                    getCardNumberName(card.number) + getCardPatternName(card.pattern)
                }
            } - 결과: ${dealer.hand.calculate()}",
        )
    }

    private fun showPlayersCardsResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            println(
                "${player.nickname}: ${
                    player.hand.cards.joinToString(", ") { card ->
                        getCardNumberName(card.number) + getCardPatternName(card.pattern)
                    }
                } - 결과: ${player.hand.calculate()}",
            )
        }
    }

    private fun getCardNumberName(cardNumber: CardNumber): String {
        return when (cardNumber) {
            CardNumber.ACE -> "A"
            CardNumber.KING -> "K"
            CardNumber.JACK -> "J"
            CardNumber.QUEEN -> "Q"
            else -> cardNumber.value.toString()
        }
    }

    private fun getCardPatternName(pattern: Pattern): String {
        return when (pattern) {
            Pattern.SPADE -> SPACE_NAME
            Pattern.CLOVER -> CLOVER_NAME
            Pattern.HEART -> HEART_NAME
            Pattern.DIAMOND -> DIAMOND_NAME
        }
    }

    fun printMatchResult(participants: Participants) {
        println("\n[ 최종 승패 ]")
        println("${participants.dealer.nickname}: ${printResult(participants.dealer.hand.gameResult)}")
        participants.playerGroup.players.forEach { player ->
            println("${player.nickname}: ${printResult(player.hand.gameResult)}")
        }
    }

    private fun printResult(gameResult: GameResult): String {
        var answer = ""
        if (gameResult.win != GameResult.DEFAULT_RESULT_VALUE) answer += "${gameResult.win}승 "
        if (gameResult.push != GameResult.DEFAULT_RESULT_VALUE) answer += "${gameResult.push}무 "
        if (gameResult.defeat != GameResult.DEFAULT_RESULT_VALUE) answer += "${gameResult.defeat}패 "
        return answer
    }

    fun printError(e: Throwable) {
        println(e)
    }
}
