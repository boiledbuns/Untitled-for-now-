package com.hestia.presentationlayer.displaycards;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hestia.R;
import com.hestia.datalayer.Card.CardDecorator;
import com.hestia.domainlayer.Card;
import com.hestia.presentationlayer.customadapter.DisplayCardAdapter;
import com.hestia.presentationlayer.singledeck.TabFragment;

public class DisplayCardsTab extends Fragment {
  private DisplayCardAdapter cardListAdapter;
  View rootView;

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // inflate the view using the inflater
    this.rootView = inflater.inflate(R.layout.display_cards_tab, container, false);

    // initializes the reference to the recyclerview
    RecyclerView recyclerView = rootView.findViewById(R.id.display_cards_tab_recycler);
    recyclerView.setHasFixedSize(true);

    // initialize the layout manager and attaches it to the view
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
    recyclerView.setLayoutManager(mLayoutManager);


    // create the new adapter
    cardListAdapter = new DisplayCardAdapter();
    recyclerView.setAdapter(cardListAdapter);

    return this.rootView;
  }

  public void attachCards(LiveData<PagedList<CardDecorator>> cardlist) {
    Log.d("SINGLE_CARDS_TAB", cardlist.toString());
    cardlist.observe(this, liveCardlist -> cardListAdapter.submitList(liveCardlist));
  }
}


