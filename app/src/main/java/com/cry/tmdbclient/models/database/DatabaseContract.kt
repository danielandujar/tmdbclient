package com.cry.tmdbclient.models.database

object DatabaseContract {
    const val INT_TRUE = 1
    const val INT_FALSE = 0

    object TableMovie {
        //table
        const val TABLE_NAME = "movie"
        //columns
        const val COLUMN_MOVIE_ID = "movie_id"
        const val COLUMN_MOVIE_TITLE = "movie_title"
        const val COLUMN_MOVIE_TITLE_ORIGINAL = "original_title"
        const val COLUMN_MOVIE_TAGLINE = "tagline"
        const val COLUMN_POSTER_PATH = "poster_path"
        const val COLUMN_BACKDROP_PATH = "backdrop_path"
        const val COLUMN_OVERVIEW = "overview"
        const val COLUMN_ORIGINAL_LANG = "original_languages"
        const val COLUMN_POPULARITY = "popularity"
        const val COLUMN_RELEASE_DATE = "release_date"
        const val COLUMN_HAS_VIDEO = "has_video"
        const val COLUMN_VOTE_AVERAGE = "vote_average"
        const val COLUMN_VOTE_COUNT = "vote_count"
        const val COLUMN_BUDGET = "budget"
        const val COLUMN_HOMEPAGE = "homepage"
        const val COLUMN_REVENUE = "revenue"
        const val COLUMN_RUNTIME = "runtime"
        const val COLUMN_STATUS = "status"
        const val COLUMN_IS_ADULT = "is_adult"
        const val COLUMN_IS_FAVORITE = "is_favorite"

    }
    object TableActor {
        //table
        const val TABLE_NAME = "actor"
        //columns
        const val COLUMN_ACTOR_ID = "actor_id"
        const val COLUMN_ACTOR_NAME = "name"
        const val COLUMN_PROFILE_PATH = "profile_path"
    }
    object TableGenre {
        //Table
        const val TABLE_NAME = "genre"

        //Columns
        const val COLUMN_GENRE_ID = "genre_id"
        const val COLUMN_GENRE_NAME = "name"
    }
    object TableWatchList {
        //Table
        const val TABLE_NAME = "watch_list"
        //Columns
        const val COLUMN_WATCHLIST_ID = "id"
        const val COLUMN_MOVIE_ID = "movie_id"
        const val COLUMN_DATE_ADDED = "date_added"
        const val COLUMN_IS_REMOVED = "is_removed"
    }
}