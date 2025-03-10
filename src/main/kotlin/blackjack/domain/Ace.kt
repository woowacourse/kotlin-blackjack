package blackjack.domain

object Ace : Rank {
    override val possibleValues: Set<Int> = setOf(1, 11)
}
