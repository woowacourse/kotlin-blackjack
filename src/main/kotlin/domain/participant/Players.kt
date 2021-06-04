package domain.participant

class Players(val players: List<Player> = listOf()) : Participants(players.map { it }) {
}