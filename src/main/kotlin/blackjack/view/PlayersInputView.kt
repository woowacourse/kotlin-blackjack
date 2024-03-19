package blackjack.view

import blackjack.model.BattingMoney
import blackjack.model.deck.Card
import blackjack.model.participant.Players

class PlayersInputView {
    fun read(cards: (Int) -> List<Card>): Players {
        val names = readPlayerNames()
        val battingMoneys = readBattingMoney(names)
        return Players(names.zip(battingMoneys).toMap(), cards)
    }

    private fun readPlayerNames(): Set<String> =
        retryWhileNotException {
            println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            val names =
                readln().splitNames()
            validatePlayerNames(names)
            names.toSet()
        }

    private fun validatePlayerNames(names: List<String>) {
        require(names.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { "${MIN_PLAYER_COUNT}명 이상 ${MAX_PLAYER_COUNT}명 이하의 플레이어만 가능합니다." }
        require(names.distinct().size == names.size) {
            "중복된 이름은 플레이어로 등록할 수 없습니다."
        }
    }

    private fun readBattingMoney(playerNames: Set<String>): List<BattingMoney> =
        retryWhileNotException {
            val battingMoney = mutableListOf<String>()
            playerNames.forEach {
                println("${it}의 배팅 금액은?")
                val money = readln()
                isPositiveDigit(it)
                battingMoney.add(money)
            }
            battingMoney.map { BattingMoney.ofAmount(it.toInt()) }
        }

    private fun String.splitNames() =
        split(SPLIT_DELIMITER)
            .map { it.trim() }
            .filterNot { it.isBlank() }

    private fun isPositiveDigit(it: String) = it.toIntOrNull() != null && it.toInt() >= MINIMUM_DIGIT

    companion object {
        private const val SPLIT_DELIMITER = ","
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 6
        private const val MINIMUM_DIGIT = 0
    }
}
