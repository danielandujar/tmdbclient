package com.cry.tmdbclient.models.database.obj

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_BACKDROP_PATH
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_BUDGET
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_HAS_VIDEO
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_HOMEPAGE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_IS_ADULT
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_IS_FAVORITE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_MOVIE_ID
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_MOVIE_TAGLINE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_MOVIE_TITLE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_MOVIE_TITLE_ORIGINAL
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_ORIGINAL_LANG
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_OVERVIEW
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_POPULARITY
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_POSTER_PATH
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_RELEASE_DATE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_REVENUE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_RUNTIME
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_STATUS
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_VOTE_AVERAGE
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.COLUMN_VOTE_COUNT
import com.cry.tmdbclient.models.database.DatabaseContract.TableMovie.TABLE_NAME
import org.threeten.bp.Instant

@Entity(tableName = TABLE_NAME)
data class Movie(
    @PrimaryKey @NonNull @ColumnInfo(name=COLUMN_MOVIE_ID)
    var id: Int,
    @ColumnInfo(name=COLUMN_MOVIE_TITLE)
    var title: String,
    @ColumnInfo(name=COLUMN_MOVIE_TITLE_ORIGINAL)
    var originalTitle: String,
    @ColumnInfo(name=COLUMN_MOVIE_TAGLINE)
    var tagline: String,
    @ColumnInfo(name=COLUMN_POSTER_PATH)
    var posterPath: String,
    @ColumnInfo(name=COLUMN_BACKDROP_PATH)
    var backdropPath: String,
    @ColumnInfo(name=COLUMN_OVERVIEW)
    var overview: String,
    @ColumnInfo(name=COLUMN_ORIGINAL_LANG)
    var originalLanguage: String,
    @ColumnInfo(name=COLUMN_POPULARITY)
    var popularity: Double,
    @ColumnInfo(name=COLUMN_RELEASE_DATE)
    var releaseDate: String,
    @ColumnInfo(name=COLUMN_HAS_VIDEO)
    var hasVideo: Boolean,
    @ColumnInfo(name=COLUMN_VOTE_AVERAGE)
    var voteAverage: Double,
    @ColumnInfo(name=COLUMN_VOTE_COUNT)
    var voteCount: Int,
    @ColumnInfo(name=COLUMN_BUDGET)
    var budget: Int,
    @ColumnInfo(name=COLUMN_HOMEPAGE)
    var homepage: String,
    @ColumnInfo(name=COLUMN_REVENUE)
    val revenue: Int,
    @ColumnInfo(name=COLUMN_RUNTIME)
    var runtime: Int,
    @ColumnInfo(name=COLUMN_STATUS)
    var status: String,
    @ColumnInfo(name=COLUMN_IS_ADULT)
    var isAdult: Boolean,
    @ColumnInfo(name=COLUMN_IS_FAVORITE)
    var isFavorite : Boolean
)