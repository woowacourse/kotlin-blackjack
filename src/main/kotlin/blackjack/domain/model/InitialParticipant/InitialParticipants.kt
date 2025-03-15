
import blackjack.domain.model.playing.PlayingParticipants

class InitialParticipants(val initialDealer: InitialDealer, val initialPlayers: List<InitialPlayer>) {
    val participants get() = listOf(initialDealer, *initialPlayers.toTypedArray())

    fun toPlayingParticipants() = PlayingParticipants(initialDealer.toPlayingDealer(), initialPlayers.map { it.toPlayingPlayer() })
}
