package dsl

class CatsBuilder {
    private val cats = mutableListOf<Cat>()

    fun momo(block: CatBuilder.() -> Unit) {
        cats.add(CatBuilder().apply(block).build("모모"))
    }

    fun chichi(block: CatBuilder.() -> Unit) {
        cats.add(CatBuilder().apply(block).build("치치"))
    }

    fun build(): List<Cat> = cats
}