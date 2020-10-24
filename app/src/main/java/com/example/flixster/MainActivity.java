package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.Movie;
import adapters.MovieAdapater;
import okhttp3.Headers;

public class MainActivity extends AppCompatActivity
{
    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    // we want tag to easily log data
    public static final String TAG = "MainActivity";
    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();
        // Create the adapter
       final MovieAdapater movieAdapater =  new MovieAdapater(this, movies);

        // Set the adapter on the recycler View
        rvMovies.setAdapter(movieAdapater);
        //set a Layout Manager
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        AsyncHttpClient client = new AsyncHttpClient();
        // the reason we are using JsonHttpResponseHandler() is because the music api speaks JSON
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                //we want tag to easily log data that's why we have tag here
                Log.d(TAG, "onSuccess");
                // data is in json object, we know this because if we go back to json data there is a result
                JSONObject jsonObject = json.jsonObject;
                // we say json array because corresponding value is an array
                try {
                    // this json array is going to be turned into a list of movies
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG, "results: " + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapater.notifyDataSetChanged();
                    Log.i(TAG, "Movies: " + movies.size());
                    // this will return a list of movie objects
                   // List<Movie> movies = Movie.fromJsonArray(results);
                }
                catch (JSONException e)
                {
                    Log.e(TAG, "Hit json exception", e);}

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String s, Throwable throwable)
            {
                //we want tag to easily log data that's why we have tag here
                Log.d(TAG, "onFailure");

            }
        });
    }
}