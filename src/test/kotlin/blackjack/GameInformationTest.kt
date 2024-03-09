package blackjack

import blackjack.model.Card
import blackjack.model.CardNumber
import blackjack.model.CardSymbol
import blackjack.model.GameInformation
import blackjack.model.GameState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameInformationTest {
    private lateinit var gameInformation: GameInformation

    @BeforeEach
    fun setUp() {
        gameInformation = GameInformation()
    }

    @Test
    fun `카드 한장 뽑아서 손패에 추가`() {
        val card = Card(CardNumber.ACE, CardSymbol.SPADE)
        gameInformation.drawCard(card)

        val actual = gameInformation.cards

        assertThat(actual.size).isEqualTo(1)
        assertThat(actual.contains(card)).isTrue
    }

    @Test
    fun `카드 추가로 뽑았을때 Bust 상태 변경 확인`() {
        gameInformation.drawCard(Card(CardNumber.JACK, CardSymbol.SPADE))
        gameInformation.drawCard(Card(CardNumber.QUEEN, CardSymbol.HEART))
        gameInformation.drawCard(Card(CardNumber.KING, CardSymbol.CLOVER))

        val actual = gameInformation.state

        assertThat(actual).isEqualTo(GameState.Finished.BUST)
    }

    @Test
    fun `카드 추가로 뽑았을때 BlackJack 상태 변경 확인`() {
        gameInformation.drawCard(Card(CardNumber.JACK, CardSymbol.SPADE))
        gameInformation.drawCard(Card(CardNumber.QUEEN, CardSymbol.HEART))
        gameInformation.drawCard(Card(CardNumber.ACE, CardSymbol.CLOVER))

        val actual = gameInformation.state

        assertThat(actual).isEqualTo(GameState.Finished.BLACKJACK)
    }
}
