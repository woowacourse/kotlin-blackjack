package blackjack.domain.carddeck.shapegenerator

import blackjack.Shape

interface ShapeGenerator {
    fun pickShape(): Shape
}
