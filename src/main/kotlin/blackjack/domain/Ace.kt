package blackjack.domain

class Ace : Rank {
    override val possibleValues: List<Int> = ACE_VALUES

    companion object {
        val ACE_VALUES = listOf(1, 11)
    }
}
