package domain.game

import domain.participant.Dealer
import domain.participant.Participants
import domain.participant.Player

class GameResult(private val participants: Participants) {
    fun getPlayersProfit(): Map<Player, Int> {
        return participants.players.list.associateWith { player -> player.getProfit(participants.dealer.getScore()) }
    }

    fun getDealerProfit(): Pair<Dealer, Int> {
        val playersProfit = getPlayersProfit()
        return Pair(participants.dealer, -playersProfit.values.sum())
    }
}
