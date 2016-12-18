package com.meajireview.meajireview_android.activity;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.meajireview.meajireview_android.R;
import com.meajireview.meajireview_android.adapter.ShopListAdapter;
import com.meajireview.meajireview_android.item.ShopInfo;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by songmho on 2016-10-09.
 */
public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.toolBar) Toolbar toolbar;
    @BindView(R.id.txtNothing) TextView txtNothing;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.prograssBar) ProgressBar prograssBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initRecyclerView();
        initToolbar();

    }

    /**
     * RecyclerView 초기화 메소드<br>
     */
    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * List 생성 메소드 <br>
     */
    private void makeList(final String shop) {
        prograssBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Shop");
                        query.whereEqualTo("name",shop);
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                ArrayList<ShopInfo> shopInfos = new ArrayList<>();
                                for(ParseObject o:list)
                                  shopInfos.add(new ShopInfo(o.getInt("shop_id"),o.getString("name"), o.getString("phone"), "",o.getString("open")+" ~ "+o.getString("close")));

                                recyclerView.setAdapter(new ShopListAdapter(getApplicationContext(), shopInfos));
                            }
                        });

                        prograssBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();

    }

    /**
     * Toolbar초기화 메소드 <br>
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager)SearchActivity.this.getSystemService(SEARCH_SERVICE);
        SearchView searchView = null;
        if(searchItem!=null)
            searchView = (SearchView) searchItem.getActionView();
        if(searchView!=null){
            searchView.setIconifiedByDefault(false);
            searchView.requestFocus();
            searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            searchView.setQueryHint("검색어를 입력해주세요.");
            searchView.setSearchableInfo(searchManager.getSearchableInfo(SearchActivity.this.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    makeList(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.home){
            finish();
            overridePendingTransition(0,0);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(0,0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
