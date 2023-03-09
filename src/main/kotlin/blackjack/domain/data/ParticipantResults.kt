package blackjack.domain.data

data class ParticipantResults(val dealerResult: DealerResult, val playerResults: List<PlayerResult>, val profits: List<ParticipantProfit>)
