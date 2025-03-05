package blackjack.view

class OutputView {
    fun displayPlayerEnrollGuide() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun displayFirstDrawEnd(players: List<String>) {
        println("딜러와 ${players.joinToString(", ") }}에게 2장을 나누었습니다.")
    }

    fun displayDealerDrawInfo() {
        println("딜러는 16이하라 한 장의 카드를 더 받았습니다.")
    }

    fun displayPersonInfo(
        name: String,
        cards: List<String>,
        score: Int,
    ) {
        println("$name 카드: ${cards.joinToString(", ") }} - 결과: $score")
    }

    fun displayResultTitle() {
        println("## 최종 승패")
    }

    fun displayDealerResult(
        name: String,
        winningCount: Int,
        loseCount: Int,
    ) {
        println("$name: ${winningCount}승 ${loseCount}패")
    }

    fun displayPlayerResult(
        name: String,
        isWinning: Boolean,
    ) {
        println("$name: ${if (isWinning) "승" else "패"}")
    }
}
