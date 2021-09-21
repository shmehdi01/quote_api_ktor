package api.shmehdi.qouteapp.database.dao

import api.shmehdi.qouteapp.data.models.entities.Quote

interface QuoteDao {

    suspend fun addQuote(q: Quote): Int?

    suspend fun updateQuote(id: Int, q: Quote)

    suspend fun getQuotes(): List<Quote>

    suspend fun getQuote(id: Int): Quote?

    suspend fun deleteQuote(id: Int)

    suspend fun getQuoteByAuthor(author: String): List<Quote>
}