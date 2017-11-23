package williamwai.popularMovieApp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import williamwai.sc_recyclerviewproject.R;

public class MovieDetails extends AppCompatActivity {

    ImageView imageView;
    TextView tvName;
    TextView tvOverview;
    TextView tvRating;
    TextView tvRelease;
    String poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        imageView = (ImageView) findViewById(R.id.movieImage);
        tvName = (TextView) findViewById(R.id.movieName);
        tvOverview = (TextView) findViewById(R.id.movieOverview);
        tvRating = (TextView) findViewById(R.id.movieRating);
        tvRelease = (TextView) findViewById(R.id.movieRelease);


        //imageView.setImageResource(getIntent().getIntExtra("img_id", 00));



        tvName.setText("Title : " + getIntent().getStringExtra("name"));
        tvOverview.setText("Plot synopsis :" + getIntent().getStringExtra("overview"));
        tvRating.setText("Vote Average :" + getIntent().getStringExtra("vote_average"));
        tvRelease.setText("Release Date :" + getIntent().getStringExtra("release_date"));


        poster = getIntent().getStringExtra("img_id");

        Picasso.with(getApplicationContext()).load("http://image.tmdb.org/t/p/w780/" + poster).into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
