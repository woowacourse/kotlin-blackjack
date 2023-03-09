package domain.person

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    fun Player(cardNumbers: List<CardNumber>, name: String) =
        Player(cardNumbers.map { Card(CardShape.HEART, it) }, name)

    fun Dealer(cardNumbers: List<CardNumber>) = Dealer(cardNumbers.map { Card(CardShape.HEART, it) })

    @Test
    fun `플레이어 이름은 중복되면 안된다`() {
        // given
        val dealer = Dealer(listOf(CardNumber.EIGHT, CardNumber.KING))
        val players =
            listOf(
                Player(listOf(CardNumber.EIGHT, CardNumber.KING), "베르"),
                Player(listOf(CardNumber.EIGHT, CardNumber.KING), "베르"),
            )

        // then
        assertThrows<IllegalArgumentException> { Participants(dealer, players) }
    }

    @Test
    fun `플레이어 이름은 딜러와 같으면 안된다`() {
        // given
        val dealer = Dealer(listOf(CardNumber.EIGHT, CardNumber.KING))
        val players =
            listOf(
                Player(listOf(CardNumber.EIGHT, CardNumber.KING), "베르"),
                Player(listOf(CardNumber.EIGHT, CardNumber.KING), "딜러"),
            )

        // then
        assertThrows<IllegalArgumentException> { Participants(dealer, players) }
    }
}
