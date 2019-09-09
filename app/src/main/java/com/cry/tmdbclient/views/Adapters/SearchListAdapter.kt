package com.cry.tmdbclient.views.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cry.tmdbclient.R
import com.cry.tmdbclient.core.utils.Constants
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.viewmodels.BaseMoviesViewModel
import com.cry.tmdbclient.viewmodels.SearchMoviesViewModel
import com.cry.tmdbclient.views.MovieDescActivity

class SearchListAdapter(
    var list: MutableList<Movie>,
    val viewModel : SearchMoviesViewModel
) : RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.listitem_search_result, parent, false)
        return SearchListViewHolder(root, list, viewModel)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        holder.bind(position)
    }

    class SearchListViewHolder (val root : View, val list : MutableList<Movie>, val viewModel : SearchMoviesViewModel) : RecyclerView.ViewHolder(root), View.OnClickListener {
        lateinit var imvPoster : ImageView
        lateinit var txtMovieName : TextView
        lateinit var imvStar1 : ImageView
        lateinit var imvStar2 : ImageView
        lateinit var imvStar3 : ImageView
        lateinit var imvStar4 : ImageView
        lateinit var imvStar5 : ImageView
        lateinit var imgBtnFavorite : ImageButton
        lateinit var txtAddFavorite : TextView
        lateinit var imgBtnWatchList : ImageButton
        lateinit var txtAddWatchList : TextView
        var moviePos = 0

        fun bind(pos : Int) {
            moviePos = pos
            imvPoster = root.findViewById(R.id.imgPoster)
            txtMovieName = root.findViewById(R.id.txtMovieTitle)
            imvStar1 = root.findViewById(R.id.imvStar1)
            imvStar2 = root.findViewById(R.id.imvStar2)
            imvStar3 = root.findViewById(R.id.imvStar3)
            imvStar4 = root.findViewById(R.id.imvStar4)
            imvStar5 = root.findViewById(R.id.imvStar5)
            imgBtnFavorite = root.findViewById(R.id.btnAddFavorite)
            imgBtnWatchList = root.findViewById(R.id.btnAddWatchList)
            txtAddFavorite = root.findViewById(R.id.txtAddFavorite)
            txtAddWatchList = root.findViewById(R.id.txtAddWatchList)

            var ratingIndex = list[pos].voteAverage
            setRatingStars(ratingIndex)

            txtMovieName.text = list[pos].title

            val defaultOptions = RequestOptions()

            Glide.with(root.context)
                .setDefaultRequestOptions(defaultOptions)
                .load(Constants.TMDB_IMG_URL + list[pos].posterPath)
                .into(imvPoster)

            root.setOnClickListener(this)

            setFavoriteDrawable(list[pos].isFavorite)

            imgBtnFavorite.setOnClickListener {
                var mov = list[pos]
                viewModel.toggleFavorite(mov)
                setFavoriteDrawable(mov.isFavorite)
            }
            txtAddFavorite.setOnClickListener {
                var mov = list[pos]
                viewModel.toggleFavorite(mov)
                setFavoriteDrawable(mov.isFavorite)
            }
            imgBtnWatchList.setOnClickListener{
                var mov = list[pos]
                viewModel.addMovieWatchList(mov)
            }
            txtAddWatchList.setOnClickListener{
                var mov = list[pos]
                viewModel.addMovieWatchList(mov)
            }
        }
        override fun onClick(view: View?) {
            var intent = Intent(view!!.context, MovieDescActivity::class.java)
            intent.putExtra("MOVIE_ID", list[moviePos].id)
            view.context.startActivity(intent)
        }
        private fun setRatingStars(rIdx : Double) {
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