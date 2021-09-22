package api.shmehdi.qouteapp.routes

import api.shmehdi.qouteapp.data.models.dto.BaseResponse
import api.shmehdi.qouteapp.data.models.entities.Quote
import api.shmehdi.qouteapp.data.repository.QuoteRepository
import api.shmehdi.qouteapp.data.services.QuoteService
import api.shmehdi.qouteapp.database.impl.QuoteDaoImpl
import api.shmehdi.qouteapp.vo.Resource
import api.shmehdi.qouteapp.vo.errorResponse
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.quoteRoutes() {

    val quoteRepository = QuoteRepository(QuoteService(QuoteDaoImpl()))

    routeV1("/quotes") {

        get {
            when(val resource = quoteRepository.getQuotes()){
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        get("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("Id not found"))

            when(val resource = quoteRepository.getQuote(id)){
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        post {
            val quote = call.receive<Quote>()

            when(val resource = quoteRepository.addQuote(quote)){
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        put("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("Id not found"))
            val quote = call.receive<Quote>()

            when(val resource = quoteRepository.updateQuote(id,quote)){
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: 0
            if (id == 0) call.respond(BaseResponse.error("Id not found"))

            when(val resource = quoteRepository.deleteQuote(id)){
                is Resource.Error -> resource.errorResponse(call)
                is Resource.Success -> call.respond(BaseResponse.success(resource.data))
            }
        }
    }
}

fun Application.registerQuoteRoute() {
    routing {
        quoteRoutes()
    }
}