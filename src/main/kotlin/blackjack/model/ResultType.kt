package blackjack.model

enum class ResultType(val value: Char) {
    WIN('승'),
    TIE('무'),
    LOSS('패'), ;

    companion object {
        fun judgeScore(
            reference: Participant,
            target: Participant,
        ): ResultType {
            val referenceFinalScore = ScoreCalculator.calculateFinalScore(reference.cards)
            val targetFinalScore = ScoreCalculator.calculateFinalScore(target.cards)
            if (targetFinalScore < referenceFinalScore) return WIN
            if (targetFinalScore == referenceFinalScore) return TIE
            return LOSS
        }
    }
}
