package blackjack.domain

object ScoreCalculator {
    fun possibleScoreOf(vararg cards: Card): Set<Int> {
        if (cards.isEmpty()) return emptySet()
        if (cards.size == 1) return cards.first().possibleScores
        if (cards.size == 2) return possibleScoreOf(cards[0], cards[1])

        var result = possibleScoreOf(cards[0], cards[1])
        (2 until cards.size).forEach { index: Int ->
            result = possibleScoreOf(result, cards[index].possibleScores)
        }
        return result
    }

    private fun possibleScoreOf(
        card1: Card,
        card2: Card,
    ): Set<Int> = possibleScoreOf(card1.possibleScores, card2.possibleScores)

    private fun possibleScoreOf(
        possibleScore1: Set<Int>,
        possibleScore2: Set<Int>,
    ): Set<Int> {
        var result: Set<Int> = setOf()

        possibleScore1.forEach { score1 ->
            possibleScore2.forEach { score2 ->
                result = result.plus(score1 + score2)
            }
        }

        return result
    }
}
