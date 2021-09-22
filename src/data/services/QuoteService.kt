package api.shmehdi.qouteapp.data.services

import api.shmehdi.qouteapp.data.models.entities.Quote
import api.shmehdi.qouteapp.database.dao.QuoteDao
import api.shmehdi.qouteapp.errors.NotFoundException

class QuoteService(private val quoteDao: QuoteDao) {

    suspend fun addQuote(quote: Quote): Quote? {
        val id = quoteDao.addQuote(quote)
        return if (id != null)
            getQuote(id)
        else null
    }

    @Throws(NotFoundException::class)
    suspend fun getQuote(id: Int) = quoteDao.getQuote(id) ?: throw NotFoundException("Quote not found")

    suspend fun getQuotes() = quoteDao.getQuotes()

    @Throws(NotFoundException::class)
    suspend fun updateQuote(id: Int, quote: Quote) {
        getQuote(id)
        quoteDao.updateQuote(id, quote)
    }

    @Throws(NotFoundException::class)
    suspend fun deleteQuote(id: Int) {
        getQuote(id)
        quoteDao.deleteQuote(id)
    }
}