package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import state.BlackJackState
import state.FirstState
import state.HitState

class FirstStateTest {
    @Test
    fun `카드를 한장 받으면  FirstState가 된다`() {
        val firstState = FirstState()
        val result = firstState.draw(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        assertThat(result).isInstanceOf(FirstState::class.java)
    }

    @Test
    fun `카드가 두 장이 됐는데 21이면 BlackJackState가 된다`() {
        val firstState = FirstState()
        val result = firstState.draw(Card.of(CardCategory.CLOVER, CardNumber.ACE))
            .draw(Card.of(CardCategory.CLOVER, CardNumber.JACK))
        assertThat(result).isInstanceOf(BlackJackState::class.java)
    }

    @Test
    fun `카드가 두 장이 됐는데 21이 안된다면 HitState가 된다`() {
        val firstState = FirstState()
        val result = firstState.draw(Card.of(CardCategory.CLOVER, CardNumber.ACE))
            .draw(Card.of(CardCategory.CLOVER, CardNumber.NINE))
        assertThat(result).isInstanceOf(HitState::class.java)
    }
}
