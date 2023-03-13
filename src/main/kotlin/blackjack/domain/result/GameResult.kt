package blackjack.domain.result

class GameResult(val participantsProfit: List<ParticipantProfit>) {
    val dealerProfit: Int = -(
        participantsProfit.sumOf {
            it.profit
        }
        )
}
