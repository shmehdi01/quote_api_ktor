package api.shmehdi.qouteapp.data.repository

import api.shmehdi.qouteapp.data.models.entities.Quote
import api.shmehdi.qouteapp.data.services.QuoteService

class QuoteRepository(private val quoteService: QuoteService) : BaseRepository {

    suspend fun getQuote(id: Int) = safeCall {
        quoteService.getQuote(id)
    }

    suspend fun getQuotes() = safeCall {
        quoteService.getQuotes()
    }

    suspend fun addQuote(quote: Quote) = safeCall {
        quoteService.addQuote(quote)
    }

    suspend fun deleteQuote(id: Int) = safeCall {
        quoteService.deleteQuote(id)
    }

    suspend fun updateQuote(id: Int, quote: Quote) = safeCall {
        quoteService.updateQuote(id, quote)
    }
}