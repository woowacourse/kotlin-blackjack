package blackjack.domain.carddeck.shapegenerator

import blackjack.Shape

class RandomShapeGenerator : ShapeGenerator {
    override fun pickShape(): Shape = Shape.values().toList().shuffled().last()
}
