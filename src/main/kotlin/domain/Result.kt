package domain

enum class Result(val result: String, val compareStatus: (Int, Int) -> Boolean) {
    WIN("승", { playerScore, dealerScore -> playerScore > dealerScore }),
    STAND_OFF("무", { playerScore, dealerScore -> playerScore == dealerScore }),
    LOSE("패", { playerScore, dealerScore -> playerScore < dealerScore });

    companion object {
        @JvmStatic
        fun find(player: Player, dealer: Dealer) : Result {
            return values().firstOrNull { it.compareStatus(player.score(), dealer.score()) }
                ?: throw IllegalArgumentException("승패 결과를 찾을 수 없습니다.")
        }
    }
}