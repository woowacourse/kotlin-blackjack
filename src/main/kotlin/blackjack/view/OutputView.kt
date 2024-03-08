package blackjack.view

import blackjack.base.BaseHolder
import blackjack.model.Dealer
import blackjack.model.Participants.Companion.INITIAL_CARD_COUNTS
import blackjack.model.Player
import blackjack.model.PlayerGroup

object OutputView {
    fun printGameSetting(
        dealer: Dealer,
        playerGroup: PlayerGroup,
    ) {
        println(
            "\n${dealer.humanName}와 ${playerGroup.players.joinToString(", ") { it.humanName.name }}에게 ${INITIAL_CARD_COUNTS}장의 카드를 나누었습니다.",
        )
        showDealerInitCard(dealer)
        showPlayersInitCards(playerGroup)
        println()
    }

    private fun showDealerInitCard(dealer: Dealer) {
        val showCard = dealer.hand.cards.first()
        println("${dealer.humanName}: $showCard")
    }

    private fun showPlayersInitCards(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            showPlayerCards(player)
        }
    }

    fun showPlayerCards(player: Player) {
        println(convertHolderCardsToString(player))
    }

    private fun convertHolderCardsToString(holder: BaseHolder) =
        "${holder.humanName}: ${holder.hand.cards.joinToString(", ")}"

    fun printDealerDrawCard() {
        println("\n딜러의 카드가 ${Dealer.THRESHOLD}이하 이므로, 1장의 카드를 더 받습니다.")
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
        println("${convertHolderCardsToString(dealer)} - 결과: ${dealer.hand.calculate()}")
    }

    private fun showPlayersCardsResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            println("${convertHolderCardsToString(player)} - 결과: ${player.hand.calculate()}")
        }
    }

    fun printMatchResult(
        dealer: Dealer,
        playerGroup: PlayerGroup,
    ) {
        println("\n[ 최종 승패 ]")
        println("${dealer.humanName}: ${dealer.gameResult}")
        playerGroup.players.forEach { player ->
            println("${player.humanName}: ${player.gameResult}")
        }
    }
}
