package blackjack.domain

sealed interface Rank {
    val possibleValues: List<Int>
}
