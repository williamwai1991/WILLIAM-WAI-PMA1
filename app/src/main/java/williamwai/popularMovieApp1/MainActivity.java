package williamwai.popularMovieApp1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import williamwai.sc_recyclerviewproject.R;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String API_KEY = "";
    private static String URL_DATA = null;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;
    static boolean sortByPop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        listItems = new ArrayList<>();


    }


    public void loadRecyclerViewData(String sortBy) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data....");
        progressDialog.show();

        if (!sortByPop) {
            URL_DATA = "http://api.themoviedb.org/3/movie/" + sortBy + "?api_key=" + API_KEY;


            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    URL_DATA,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(s);
                                JSONArray array = jsonObject.getJSONArray("results");

                                listItems.clear();

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject o = array.getJSONObject(i);
                                    ListItem item = new ListItem(
                                            o.getString("title"),
                                            o.getString("overview"),
                                            o.getString("vote_average"),
                                            o.getString("release_date"),
                                            o.getString("poster_path")
                                    );
                                    listItems.add(item);
                                }

                                adapter = new MyAdapter(listItems, getApplicationContext());
                                recyclerView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String sortBy = null;
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        switch (item.getItemId()) {
            case R.id.menu_pop:
                sortBy = "popular";

                editor.putString("SORT_CRITERIA", "popular");
                editor.commit();
                loadRecyclerViewData(sortBy);
                return true;
            case R.id.menu_top:
                sortBy = "top_rated";
                editor.putString("SORT_CRITERIA", "top_rated");
                editor.commit();
                loadRecyclerViewData(sortBy);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

        String sortBy = sharedPreferences.getString("SORT_BY", "popular");
        loadRecyclerViewData(sortBy);
    }


    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String sortBy = sharedPref.getString("SORT_BY", "popular");
        if (listItems.isEmpty()) {
            loadRecyclerViewData(sortBy);
        }
    }
}
