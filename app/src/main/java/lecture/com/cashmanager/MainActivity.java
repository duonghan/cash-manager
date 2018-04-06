package lecture.com.cashmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import lecture.com.cashmanager.menu.AboutFragment;
import lecture.com.cashmanager.menu.CategoryFragment;
import lecture.com.cashmanager.menu.DebtFragment;
import lecture.com.cashmanager.menu.ReportFragment;
import lecture.com.cashmanager.menu.SettingFragment;
import lecture.com.cashmanager.menu.TransasctionsFragment;
import lecture.com.cashmanager.model.Account;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the fragment initially
        TransasctionsFragment mainfragment = new TransasctionsFragment();
        initFragment(mainfragment);


        displayHomePage();
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("user");
    }

    private void initFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_content_main, fragment);
        transaction.commit();
    }

    private void displayHomePage() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Action Button handle
        handleFab();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void handleFab(){
        final FloatingActionButton fabIncome = (FloatingActionButton)findViewById(R.id.fab_income);
        final FloatingActionButton fabExpense = (FloatingActionButton)findViewById(R.id.fab_expense);

        fabIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fabExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_change_password) {
            return true;
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("username", account.getUsername());

            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_transactions) {
            TransasctionsFragment mainfragment = new TransasctionsFragment();
            initFragment(mainfragment);
        } else if (id == R.id.nav_debts) {
            DebtFragment mainfragment = new DebtFragment();
            initFragment(mainfragment);
        } else if (id == R.id.nav_report) {
            ReportFragment mainfragment = new ReportFragment();
            initFragment(mainfragment);
        } else if (id == R.id.nav_categories) {
            CategoryFragment mainfragment = new CategoryFragment();
            initFragment(mainfragment);
        } else if (id == R.id.nav_about) {
            AboutFragment mainfragment = new AboutFragment();
            initFragment(mainfragment);
        } else if (id == R.id.nav_setting) {
            SettingFragment mainfragment = new SettingFragment();
            initFragment(mainfragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
