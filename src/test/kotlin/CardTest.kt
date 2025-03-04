import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardTest {
    private lateinit var card: List<Card>

    @BeforeEach
    fun setup() {
        val symbols = listOf("다이아몬드", "하트", "클로버", "스페이드")
        val cardNumbers = CardNumber.entries

        card = symbols.flatMap { symbol -> cardNumbers.map { cardNumber -> Card(symbol, cardNumber) } }
    }

    @Test
    fun `심볼과 카드 숫자를 매핑하여 카드를 생성한다`() {
        val deck = Card.deck
        assertThat(deck).isEqualTo(card)
    }
}
