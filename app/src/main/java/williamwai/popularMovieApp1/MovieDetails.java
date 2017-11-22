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
    TextView tv_Name;
    TextView tv_Overview;
    TextView tv_Rating;
    TextView tv_Release;
    String poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

        imageView = (ImageView) findViewById(R.id.movie_image);
        tv_Name = (TextView) findViewById(R.id.movie_name);
        tv_Overview = (TextView) findViewById(R.id.movie_overview);
        tv_Rating = (TextView) findViewById(R.id.movie_rating);
        tv_Release = (TextView) findViewById(R.id.movie_release);


        //imageView.setImageResource(getIntent().getIntExtra("img_id", 00));



        tv_Name.setText("Title : " + getIntent().getStringExtra("name"));
        tv_Overview.setText("Plot synopsis :" + getIntent().getStringExtra("overview"));
        tv_Rating.setText("Vote Average :" + getIntent().getStringExtra("vote_average"));
        tv_Release.setText("Release Date :" + getIntent().getStringExtra("release_date"));


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
