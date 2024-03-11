package blackjack.view

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
            "\n딜러와 ${playerGroup.players.joinToString(", ") { it.name.name }}에게 ${INITIAL_CARD_COUNTS}장의 카드를 나누었습니다.",
        )
        showDealerInitCard(dealer)
        showPlayersInitCards(playerGroup)
        println()
    }

    private fun showDealerInitCard(dealer: Dealer) {
        val showCard = dealer.status.hand.cards.first()
        println("딜러: $showCard")
    }

    private fun showPlayersInitCards(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            showPlayerCards(player)
        }
    }

    fun showPlayerCards(player: Player) {
        println(convertPlayerCardsToString(player))
        showStateOfPlayer(player)
    }

    private fun showStateOfPlayer(player: Player) {
        when (player.status.state) {
            UserState.BUST -> println("${player.name}의 카드가 ${Hand.BLACKJACK_NUMBER}을 초과하여 Bust되었습니다.")
            UserState.STAY -> println("${player.name}이(가) Stay에 성공하였습니다.")
            UserState.BLACKJACK -> println("${player.name}이(가) BlackJack을 달성했습니다.")
            UserState.RUNNING -> return
        }
    }

    private fun convertPlayerCardsToString(player: Player) =
        "${player.name}: ${player.status.hand.cards.joinToString(", ")}"

    private fun convertDealerCardsToString(dealer: Dealer) =
        "딜러: ${dealer.status.hand.cards.joinToString(", ")}"

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
        println("${convertDealerCardsToString(dealer)} - 결과: ${dealer.status.hand.calculate()}")
    }

    private fun showPlayersCardsResult(playerGroup: PlayerGroup) {
        playerGroup.players.forEach { player ->
            println("${convertPlayerCardsToString(player)} - 결과: ${player.status.hand.calculate()}")
        }
    }

    fun printMatchResult(
        dealer: Dealer,
        playerGroup: PlayerGroup,
    ) {
        println("\n[ 최종 승패 ]")
        println("딜러: ${dealer.gameResult}")
        playerGroup.players.forEach { player ->
            println("${player.name}: ${player.gameResult}")
        }
    }
}
