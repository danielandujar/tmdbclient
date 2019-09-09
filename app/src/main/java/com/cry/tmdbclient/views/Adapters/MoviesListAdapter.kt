package com.cry.tmdbclient.views.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cry.tmdbclient.R
import com.cry.tmdbclient.core.utils.Constants.TMDB_IMG_URL
import com.cry.tmdbclient.models.api.tmdb.obj.discover.Result
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.viewmodels.BaseMoviesViewModel
import com.cry.tmdbclient.views.MovieDescActivity

class MoviesListAdapter(
    var list: MutableList<Movie>,
    val viewModel : BaseMoviesViewModel
) : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(root, list, viewModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(position)
    }

    class MovieViewHolder (val root : View, val list : MutableList<Movie>, val viewModel : BaseMoviesViewModel) : RecyclerView.ViewHolder(root), View.OnClickListener
    {
        private lateinit var imvPoster : ImageView
        private lateinit var txtMovieName : TextView
        private lateinit var imvStar1 : ImageView
        private lateinit var imvStar2 : ImageView
        private lateinit var imvStar3 : ImageView
        private lateinit var imvStar4 : ImageView
        private lateinit var imvStar5 : ImageView
        private lateinit var imgBtnFavorite : ImageButton
        private lateinit var imgBtnAddWatchList : ImageButton
        var moviePos = 0

        fun bind(pos : Int)
        {
            moviePos = pos
            imvPoster = root.findViewById(R.id.imgPoster)
            txtMovieName = root.findViewById(R.id.txtMovieName)
            imvStar1 = root.findViewById(R.id.star1)
            imvStar2 = root.findViewById(R.id.star2)
            imvStar3 = root.findViewById(R.id.star3)
            imvStar4 = root.findViewById(R.id.star4)
            imvStar5 = root.findViewById(R.id.star5)
            imgBtnFavorite = root.findViewById(R.id.btnFavorite)
            imgBtnAddWatchList = root.findViewById(R.id.btnAddWatchList)

            var ratingIndex = list[pos].voteAverage
            setRatingStars(ratingIndex)

            setFavoriteDrawable(list[pos].isFavorite)

            imgBtnFavorite.setOnClickListener {
                var mov = list[pos]
                viewModel.toggleFavorite(mov)
                setFavoriteDrawable(mov.isFavorite)
            }
            imgBtnAddWatchList.setOnClickListener{
                var mov = list[pos]
                viewModel.addMovieWatchList(mov)
            }

            txtMovieName.text = list[pos].title

            val defaultOptions = RequestOptions()

            Glide.with(root.context)
                .setDefaultRequestOptions(defaultOptions)
                .load(TMDB_IMG_URL + list[pos].posterPath)
                .into(imvPoster)

            root.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            var intent = Intent(view!!.context, MovieDescActivity::class.java)
            intent.putExtra("MOVIE_ID", list[moviePos].id)
            view.context.startActivity(intent)
        }
        private fun setRatingStars(rIdx : Double)
        {
            //1st Star
            if (rIdx > 1.0) {
                imvStar1.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_24dp))
            }
            else if (rIdx > 0) {
                imvStar1.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_half_24dp))
            }

            //2nd Star
            if (rIdx > 2.75) {
                imvStar2.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_24dp))
            }
            else if (rIdx > 1.75) {
                imvStar2.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_half_24dp))
            }

            //3rd Star
            if (rIdx > 4.75) {
                imvStar3.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_24dp))
            }
            else if (rIdx > 3.75) {
                imvStar3.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_half_24dp))
            }

            //4th Star
            if (rIdx > 6.75) {
                imvStar4.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_24dp))
            }
            else if (rIdx > 5.75) {
                imvStar4.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_half_24dp))
            }

            //5th Star
            if (rIdx > 9) {
                imvStar5.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_24dp))
            }
            else if (rIdx > 7.75) {
                imvStar5.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_star_half_24dp))
            }
        }
        private fun setFavoriteDrawable(favorite : Boolean) {
            if (favorite)
                imgBtnFavorite.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_favorite_filled_24dp))
            else
                imgBtnFavorite.setImageDrawable(root.context.resources.getDrawable(R.drawable.ic_favorite_outline_24dp))
        }
    }
}