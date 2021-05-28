package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName

internal class PlayerTest {

    private lateinit var player :Player

    @BeforeEach
    fun initPlayer(){
        player = Player("ecsimsw", 100)
    }

    @DisplayName("사용자에 카드를 추가한다.")
    @Test
    fun draw() {
        player.draw("k1")
        assertThat(player.cards).usingRecursiveComparison().isEqualTo(listOf("k1"))

        player.draw("a2", "c10")
        assertThat(player.cards).usingRecursiveComparison().isEqualTo(listOf("k1", "a2", "c10"))
    }
}