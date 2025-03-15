
import blackjack.domain.model.Card
import blackjack.domain.model.Hands

abstract class InitialParticipant {
    abstract val name: String
    protected abstract var hands: Hands

    abstract fun showInitCards(): List<Card>

    fun showCards(count: Int = hands.size) = hands.extractCards(count)
}
