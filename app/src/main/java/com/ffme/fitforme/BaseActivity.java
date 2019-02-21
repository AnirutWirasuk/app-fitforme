package com.ffme.fitforme;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ffme.fitforme.adapter.LeftMenuAdapter;

public class BaseActivity extends AppCompatActivity {
    private static final int SUBSCRIBE_VIEW = 3;
    public boolean menuclicked = false;
    private String resultURL;

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
            "ข่าวสาร",
            "เกร็ดความรู้",
            "การกายภาพบำบัด",
            "ข้อมูลส่วนตัว",
            "คณะผู้จัดทำ",
            "ออกจากระบบ"
    };

    Integer[] imgid = {
            R.drawable.ic_lm_lists,
            R.drawable.ic_lm_cards,
            R.drawable.ic_lm_parallax,
            R.drawable.ic_lm_tabs,
            R.drawable.ic_lm_onboarding_wizards,
            R.drawable.ic_lm_login_register
    };

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerLayout.addDrawerListener(actionBarDrawerToggle);


        ImageView myImage = (ImageView) findViewById(R.id.profile_image);

        LeftMenuAdapter adapter = new LeftMenuAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list);
        LayoutInflater myinflater = getLayoutInflater();
        ViewGroup myHeader = (ViewGroup) myinflater.inflate(R.layout.nav_header_main, list, false);



//        String fileNameString = "test";
//        String key1 = "key1";
//
//        SharedPreferences sharedPref = getSharedPreferences(fileNameString, MODE_PRIVATE);
//        int highScore = sharedPref.getInt(key1, 0);

//        String text = "fragment" + Integer.toString(highScore);
//
//        TextView myAwesomeTextView = (TextView)myHeader.findViewById(R.id.textBuyNow);
//        myAwesomeTextView.setText(text);
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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);

    }

    protected void selectItem(int position, int way) {
        menuclicked = true;
        String title;
        toolbartitle = (TextView) findViewById(R.id.toolbar_title);

        switch (position) {
            case 0:
                title = "ข่าวสาร";
                resultURL = "http://fitme.pe.hu/main/news";
                break;
            case 1:
                title = "เกร็ดความรู้";
                resultURL = "http://fitme.pe.hu/main/knowledge";
                break;
            case 2:
                title = "การกายภาพบำบัด";
                resultURL = "http://fitme.pe.hu/main/physicaltherapy";
                break;
            case 3:
                title = "ข้อมูลส่วนตัว";
                break;
            case 4:
                title = "คณะผู้จัดทำ";
                resultURL = "http://fitme.pe.hu/main/team";
                break;
            case 5:
                title = "ออกจากระบบ";
                break;
            default:
                title = "ข่าวสาร";
                resultURL = "http://fitme.pe.hu/main/news";
                break;
        }

        if(position == 5){
            String fileNameString = "dataSystem";
            String loginStatus = "login";

            SharedPreferences sharedPref = getSharedPreferences(fileNameString, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(loginStatus, 0);
            editor.commit();

            Intent i = new Intent(BaseActivity.this, ValidationLogin.class);
            startActivity(i);
            finish();

        }else if(position == 3){
            Intent i = new Intent(BaseActivity.this, UpdateProfile.class);
            i.putExtra("title", title);
            startActivity(i);
            finish();
        }else{
            toolbartitle.setText(title);
            Intent i = new Intent(BaseActivity.this, MainActivity.class);
            i.putExtra("resultURL", resultURL);
            i.putExtra("title", title);
            startActivity(i);
            finish();
//            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//            fab.setVisibility(View.GONE);
//            viewCount();
//            this.currentselection = Integer.toString(position);
//
//
//            String fragmenttag = "fragment" + Integer.toString(position);
//
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            Fragment fragment;
//            fragment = MenuHomeFragment.newInstance(itemname[position]);
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame, fragment, fragmenttag)
//                    .addToBackStack(null)
//                    .commit();
//
//            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//            drawer.closeDrawer(GravityCompat.START);
//            if (way == 1) {
//
//                this.leftmenushow = 1;
//                this.homeselected = 0;
//
//            } else {
//                this.leftmenushown = 0;
//                this.leftmenushow = 0;
//                this.homeselected = 0;
//            }
        }



    }

    @Override
    public void onBackPressed() {

        toolbar.getMenu().setGroupVisible(R.id.main_menu_group, false);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
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


        if (currentactivity.equals("ListsExpandable") || currentactivity.equals("ListsDraggable") || currentactivity.equals("ListSwipeableWithButton") || currentactivity.equals("ListsExpandableDraggableSwipeable") || currentactivity.equals("ListItemDetails")
                || currentactivity.equals("ListItemDetailsSingle")) {
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
                selectItem(0, 1);
            } else {

                selectItem(5, 0);
                this.homeselected = 1;

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