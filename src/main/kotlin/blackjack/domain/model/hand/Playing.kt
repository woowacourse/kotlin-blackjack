package blackjack.domain.model.hand

abstract class Playing(hands: Hands) : Initial(hands) {
    override fun stay(): Finished = Stay(hands)

    override fun isFinished(): Boolean = false
}
