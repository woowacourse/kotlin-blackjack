package blackjack.domain.model.participant

import blackjack.domain.model.progress.WinLoss

enum class ProfitRate(
    val value: Double,
) {
    BLACKJACK_WIN(1.5),
    NORMAL_WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    companion object {
        fun calculateProfitRate(
            winLoss: WinLoss,
            cardStatus: CardStatus,
        ): ProfitRate =
            when (winLoss) {
                WinLoss.WIN -> if (cardStatus == CardStatus.BLACKJACK) BLACKJACK_WIN else NORMAL_WIN
                WinLoss.LOSE -> LOSE
                WinLoss.DRAW -> DRAW
            }
    }
}
