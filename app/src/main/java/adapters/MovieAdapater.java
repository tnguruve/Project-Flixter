package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;

import java.util.List;
import Model.Movie;

import static android.media.CamcorderProfile.get;

public class MovieAdapater extends RecyclerView.Adapter<MovieAdapater.ViewHolder>
{
    Context context;
    List<Movie> movies;

    public MovieAdapater(Context context, List<Movie> movies)
    {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    // this inflates the layout and return it inside the viewHolder
    public MovieAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View movieView =  LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    // involves populating data into item through holder
    public void onBindViewHolder(@NonNull MovieAdapater.ViewHolder holder, int position)
    {
      // get the movie at the passed in position
       Movie movie = movies.get(position);
        // bind movie data
        holder.bind(movie);
    }
    // return total counts of items in the lists
    @Override
    public int getItemCount()
    {
        return movies.size();
    }


  public class ViewHolder extends RecyclerView.ViewHolder
  {
    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;


    public ViewHolder(@NonNull View itemView)
    {
        super(itemView);
        // define viewHolder
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvOverview = itemView.findViewById(R.id.tvOverview);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

      public void bind(Movie movie)
      {
          tvTitle.setText(movie.getTitle());
          tvOverview.setText(movie.getOverview());
          Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
      }
  }
}
