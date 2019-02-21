package com.ffme.fitforme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * Sample fragment to demonstrate the instantiation of fragments with arguments
 * <p>
 * Created by Günhan on 28.10.2015.
 */
public class MenuHomeFragment extends Fragment {

    private String selection;
    private String[] classname;
    private SwipeRefreshLayout mySwipeRefreshLayout;


    public static MenuHomeFragment newInstance(String name) {

        Bundle bundle = new Bundle();
        bundle.putString("selection", name);
        MenuHomeFragment fragment = new MenuHomeFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            selection = bundle.getString("selection");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();
        View view = inflater.inflate(R.layout.list_menu, container, false);
        setRetainInstance(true);
        readBundle(getArguments());
        String[] itemname;
        Integer[] imgid;


        switch (selection) {
            case "LISTS":
                itemname = new String[]{


                        "LIST VIEWS EXPANDABLE",
                        "LIST VIEWS DRAG & DROP",
                        "LIST VIEWS SWIPE",
                        "LIST VIEWS SWIPE & DRAG & EXPAND",
                        "LIST ITEM DETAILS"

                };
                imgid = new Integer[]{


                        R.drawable.ic_svg_list_expandable,
                        R.drawable.ic_svg_list_drag_drop,
                        R.drawable.ic_svg_list_swipe,
                        R.drawable.ic_svg_list_swipe_drag_expand,
                        R.drawable.ic_svg_list_item_details

                };
                classname = new String[]{


                        "com.csform.android.uiapptemplate2.ListsExpandable",
                        "com.csform.android.uiapptemplate2.ListsDraggable",
                        "com.csform.android.uiapptemplate2.ListSwipeableWithButton",
                        "com.csform.android.uiapptemplate2.ListsExpandableDraggableSwipeable",
                        "com.csform.android.uiapptemplate2.ListItemDetails",

                };
                break;
            case "SEARCH":
                itemname = new String[]{
                        "SEARCH"
                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_search


                };

                classname = new String[]{
                        "com.csform.android.uiapptemplate2.SearchExample"
                };
                break;
            case "SPLASH":
                itemname = new String[]{
                        "SPLASH 1",
                        "SPLASH 2"
                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_splash_1,
                        R.drawable.ic_svg_splash_2


                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.SplashScreensActivity1",
                        "com.csform.android.uiapptemplate2.SplashScreensActivity2"
                };

                break;
            case "PARALLAX":
                itemname = new String[]{
                        "HEADER",
                        "CARDS OVER HEADER"
                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_parallax_header,
                        R.drawable.ic_svg_parallax_cards_over_header

                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.ParallaxHeader",
                        "com.csform.android.uiapptemplate2.CardsOverHeader"
                };
                break;
            case "ONBOARDING / WIZARDS":
                itemname = new String[]{
                        "ONBOARDING / WIZARDS"
                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_onboarding


                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.OnboardingWizzards"
                };

                break;
            case "DIALOGS":
                itemname = new String[]{
                        "INFO",
                        "SUBSCRIBE",
                        "WARNING"
                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_dialog_info,
                        R.drawable.ic_svg_dialog_subscribe,
                        R.drawable.ic_svg_dialog_warning

                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.DialogInfo",
                        "com.csform.android.uiapptemplate2.DialogSubscribe",
                        "com.csform.android.uiapptemplate2.DialogWarning"
                };

                break;
            case "TABS":
                itemname = new String[]{
                        "LINE INDICATOR",
                        "ROUND INDICATOR"

                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_tabs_line,
                        R.drawable.ic_svg_tabs_round,
                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.TabsLineIndicator",
                        "com.csform.android.uiapptemplate2.TabsRoundIndicator"


                };
                break;
            case "LOGIN / REGISTER":
                itemname = new String[]{
                        "LOGIN",
                        "FORGOT PASSWORD",
                        "REGISTER"
                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_login,
                        R.drawable.ic_svg_forgot_password,
                        R.drawable.ic_svg_register

                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.ValidationLogin",
                        "com.csform.android.uiapptemplate2.ValidationForgot",
                        "com.csform.android.uiapptemplate2.ValidationRegister"
                };
                break;
            case "CARDS":
                itemname = new String[]{
                        "RATE",
                        "FOLLOW",
                        "PROFILE"

                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_cards_rate,
                        R.drawable.ic_svg_cards_follow,
                        R.drawable.ic_svg_cards_profile

                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.CardsRate",
                        "com.csform.android.uiapptemplate2.CardsFollow",
                        "com.csform.android.uiapptemplate2.CardsProfile"
                };
                break;
            case "IMAGE GALLERY":
                itemname = new String[]{

                        "CATEGORIES"

                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_gallery


                };
                classname = new String[]{

                        "com.csform.android.uiapptemplate2.GalleryCategories"

                };
                break;
            case "SMALL COMPONENTS":
                itemname = new String[]{
                        "RADIO",
                        "CHECKBOX",
                        "SEEK",
                        "PROGRESS",
                        "DROPDOWN"
                };
                imgid = new Integer[]{

                        R.drawable.ic_svg_sc_radio,
                        R.drawable.ic_svg_sc_check,
                        R.drawable.ic_svg_sc_seek,
                        R.drawable.ic_svg_sc_progress,
                        R.drawable.ic_svg_sc_dropdown

                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.SmallComponentsRadio",
                        "com.csform.android.uiapptemplate2.SmallComponentsCheck",
                        "com.csform.android.uiapptemplate2.SmallComponentsSeek",
                        "com.csform.android.uiapptemplate2.SmallComponentsProgress",
                        "com.csform.android.uiapptemplate2.SmallComponentsDropDown"
                };
                break;

            default:
                itemname = new String[]{
                        "TABSDEFAULT",
                        "WIZARDS",
                        "DIALOGS",
                        "LIST VIEWS EXPANDABLE",
                        "LIST VIEWS DRAG & DROP",
                        "LIST VIEWS SWIPE"

                };

                imgid = new Integer[]{
                        R.drawable.ic_svg_sc_radio,
                        R.drawable.ic_svg_sc_radio,
                        R.drawable.ic_svg_sc_radio,
                        R.drawable.ic_svg_sc_radio,
                        R.drawable.ic_svg_sc_radio,
                        R.drawable.ic_svg_sc_radio


                };
                classname = new String[]{
                        "com.csform.android.uiapptemplate2.MainActivity",
                        "com.csform.android.uiapptemplate2.MainActivity",
                        "com.csform.android.uiapptemplate2.MainActivity",
                        "com.csform.android.uiapptemplate2.MainActivity",
                        "com.csform.android.uiapptemplate2.MainActivity",
                        "com.csform.android.uiapptemplate2.MainActivity"
                };
        }


        MenuGrid adapter = new MenuGrid(getActivity(), itemname, imgid);
        GridView gridView = (GridView) view.findViewById(R.id.gridViewSelect2);
        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        if (mySwipeRefreshLayout != null) {
            mySwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            Log.i("opa called refresh", "onRefresh called from SwipeRefreshLayout");

                            // This method performs the actual data-refresh operation.
//                            myUpdateOperation();
                        }
                    }
            );
        }
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {

                Intent myIntent;
                try {
                    if (!(classname[position]).equals("com.csform.android.uiapptemplate2.MainActivity") ) {
                        String additional = "";


                        Class cls = Class.forName(classname[position]);


                        myIntent = new Intent(getActivity(), cls);


                        if (!additional.isEmpty()) {
                            myIntent.putExtra(classname[position], additional);
                        } else {
                            myIntent.putExtra(classname[position], "Fade in + Ken Burns");
                        }
                        myIntent.setFlags(myIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);


                        if (!(getActivity().getClass().getSimpleName()).equals("MainActivity")) {

                            getActivity().finish();
                        }

                        startActivity(myIntent);

                    }
                } catch (ClassNotFoundException ex) {
                    Log.d("Class not found", "onItemClick: " + ex);
                }
            }
        });

        return view;
    }

//    public void myUpdateOperation() {
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                RefreshImagesUrl.refresh(getActivity().getApplicationContext());
//                mySwipeRefreshLayout.setRefreshing(false);
//            }
//        }, 2000);
//    }

}