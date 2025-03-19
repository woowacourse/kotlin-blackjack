package blackjack.domain.model.hand

abstract class Playing(hands: Hands) : Initial(hands) {
    abstract val stayStrategy: StayStrategy

    override fun stay(): Finished = Stay(hands)

    override fun isFinished(): Boolean = false
}
