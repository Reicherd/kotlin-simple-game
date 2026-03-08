package arw.simple.game.tile

enum class TileDesign(val value: Int) {
    GRASS(0),
    WALL(1),
    WATER(2),
    EARTH(3),
    TREE(4),
    SAND(5),
    ;

    companion object {
        fun fromValue(value: Int): TileDesign = when (value) {
            GRASS.value -> GRASS
            WALL.value -> WALL
            WATER.value -> WATER
            SAND.value -> SAND
            EARTH.value -> EARTH
            TREE.value -> TREE
            else -> {
                throw IllegalArgumentException("Illegal value $value")
            }
        }
    }
}