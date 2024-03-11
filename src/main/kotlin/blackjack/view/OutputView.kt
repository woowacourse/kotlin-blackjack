package blackjack.view

import blackjack.exception.Exceptions.InvalidNameLengthErrorException
import blackjack.exception.Exceptions.InvalidPlayersCountErrorException
import blackjack.exception.Exceptions.NoCardErrorException
import blackjack.model.Dealer
import blackjack.model.Dealer.Companion.DEALER_CARD_DRAW_THRESHOLD
import blackjack.model.Participants.Companion.INITIAL_CARD_COUNTS
import blackjack.model.Player
import blackjack.model.PlayerGroup

object OutputView {
    fun printGameSetting(
        dealer: Dealer,
        playerGroup: PlayerGroup,
    ) {
        println(
            "\n${dealer.nickname}와 ${playerGroup.players.joinToString(", ") { it.nickname.name }}에게 ${INITIAL_CARD_COUNTS}장의 카드를 나누었습니다.",
        )
        showDealerInitCard(dealer)
        showPlayersInitCards(playerGroup)
    }

    private fun showDealerInitCard(dealer: Dealer) {
        val showCard = dealer.hand.cards.first()
        println("${dealer.nickname}: $showCard")
    }

    private fun showPlayersInitCards(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            showPlayerCards(player)
        }
    }

    fun showPlayerCards(player: Player) {
        println("${player.nickname}: ${player.hand.cards.joinToString(", ")}")
    }

    fun printDealerDrawCard() {
        println("\n딜러의 카드가 ${DEALER_CARD_DRAW_THRESHOLD}이하 이므로, 1장의 카드를 더 받습니다.")
    }

    fun printEveryCards(
        dealer: Dealer,
        playerGroup: PlayerGroup,
    ) {
        println()
        showDealerCardsResult(dealer) // 딜러 패 출력
        showPlayersCardsResult(playerGroup)
    }

    private fun showDealerCardsResult(dealer: Dealer) {
        println("${dealer.nickname}: ${dealer.hand.cards.joinToString(", ")} - 결과: ${dealer.hand.calculate()}")
    }

    private fun showPlayersCardsResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            println("${player.nickname}: ${player.hand.cards.joinToString(", ")} - 결과: ${player.hand.calculate()}")
        }
    }

    fun printMatchResult(
        dealer: Dealer,
        playerGroup: PlayerGroup,
    ) {
        println("\n[ 최종 승패 ]")
        println("${dealer.nickname}: ${dealer.hand.gameResult}")
        playerGroup.players.forEach { player ->
            println("${player.nickname}: ${player.hand.gameResult}")
        }
    }

    fun printError(e: Throwable) {
        when (e) {
            is NoCardErrorException -> println(e.reason)
            is InvalidNameLengthErrorException -> println(e.reason)
            is InvalidPlayersCountErrorException -> println(e.reason)
        }
    }
}
