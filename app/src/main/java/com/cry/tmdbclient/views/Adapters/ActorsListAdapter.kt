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
import com.cry.tmdbclient.models.database.obj.Actor
import com.cry.tmdbclient.models.database.obj.Movie
import com.cry.tmdbclient.viewmodels.BaseMoviesViewModel
import com.cry.tmdbclient.viewmodels.MovieDescViewModel
import com.cry.tmdbclient.views.MovieDescActivity

class ActorsListAdapter (
    var list: MutableList<Actor>,
    val viewModel : MovieDescViewModel
) : RecyclerView.Adapter<ActorsListAdapter.ActorViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.actor_listitem, parent, false)
        return ActorViewHolder(root, list)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(position)
    }

    class ActorViewHolder (val root : View, val list : MutableList<Actor>) : RecyclerView.ViewHolder(root)
    {
        private lateinit var imvProfile : ImageView
        private lateinit var txtActorName : TextView

        fun bind(pos : Int)
        {
            imvProfile = root.findViewById(R.id.imgProfile)
            txtActorName = root.findViewById(R.id.txtActorName)

            txtActorName.text = list[pos].name

            val defaultOptions = RequestOptions()
                .error(R.drawable.ic_account_circle_black_24dp)

            Glide.with(root.context)
                .setDefaultRequestOptions(defaultOptions)
                .load(Constants.TMDB_IMG_URL + list[pos].profile_path)
                .into(imvProfile)
        }
    }
}