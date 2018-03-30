package com.hestia.presentationlayer.customadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hestia.R;
import com.hestia.domainlayer.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 3/29/2018.
 */

public class SingleDeckCardAdapter extends ArrayAdapter <Card> {
  private ArrayList<Card> deckList;
  private Context parentContext;

  // viewholder for lookup cache
  private static class ViewHolder {
    TextView cardName;
    TextView cardCost;
    ImageView cardImage;
  }

  public SingleDeckCardAdapter(@NonNull Context context, List <Card> cardList) {
    super(context, R.layout.deck_card_item, cardList);

    this.parentContext = context;
    this.deckList = (ArrayList) cardList;
  }

  public View getView (int position, View convertView, ViewGroup parent) {
    // view to be returned
    View resultView;
    // retrieve the Card object based on position
    Card currentCard = (Card) getItem(position);

    ViewHolder viewHolder;
    // creates the new view if it doesn't already exist
    if (convertView == null) {
      LayoutInflater inflater = LayoutInflater.from(parentContext);
      resultView = inflater.inflate(R.layout.deck_card_item, parent, false);

      // Initializes the viewholder and its references to the newly inflated layout
      viewHolder = new ViewHolder();
      viewHolder.cardName = resultView.findViewById(R.id.deck_card_name);
      viewHolder.cardCost = resultView.findViewById(R.id.deck_card_cost);

      // allows the viewholder to be accessed later on
      resultView.setTag(viewHolder);
    }
    else {
      // retrieves the viewholder object from the view
      viewHolder = (ViewHolder) convertView.getTag();
      // since the view already exists, just use that and update its values instead
      resultView = convertView;
    }

    // use the viewholder to update the layout values
    viewHolder.cardName.setText(currentCard.getName());
    viewHolder.cardCost.setText(currentCard.getCost());

    // return completed view to be rendered
    return resultView;
  }





}
