package blackjack.domain.player

import blackjack.domain.BettingAmount

class Participants(val values: List<Participant>) {
    init {
        require(values.size in PARTICIPANTS_MIN_NUMBER..PARTICIPANTS_MAX_NUMBER) { ERROR_PARTICIPANTS_BOUNDARY }
    }

    constructor(vararg values: String) : this(values.map { Participant(it) }.toList())

    fun bettingParticipants(getBettingAmount: (Participant) -> BettingAmount) {
        values.forEach {
            it.bettingAmount = getBettingAmount(it)
        }
    }

    companion object {
        const val PARTICIPANTS_MIN_NUMBER = 2
        const val PARTICIPANTS_MAX_NUMBER = 8
        const val ERROR_PARTICIPANTS_BOUNDARY = "참가자 인원은 2명 이상 8명 이하여야합니다"
    }
}
