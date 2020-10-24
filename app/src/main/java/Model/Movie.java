package Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// this is a plain old java object which encapsulates the idea of a movie
public class Movie
{
    String posterPath;
    String title;
    String overview;

    public Movie(JSONObject jsonObject) throws JSONException
    { // make sure you copy the right methods from the json
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i< movieJsonArray.length(); i++)
        {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));

        }

        return movies;
    }
// now we can easily get the title
    // currently the posterpath only prints a string we want a full url
    public String getPosterPath() {
        // %s is just saying here is what i want to replace with posterpath
        return String.format("https://image.tmdb.org/t/p/w342" +
                "%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
