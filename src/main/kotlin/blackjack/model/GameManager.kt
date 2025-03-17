package blackjack.model

import blackjack.model.Money.Companion.plus
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

    fun drawInitialCardWithCount(count: Int) {
        drawCardWithCount(dealer, count)
        players.forEach { player ->
            drawCardWithCount(player, count)
        }
    }

    private fun drawCardWithCount(
        participant: Participant,
        count: Int,
    ) {
        repeat(count) {
            participant.addCard(deck.draw())
        }
    }

    fun processDrawOrStayBasedOnPlayer(
        player: Player,
        playerDrawDecision: (Player) -> CardDrawDecision,
        showPlayerHands: (Player) -> Unit,
    ) {
        while (true) {
            val decision: CardDrawDecision = playerDrawDecision(player)
            if (drawCardWithChoice(decision, player)) {
                showPlayerHands(player)
                if (player.isBust()) break
                continue
            }
            showPlayerHands(player)
            break
        }
    }

    private fun drawCardWithChoice(
        drawDecision: CardDrawDecision,
        player: Player,
    ): Boolean {
        if (drawDecision.isDraw()) {
            drawCard(player)
            return true
        }
        return false
    }

    fun isDrawCardBasedOnDealer(): Boolean {
        if (dealer.isAvailDrawCard()) {
            drawCard(dealer)
            return true
        }
        return false
    }

    private fun drawCard(participant: Participant) {
        participant.addCard(deck.draw())
    }

    fun getPlayersProfit(): List<ParticipantProfitInfo> =
        players.map { player ->
            ParticipantProfitInfo(player.name, ProfitCalculator.calculateProfit(dealer, player))
        }

    fun getDealerProfit(playersProfitInfo: List<ParticipantProfitInfo>): ParticipantProfitInfo {
        val playersSum = playersProfitInfo.fold(Money.from(0L)) { sum, player -> sum + player.profit }
        return ParticipantProfitInfo(dealer.name, Money.toDealerMoney(playersSum))
    }
}
