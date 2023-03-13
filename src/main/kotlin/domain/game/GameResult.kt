package domain.game

import domain.participant.Dealer
import domain.participant.Participants
import domain.participant.Player

class GameResult(private val participants: Participants) {
    fun getPlayersProfit(): Map<Player, Double> {
        return participants.players.list.associateWith { player -> player.getProfit(participants.dealer.state) }
    }

    fun getDealerProfit(): Pair<Dealer, Double> {
        val playersProfit = getPlayersProfit()
        return Pair(participants.dealer, -playersProfit.values.sum())
    }
}
