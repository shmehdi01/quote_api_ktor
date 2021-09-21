package api.shmehdi.qouteapp.database.impl

import api.shmehdi.qouteapp.data.models.entities.Quote
import api.shmehdi.qouteapp.data.models.entities.Quotes
import api.shmehdi.qouteapp.database.dao.QuoteDao
import api.shmehdi.qouteapp.database.dbQuery
import api.shmehdi.qouteapp.utils.getSingleOrNull
import api.shmehdi.qouteapp.utils.toQuote
import org.jetbrains.exposed.sql.*

class QuoteDaoImpl : QuoteDao {

    override suspend fun addQuote(q: Quote): Int? =
        dbQuery {
            return@dbQuery Quotes.insert {
                it[quote] = q.quote
                it[author] = q.author
                it[authorId] = q.author
                it[userId] = q.userId
                it[isActive] = true

            }.resultedValues?.single()?.get(Quotes.id)
        }


    override suspend fun updateQuote(id: Int, q: Quote) {
        dbQuery {
            Quotes.update({ Quotes.id eq q.id }) {
                it[quote] = q.quote
            }
        }
    }

    override suspend fun getQuotes(): List<Quote> = dbQuery {
        Quotes.selectAll().map { toQuote(it) }
    }

    override suspend fun getQuote(id: Int): Quote? = dbQuery {
        Quotes.select { Quotes.id eq id }.getSingleOrNull {
            toQuote(it)
        }
    }

    override suspend fun deleteQuote(id: Int) {
        dbQuery {
            Quotes.deleteWhere {
                Quotes.id eq id
            }
        }
    }

    override suspend fun getQuoteByAuthor(author: String): List<Quote> {
        return dbQuery {
            return@dbQuery Quotes.select { Quotes.author eq author }.map { toQuote(it) }
        }
    }
}