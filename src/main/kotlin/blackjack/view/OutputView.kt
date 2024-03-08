package blackjack.view

import blackjack.base.BaseHolder
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.Participants.Companion.INITIAL_CARD_COUNTS
import blackjack.model.Player
import blackjack.model.PlayerGroup
import blackjack.model.UserState

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
        showStateOfPlayer(player)
    }

    private fun showStateOfPlayer(player: Player) {
        when (player.hand.state) {
            UserState.BUST -> println("${player.humanName}의 카드가 ${Hand.BLACKJACK_NUMBER}을 초과하여 Bust되었습니다.")
            UserState.STAY -> println("${player.humanName}이(가) Stay에 성공하였습니다.")
            UserState.BLACKJACK -> println("${player.humanName}이(가) BlackJack을 달성했습니다.")
            UserState.RUNNING -> return
        }
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
