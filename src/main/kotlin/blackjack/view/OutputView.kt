package blackjack.view

import blackjack.model.BetAmount
import blackjack.model.CardNumber
import blackjack.model.Dealer
import blackjack.model.Dealer.Companion.DEALER_CARD_DRAW_THRESHOLD
import blackjack.model.GameResult
import blackjack.model.Participants
import blackjack.model.Participants.Companion.INITIAL_CARD_COUNTS
import blackjack.model.Pattern
import blackjack.model.Player
import blackjack.model.PlayerGroup
import blackjack.model.WinningState

object OutputView {
    private const val SPACE_NAME = "스페이스"
    private const val CLOVER_NAME = "클로버"
    private const val HEART_NAME = "하트"
    private const val DIAMOND_NAME = "다이아몬드"

    fun printGameSetting(participants: Participants) {
        println(
            "\n${participants.dealer.userInfo.nickname}와 ${
                participants.playerGroup.players.joinToString(
                    ", ",
                ) { it.userInfo.nickname.name }
            }에게 ${INITIAL_CARD_COUNTS}장의 카드를 나누었습니다.",
        )
        showDealerInitCard(participants.dealer)
        showPlayersInitCards(participants.playerGroup)
    }

    private fun showDealerInitCard(dealer: Dealer) {
        val showCard = dealer.getState().hand().cards.first()
        println("${dealer.userInfo.nickname}: ${getCardNumberName(showCard.number)}${getCardPatternName(showCard.pattern)}")
    }

    private fun showPlayersInitCards(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            showPlayerCards(player)
        }
    }

    fun showPlayerCards(player: Player) {
        println(
            "${player.userInfo.nickname}: ${
                player.getState().hand().cards.joinToString(", ") { card ->
                    getCardNumberName(card.number) + getCardPatternName(card.pattern)
                }
            }",
        )
    }

    fun printDealerDrawCard(dealer: Dealer) {
        println("\n${dealer.userInfo.nickname}의 카드가 ${DEALER_CARD_DRAW_THRESHOLD}이하 이므로, 1장의 카드를 더 받습니다.")
    }

    fun printEveryCards(participants: Participants) {
        println()
        showDealerCardsResult(participants.dealer)
        showPlayersCardsResult(participants.playerGroup)
    }

    private fun showDealerCardsResult(dealer: Dealer) {
        println(
            "${dealer.userInfo.nickname}: ${
                dealer.getState().hand().cards.joinToString(", ") { card ->
                    getCardNumberName(card.number) + getCardPatternName(card.pattern)
                }
            } - 결과: ${dealer.getState().hand().calculate()}",
        )
    }

    private fun showPlayersCardsResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            println(
                "${player.userInfo.nickname}: ${
                    player.getState().hand().cards.joinToString(", ") { card ->
                        getCardNumberName(card.number) + getCardPatternName(card.pattern)
                    }
                } - 결과: ${player.getState().hand().calculate()}",
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

    fun printError(e: Throwable) {
        println(e)
    }

    fun printGameResult(winningState: WinningState) {
        println("\n[ 최종 승익 ]")
        winningState.state.entries.forEach { (participant, winningState) ->
            when (participant) {
                is Dealer -> printDealerResult(participant, winningState)
                is Player -> printPlayerResult(participant, winningState)
            }
        }
    }

    private fun printDealerResult(
        dealer: Dealer,
        gameResult: GameResult,
    ) {
        println("${dealer.userInfo.nickname}: ${gameResult.calculate(BetAmount(1000))}")
    }

    private fun printPlayerResult(
        player: Player,
        gameResult: GameResult,
    ) {
        println("${player.userInfo.nickname}: ${gameResult.calculate(player.userInfo.betAmount)}")
    }
}
