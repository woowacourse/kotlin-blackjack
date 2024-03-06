package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.Player

object OutputView {
    fun printGameSetting(
        dealer: Dealer,
        participants: Participants,
    ) {
        println("${dealer.name}와 ${participants.players.joinToString(", ") { it.name }}에게 2장의 카드를 나누었습니다.")
        showDealerInitCard(dealer) // 딜러 패 출력
        showPlayersInitCards(participants)
    }

    private fun showDealerInitCard(dealer: Dealer) {
        val showCard = dealer.deck.cards.first()
        println("${dealer.name}: $showCard")
    }

    private fun showPlayersInitCards(participants: Participants) {
        participants.players.forEach { player ->
            showPlayerCards(player)
        }
    }

    fun showPlayerCards(player: Player) {
        println("${player.name}: ${player.deck.cards.joinToString(", ")}")
    }
}
