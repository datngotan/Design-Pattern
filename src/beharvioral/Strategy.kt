package beharvioral

interface RouteStrategy {
    fun buildRoute()
}


class RoadStrategy: RouteStrategy {
    override fun buildRoute() {
        println("Building route by road strategy...")
    }
}

class PublicTransportStrategy: RouteStrategy {
    override fun buildRoute() {
        println("Building route by public transport strategy...")
    }
}

class WalkingStrategy: RouteStrategy {
    override fun buildRoute() {
        println("Building route by walking strategy...")
    }
}

// context use strategy and swap between the strategy at run time.
class RoutePlanner(
    private var strategy: RouteStrategy
) {

    fun setStrategy(strategy: RouteStrategy) {
        this.strategy = strategy
    }

    fun buildRoute() {
        strategy.buildRoute()
    }
}


fun main() {
    val planner = RoutePlanner(PublicTransportStrategy())
    planner.buildRoute()
    planner.setStrategy(RoadStrategy())
    planner.buildRoute()
    planner.setStrategy(WalkingStrategy())
    planner.buildRoute()
}