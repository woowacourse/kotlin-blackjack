package blackjack.domain.card

sealed class CardsState {

    object BlackJack : CardsState()

    data class Running(val score: Int) : CardsState()

    object Bust : CardsState()

    //TODO: 객체가 스스로 일할 수 있게
    companion object {

        private const val BLACKJACK_SCORE = 21

        fun valueOf(
            score: Int,
            isInitialDraw: Boolean = false,
        ): CardsState {

            return when{
                score > BLACKJACK_SCORE -> Bust
                score == BLACKJACK_SCORE && isInitialDraw -> BlackJack
                else -> Running(score)
            }
        }
    }
}
