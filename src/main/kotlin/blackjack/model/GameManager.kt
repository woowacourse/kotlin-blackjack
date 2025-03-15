package blackjack.model

import blackjack.model.card.Deck
import blackjack.model.dto.ParticipantProfitInfo
import blackjack.model.state.CardDrawDecision
import blackjack.model.user.Dealer
import blackjack.model.user.Participant
import blackjack.model.user.Player

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val deck = Deck.create()

    fun distributeInitialCardWithCount(count: Int) {
        distributeCardWithCount(dealer, count)
        players.forEach { player ->
            distributeCardWithCount(player, count)
        }
    }

    private fun distributeCardWithCount(
        participant: Participant,
        count: Int,
    ) {
        repeat(count) {
            participant.addCard(deck.draw())
        }
    }

    fun distributeCardWithChoice(
        drawDecision: CardDrawDecision,
        player: Player,
    ): Boolean {
        if (drawDecision.isDraw()) {
            distributeCard(player)
            return true
        }
        return false
    }

    fun distributeCard(participant: Participant) {
        participant.addCard(deck.draw())
    }

    fun getPlayersProfit(): List<ParticipantProfitInfo> =
        players.map { player ->
            ParticipantProfitInfo(player.name, ProfitCalculator.calculateProfit(dealer, player))
        }

    fun getDealerProfit(playerProfitInfo: List<ParticipantProfitInfo>): ParticipantProfitInfo {
        val playersSum = playerProfitInfo.sumOf { player -> player.profit.amount }
        return ParticipantProfitInfo(dealer.name, Money(playersSum * -1))
    }
}
