package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTest {
    @DisplayName("게이머 생성")
    @Test
    fun nameSplitTest() {
        val inputNames = "test1,test2,test3"
        val gamers = Gamers(inputNames)
        listOf("test1", "test2", "test3")
        assertThat(gamers.names).isEqualTo(listOf(Gamer("test1"), Gamer("test2"), Gamer("test3")))
    }

    @DisplayName("카드 객체 생성")
    @Test
    fun carList() {
        assertThat(TrumpCard(TrumpCardNumber.FIVE, TrumpCardPattern.CLOVER).getScore()).isEqualTo(5)
    }
}
