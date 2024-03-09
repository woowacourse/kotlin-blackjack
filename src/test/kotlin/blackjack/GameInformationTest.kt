package blackjack

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.GameInformation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameInformationTest {
    @Test
    fun `카드 한장 뽑아서 손패에 추가`() {
        val gameInformation = GameInformation()
        val card = Card(CardNumber.ACE, CardSymbol.SPADE)
        gameInformation.drawCard(card)

        val actual = gameInformation.cards

        assertThat(actual.size).isEqualTo(1)
        assertThat(actual.contains(card)).isTrue
    }
}
