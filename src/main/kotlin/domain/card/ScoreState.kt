package domain.card

sealed class ScoreState {
    object Burst : ScoreState()
    object BlackJack : ScoreState()
    data class Normal(val value: Int) : ScoreState()
}
