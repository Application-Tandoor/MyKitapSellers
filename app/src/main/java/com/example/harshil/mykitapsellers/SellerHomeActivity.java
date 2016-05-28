package com.example.harshil.mykitapsellers;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;

public class SellerHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = "SellerHomeActivity";
    public ActionBarDrawerToggle toggle;

    @Bind(R.id.bookName) TextView _bookName;
    @Bind(R.id.isbnText) EditText _isbnText;

    @Bind(R.id.priceText) EditText _priceText;
    @Bind(R.id.quantText) EditText _quantityText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Setting UpdateStockkFrag as default fragment...
        Fragment squadFragment = new UpdateStockFrag();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flContent,squadFragment,null);
        fragmentTransaction.commit();

//        MenuItem item = (MenuItem) findViewById(R.id.nav_updateStock);
//        item.setChecked(true);

        setTitle("Update Stock");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //implementing navigation drawer toggle...
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        toggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.seller_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Log.e(TAG, "__________onNavigationItemSelected__________");

        int id = item.getItemId();
        Log.e("This is Menu item 1 "+item ,"");
        Log.e("This is id 1 "+id ,"");
        Fragment fragment=null;
        Class fragClass;

        switch (id){
            case R.id.nav_manageStock :
                fragClass=ManageStockFrag.class;
                break;
            case R.id.nav_myCash :
                fragClass=MyCashFrag.class;
                break;
//            case R.id.nav_accounting :
//                fragClass=MyCashActivity.class;
//                break;
//            case R.id.nav_sellerHome :
//                fragClass=AccountingActivity.class;
//                break;
            default:
                fragClass=UpdateStockFrag.class;
               // setTitle("Seller Home");
                Log.e("This is Menu item 2 "+item ,"");
                Log.e("This is id 2 "+id ,"");
        }

        try {
            fragment=(Fragment)fragClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        if(fragment!=null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
//        if(item.getTitle()!=null)   setTitle(item.getTitle());
        setTitle(item.getTitle());

        // Close the navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //methods for fragment
    // UpdateStockFrag...

    public void addBClicked(View v){

        //perform necessary validation...
        int price=Integer.parseInt(_priceText.getText().toString());
        int quantity=Integer.parseInt(_quantityText.getText().toString());

        if(quantity==0){
            _quantityText.setError("Quantity can't be zero");
            return;
        }
        if(_isbnText.length()<13){
            _quantityText.setError("ISBN must be 13 charaacers long");
            return;
        }

        _bookName.setText(getResources().getString(R.string.bookNameStr));
        _priceText.setText(getResources().getString(R.string.priceStr));
        _quantityText.setText(getResources().getString(R.string.quantityStr));

        //add book details to corressponding objeccts, and stockList...
//
//
//
    }

    public void cancelBClicked(View v){

        //reset price & quantity inputs...
        _priceText.setText(getResources().getString(R.string.priceStr));
        _quantityText.setText(getResources().getString(R.string.quantityStr));
    }

    public void resetBClicked(View v){

        //reset all book details...
        _bookName.setText(getResources().getString(R.string.bookNameStr));
        _isbnText.setText("");
        _priceText.setText(getResources().getString(R.string.priceStr));
        _quantityText.setText(getResources().getString(R.string.quantityStr));
    }

    public void fabBClicked(View v){

        //inflate the UpdateListFrag fragment to enable user review its stock updates...
        Fragment fragment = (Fragment) UpdateListFrag.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }

    public void newBookClicked(View v){

        //inflate the UpdateListFrag fragment to enable user review its stock updates...
        Fragment fragment = (Fragment) UpdateStockFrag.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
    }


}
