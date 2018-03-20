package com.hestia.presentationlayer.singledeck;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.hestia.datalayer.DeckRepository;
import com.hestia.datalayer.DeckRepositoryImpl;
import com.hestia.domainlayer.Deck;

import java.util.ArrayList;

/**
 * Created by Richard on 3/14/2018.
 */

public class SingleDeckPresenter implements SingleDeckContract.Presenter{
  private ArrayList <TabFragment> tabFragments = new ArrayList<>();
  private SingleDeckContract.View singleDeckView;
  private DeckRepository deckRepository;
  Deck currentDeck;


  public SingleDeckPresenter(SingleDeckContract.View view, String deckID) {
    singleDeckView = view;

    // initialize the repository object to get data
    deckRepository = new DeckRepositoryImpl();
    deckRepository.getFullDeck(this, deckID);
  }


  @Override
  public void receiveFullDeck(Deck deck) {
    this.currentDeck = deck;
    // iterate through each tab fragment to update its information
    for (TabFragment tabInstance: tabFragments) {
      tabInstance.updateUI(currentDeck);
    }
    // update ui to reflect deck changes
  }


  @Override
  public void addTabFragment(TabFragment tabFragment) {
    // adds the fragment into the current array of tab fragments
    this.tabFragments.add(tabFragment);
  }


}
