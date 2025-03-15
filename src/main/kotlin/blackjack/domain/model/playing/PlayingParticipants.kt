package blackjack.domain.model.playing

import blackjack.domain.model.finished.MatchDealer
import blackjack.domain.model.finished.MatchParticipants
import blackjack.domain.model.finished.MatchPlayer

class PlayingParticipants(val playingDealer: PlayingDealer, val playingPlayers: List<PlayingPlayer>) {
    val participants get() = listOf(playingDealer, *playingPlayers.toTypedArray())

    fun toMatchPlayers(): MatchParticipants {
        val matchPlayers =
            playingPlayers.map { playingPlayer ->
                val matchResult = playingPlayer.match(playingDealer)
                MatchPlayer(playingPlayer.name, matchResult)
            }
        val matchDealer = MatchDealer(playingDealer.name)
        return MatchParticipants(matchDealer, matchPlayers)
    }
}
