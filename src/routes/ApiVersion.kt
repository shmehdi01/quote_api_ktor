package api.shmehdi.qouteapp.routes

import io.ktor.routing.*
import io.ktor.util.pipeline.*

@ContextDsl
fun Route.routeV1(path: String, build: Route.() -> Unit) {
    route("/api/v1/$path", build)
}

@ContextDsl
fun Route.routeV1(build: Route.() -> Unit) {
    route("api/v1", build)
}