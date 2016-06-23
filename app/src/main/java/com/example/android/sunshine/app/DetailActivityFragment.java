package com.example.android.sunshine.app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
        setHasOptionsMenu(true); //add the menu to the fragment
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detailfragment, menu);

        MenuItem menuItem = menu.findItem(R.id.action);

        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem); //get shareItemProvider from detailfragment

        if(mShareActionProvider != null)
        {
            mShareActionProvider.setShareIntent(createShareForecastIntent()); // add intent to ShareitemProvider
        }
        else
        {
            Log.d(LOG_TAG, "Share Action Provider is null");
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private final String LOG_TAG = DetailActivityFragment.class.getSimpleName();
    private final String trend = "#SunShineApp";
    private String mforecastStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        View rootview = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView text =(TextView) rootview.findViewById(R.id.detail_text);
        if(intent.hasExtra(Intent.EXTRA_TEXT))
        {
            mforecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            text.setText(mforecastStr);
        }
        return rootview;
    }

    private Intent createShareForecastIntent()
    {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET); // this flag helps to return to our app..after sharing
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,mforecastStr + trend);

        return shareIntent;
    }
}
