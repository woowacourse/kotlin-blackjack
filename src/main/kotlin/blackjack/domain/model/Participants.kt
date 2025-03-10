package blackjack.domain.model

class Participants(val dealer: Dealer, val players: List<Player>) {
    val list: List<Participant> = listOf(dealer) + players

    init {
        val participantNames: List<String> = listOf(dealer.name) + players.map { player -> player.name }
        require(participantNames == participantNames.distinct()) { MESSAGE_ERROR_PARTICIPANT_NAMES_NOT_UNIQUE }
    }

    companion object {
        private const val MESSAGE_ERROR_PARTICIPANT_NAMES_NOT_UNIQUE = "참가자들의 이름은 서로 중복될 수 없습니다."
    }
}
