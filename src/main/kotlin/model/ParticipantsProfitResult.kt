package model

class ParticipantsProfitResult(val value: List<Result>) {
    val dealerResult: Result
        get() = value.find { it.participant.isDealer() }!!
    val playersResult: List<Result>
        get() = value.filter { !it.participant.isDealer() }
}
