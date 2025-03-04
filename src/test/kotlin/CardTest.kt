import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardTest {
    private lateinit var card: Deck

    @BeforeEach
    fun setup() {
        val symbols = Shape.entries
        val cardNumbers = CardNumber.entries

        card = Deck(symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } })
    }

    @Test
    fun `심볼과 카드 숫자를 매핑하여 카드를 생성한다`() {
        val deck = Card.initCard(FalseShuffle())
        assertThat(deck).isEqualTo(card)
    }

    @Test
    fun `카드를 셔플해서 첫번째 장에 있는 카드를 나누어준다`() {
        val deck = Card.initCard(FalseShuffle())
        val card = deck.spreadCard()

        assertThat(card).isEqualTo(Card(Shape.Diamond, CardNumber.Ace))
    }
}
