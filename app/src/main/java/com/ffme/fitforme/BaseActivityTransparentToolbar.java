package com.ffme.fitforme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ffme.fitforme.adapter.LeftMenuAdapter;

public class BaseActivityTransparentToolbar extends AppCompatActivity {
    private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";
    private static final int SUBSCRIBE_VIEW = 3;
    public int orientation = 0;
    public boolean menuclicked = false;


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    TextView toolbartitle;
    String currentselection;

    int homeselected = 0;
    int leftmenushown = 0;
    int leftmenushow = 0;


    ListView list;
    String[] itemname = {
            "LISTS",
            "CARDS",
            "PARALLAX",
            "TABS",
            "ONBOARDING / WIZARDS",
            "LOGIN / REGISTER",
            "SEARCH",
            "IMAGE GALLERY",
            "DIALOGS",
            "SMALL COMPONENTS",
            "SPLASH"

    };

    Integer[] imgid = {
            R.drawable.ic_lm_lists,
            R.drawable.ic_lm_cards,
            R.drawable.ic_lm_parallax,
            R.drawable.ic_lm_tabs,
            R.drawable.ic_lm_onboarding_wizards,
            R.drawable.ic_lm_login_register,
            R.drawable.ic_lm_search,
            R.drawable.ic_lm_image_gallery,
            R.drawable.ic_lm_dialogs,
            R.drawable.ic_lm_small_components,
            R.drawable.ic_lm_splash


    };
    private AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_page_transparent_toolbar);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        setSupportActionBar(toolbar);

        toolbar.getBackground().setAlpha(0);

        appbar = (AppBarLayout) findViewById(R.id.app_bar_transparent);
        appbar.getBackground().setAlpha(0);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        LeftMenuAdapter adapter = new LeftMenuAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list);
        LayoutInflater myinflater = getLayoutInflater();
        ViewGroup myHeader = (ViewGroup) myinflater.inflate(R.layout.nav_header_main, list, false);
        list.addHeaderView(myHeader, null, false);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            selectItem(position - 1, 0);


                                        }
                                    }

        );

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
               /* switch (item.getItemId()) {

                    case R.id.menu_home:
                        Intent anIntent = new Intent(getApplicationContext(), TheClassYouWantToLoad.class);
                        startActivity(loadPlayer);
                        drawerLayout.closeDrawers();
                        break;

                }*/
                return false;
            }
        });

    }

    private void selectItem(int position, int way) {
        menuclicked = true;
        orientation = 0;
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        contentFrameLayout.removeAllViews();
       // float scale = getResources().getDisplayMetrics().density;
       // int dpAsPixels = (int) (230 * scale + 0.5f);
        contentFrameLayout.setPadding(0, 230, 0, 0);
        toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        Drawable toolback = getDrawable(R.drawable.side_nav_bar);
        toolbar.setBackground(toolback);
        toolbar.getBackground().setAlpha(255);
        toolbar.setElevation(8);
       // FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
        contentFrameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar_transparent);
        appbar.setBackground(toolback);
        appbar.setElevation(8);


        String title;
        toolbartitle = (TextView) findViewById(R.id.toolbar_title);

        switch (position) {
            case 0:
                title = "LISTS";
                break;
            case 1:
                title = "CARDS";
                break;
            case 2:
                title = "PARALLAX";
                break;
            case 3:
                title = "TABS";
                break;
            case 4:
                title = "ONBOARDING / WIZARDS";
                break;
            case 5:
                title = "LOGIN / REGISTER";
                break;
            case 6:
                title = "SEARCH";
                break;
            case 7:
                title = "IMAGE GALERY";
                break;
            case 8:
                title = "DIALOGS";
                break;
            case 9:
                title = "SMALL COMPONETS";
                break;
            case 10:
                title = "SPLASH";
                break;

            default:
                title = "LISTS";
                break;
        }
        toolbartitle.setText(title);


        viewCount();
        this.currentselection = Integer.toString(position);


        String fragmenttag = "fragment" + Integer.toString(position);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        fragment = MenuHomeFragment.newInstance(itemname[position]);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment, fragmenttag)
                .addToBackStack(null)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (way == 1) {

            this.leftmenushow = 1;
            this.homeselected = 0;

        } else {
            this.leftmenushown = 0;
            this.leftmenushow = 0;
            this.homeselected = 0;
        }


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {


        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbartitle = (TextView) findViewById(R.id.toolbar_title);

            if (orientation != 1) {
                // setContentView(R.layout.empty_page_transparent_toolbar);
                FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
                contentFrameLayout.setPadding(0, 230, 0, 0);
                toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
                Drawable toolback = getDrawable(R.drawable.side_nav_bar);

                toolbar.setBackground(toolback);
                toolbar.getBackground().setAlpha(255);
                toolbar.setElevation(8);
                //FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
                contentFrameLayout.setLayoutParams(new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
                AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar_transparent);
                appbar.setBackground(toolback);
                appbar.setElevation(8);


            }


            // Toast.makeText(this, "landscape11", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            toolbartitle = (TextView) findViewById(R.id.toolbar_title);


            if (orientation != 1) {
                // setContentView(R.layout.empty_page_transparent_toolbar);
                FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
                contentFrameLayout.setPadding(0, 230, 0, 0);
                toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
                Drawable toolback = getDrawable(R.drawable.side_nav_bar);

                toolbar.setBackground(toolback);
                toolbar.getBackground().setAlpha(255);
                toolbar.setElevation(8);
                FrameLayout content = (FrameLayout) findViewById(R.id.content_frame);
                content.setLayoutParams(new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
                AppBarLayout appbar = (AppBarLayout) findViewById(R.id.app_bar_transparent);
                appbar.setBackground(toolback);
                appbar.setElevation(8);


            }


            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {

        String currentactivity = this.getClass().getSimpleName();

        if (leftmenushown == 1) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                this.finishAffinity();
                return;
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        }
        if (leftmenushow == 1) {
            this.leftmenushown = 1;
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);
            return;

        }


        if (currentactivity.equals("ListsExpandable") || currentactivity.equals("ListsDraggable") || currentactivity.equals("ListSwipeableWithButton") || currentactivity.equals("ListsExpandableDraggableSwipeable")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(0, 0);

                this.homeselected = 1;

            }
        } else if (currentactivity.equals("SearchExample")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(6, 0);
                this.homeselected = 1;

            }
        } else if (currentactivity.equals("SplashScreensActivity1") || currentactivity.equals("SplashScreensActivity2")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(10, 0);
                this.homeselected = 1;

            }
        } else if (currentactivity.equals("OnboardingWizzards")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(4, 0);
                this.homeselected = 1;

            }
        } else if (currentactivity.equals("DialogInfo") || currentactivity.equals("DialogSubscribe") || currentactivity.equals("DialogWarning")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(8, 0);
                this.homeselected = 1;

            }
        } else if (currentactivity.equals("TabsLineIndicator") || currentactivity.equals("TabsRoundIndicator")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(3, 0);
                this.homeselected = 1;

            }
        } else if (currentactivity.equals("ValidationLogin") || currentactivity.equals("ValidationForgot") || currentactivity.equals("ValidationRegister")) {
            if (this.homeselected == 1) {
//                selectItem(0, 1);
            } else {

//                selectItem(5, 0);
//                this.homeselected = 1;

            }
        } else if (currentactivity.equals("CardsRate") || currentactivity.equals("CardsFollow") || currentactivity.equals("CardsProfile")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(1, 0);
                this.homeselected = 1;
            }


        } else if (currentactivity.equals("GalleryCategories")) {

            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(7, 0);
                this.homeselected = 1;

            }
        } else if (currentactivity.equals("SmallComponentsRadio") || currentactivity.equals("SmallComponentsCheck") || currentactivity.equals("SmallComponentsProgress") || currentactivity.equals("SmallComponentsSeek") || currentactivity.equals("SmallComponentsDropDown")) {
            if (this.homeselected == 1) {
                selectItem(0, 1);
            } else {

                selectItem(9, 0);
                this.homeselected = 1;

            }
        } else {

            selectItem(0, 1);
            this.leftmenushown = 1;
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.openDrawer(GravityCompat.START);

            this.leftmenushow = 1;
            this.homeselected = 1;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    public void subscribeDialog() {

        SharedPreferences subscribe = getApplicationContext().getSharedPreferences("SUBSCRIBE", MODE_PRIVATE);
        int subscribeStatus = subscribe.getInt("subscribed", 0);
        if (subscribeStatus == 0) {

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), DialogSubscribeMailChimp.class);
                    startActivity(intent);
                }
            }, 2000);
        }

    }

    public void viewCount() {
        SharedPreferences subscribe = getApplicationContext().getSharedPreferences("SUBSCRIBE", MODE_PRIVATE);
        int subscribeStatus = subscribe.getInt("subscribed", 0);
        if (subscribeStatus == 0) {
            int viewNumber = subscribe.getInt("numOfViews", 1);
            if (viewNumber == SUBSCRIBE_VIEW) {
                subscribeDialog();
            }
            SharedPreferences.Editor editor = subscribe.edit();
            editor.putInt("numOfViews", viewNumber + 1);
            editor.apply();
        }

    }

    public void buy(View v) {
        String url = "http://csform.com/documentation-for-matta-ui-template-app-for-android/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void documentation(View v) {
        String url = "http://csform.com/documentation-for-matta-ui-template-app-for-android/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


}