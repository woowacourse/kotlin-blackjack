package blackjack.domain

sealed interface Rank {
    val possibleValues: Set<Int>
}
