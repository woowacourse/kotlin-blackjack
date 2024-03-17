package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSymbol
import blackjack.model.game.GameInformation
import blackjack.model.game.GameState
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

        val actual = gameInformation.hand.cards

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
    fun `카드 2장 뽑았을때 21일때 BlackJack 상태 변경 확인`() {
        gameInformation.drawCard(Card(CardNumber.JACK, CardSymbol.SPADE))
        gameInformation.drawCard(Card(CardNumber.ACE, CardSymbol.HEART))

        val actual = gameInformation.state

        assertThat(actual).isEqualTo(GameState.Finished.BLACKJACK)
    }

    @Test
    fun `카드 3장 뽑았을때 21일때 STAY 상태 확인`() {
        gameInformation.drawCard(Card(CardNumber.JACK, CardSymbol.SPADE))
        gameInformation.drawCard(Card(CardNumber.SEVEN, CardSymbol.HEART))
        gameInformation.drawCard(Card(CardNumber.FOUR, CardSymbol.HEART))

        val actual = gameInformation.state

        assertThat(actual).isEqualTo(GameState.Running.HIT)
    }
}
