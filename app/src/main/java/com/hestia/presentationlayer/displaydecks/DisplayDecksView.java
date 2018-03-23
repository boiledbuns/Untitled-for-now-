package com.hestia.presentationlayer.displaydecks;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hestia.R;
import com.hestia.domainlayer.Deck;
import com.hestia.presentationlayer.customadapter.DisplayDeckAdapter;
import com.hestia.presentationlayer.singledeck.SingleDeckView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 3/6/2018.
 *
 * I'm gonna forget so here it is:
 * > This Fragment contains -- ONLY -- the display logic for the fragment
 * > The instance of the Presenter for this fragment has functions that
 * are called to perform the logic for user interaction
 *
 *
 */
public class DisplayDecksView extends Fragment implements DisplayDecksContract.View {

  private DisplayDecksContract.Presenter displayDeckPresenter;
  private DisplayDeckAdapter mAdapter;

  private RecyclerView mRecyclerView;
  private RecyclerView.LayoutManager mLayoutManager;

  @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
      // inflate the layout for this view
      View rootView = inflater.inflate(R.layout.display_decks, container, false);

    // initialize the instance of the recycler view
    mRecyclerView = rootView.findViewById(R.id.recycler_list);
    mRecyclerView.setHasFixedSize(true);

    // initializes and sets the layout manager plus adapter
    mLayoutManager = new GridLayoutManager(getActivity(), 2);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mAdapter = new DisplayDeckAdapter(getActivity());

    // creates a new onclick and passes it to the adapter
    AdapterListener listener = new AdapterListener();
    mAdapter.addOnClickListener(listener);

    mRecyclerView.setAdapter(mAdapter);

    return rootView;
  }

  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // create an instance of the presenter
    displayDeckPresenter = new DisplayDecksPresenter(this);
  }


  public void addDecks (List <Deck> decks) {
    // add the list of decks to the adapter
    mAdapter.addDecks(decks);
  }


  /**
   * Custom adapter class to circumvent RecyclerView's lack of an OnItemClickListener
   * Allows us to keep view code in our this view class
   */
  class AdapterListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
      // get the tag from the view
      String position = view.getTag().toString();
      //Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();

      // create a bundle to pass the new fragment the id of the deck
      SingleDeckView singleDeckFragment = new SingleDeckView();
      Bundle args = new Bundle();
      args.putString("deck_id", position);
      singleDeckFragment.setArguments(args);

      FragmentTransaction transaction = getFragmentManager().beginTransaction();
      // replace contents of fragment container with this fragment
      transaction.replace(R.id.content_frame, singleDeckFragment);
      // adds the replaced fragment to back stack to allow user to navigate back to it
      transaction.addToBackStack(null);

      // commit the transaction
      transaction.commit();
    }
  }



}

