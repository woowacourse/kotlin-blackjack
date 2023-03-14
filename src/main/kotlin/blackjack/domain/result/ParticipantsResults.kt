package blackjack.domain.result

class ParticipantsResults(val participantsResults: List<ParticipantResult>) {
    fun getDealerResult(): Int =
        -participantsResults.sumOf { it.getProfit() }
}
