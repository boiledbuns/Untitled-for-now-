package com.hestia.presentationlayer.singledeck;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hestia.domainlayer.Deck;

/**
 * Created by Richard on 3/19/2018.
 */

public class TabFragment extends Fragment {
  SingleDeckContract.Presenter parentPresenter;

  public int test = 20;


  public void updateUI (Deck deck) {

  }

  public void setPresenter(SingleDeckContract.Presenter presenter) {
    // uses the parent presenter to update information
    parentPresenter = presenter;
  }

}
