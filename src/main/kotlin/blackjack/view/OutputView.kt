package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.view.model.DealerResult
import blackjack.view.model.DealerSummary
import blackjack.view.model.PlayerResult
import blackjack.view.model.PlayerSummary

class OutputView {
    fun requestPlayers() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun showCardDealing(
        playersName: List<String>,
        dealerCardsContent: List<String>,
        playerCardsContent: List<List<String>>,
    ) {
        println()
        println("${playersName.joinToString()}에게 2장씩 나누었습니다.")
        println("딜러가 한 장을 오픈했습니다.")
        println("딜러: ${dealerCardsContent.joinToString()}")
        playersName.zip(playerCardsContent).forEach { (name, cardsContent) ->
            println("${name}카드: ${cardsContent.joinToString()}")
        }
        println()
    }

    fun askWantToHit(name: String) {
        println("${name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun showPlayerCards(
        name: String,
        cards: List<String>,
        score: Int,
    ) {
        println("${name}카드: ${cards.joinToString()}, 점수 : $score")
    }

    fun showParticipantsSummary(
        dealerSummary: DealerSummary,
        playerSummaries: List<PlayerSummary>,
    ) {
        println()
        println("$dealerSummary")
        playerSummaries.forEach { playerSummary -> println("$playerSummary") }
        println()
    }

    fun showResult(
        dealerResult: DealerResult,
        playersResults: List<PlayerResult>,
    ) {
        println("## 최종 승패")
        println("딜러: $dealerResult")
        playersResults.forEach { playerResult ->
            println("${playerResult.name}: ${playerResult.result}")
        }
    }

    fun showDealerHit() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    val Card.prettyString: String
        get() = rank.prettyString + suit.prettyString

    val Rank.prettyString: String
        get() =
            when (this) {
                Rank.AceRank -> "A"
                is Rank.FaceRank -> value.toString()
                is Rank.NumberRank -> value.toString()
            }

    val Suit.prettyString: String
        get() =
            when (this) {
                Suit.SPADE -> "스페이드"
                Suit.HEART -> "하트"
                Suit.DIAMOND -> "다이아몬드"
                Suit.CLOVER -> "클로버"
            }
}
