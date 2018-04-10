package com.hestia.datalayer.Card;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import com.hestia.domainlayer.CardImpl;
import com.hestia.presentationlayer.DeckDecorator;

import java.util.List;

/**
 * Created by Richard on 3/31/2018.
 */

@Dao
public interface CardDao {
  // selects all cards
  @Query("SELECT * FROM CardDecorator LIMIT :batchSize")
  List<CardDecorator> getBatch(int batchSize);

  // selects all cards in a class
  @Query("SELECT * FROM CardDecorator")
  List<CardDecorator> getAllInClass();

  //TODO make column
  // selects all cards in an expansion
  @Query("SELECT * FROM CardDecorator")
  List<CardDecorator> getAllInExpansion();

  // selects all cards with a certain cost
  @Query("SELECT * FROM CardDecorator WHERE card_cost LIKE :cost")
  List<CardDecorator> getAllWithCost(int cost);

  // inserts all card into the database
  @Insert
  void insertAll(List <CardDecorator> cards);
  // inserts all card into the database

  @Insert
  void insert(CardDecorator cardDecorator);


  // selects all cards in a class
  @Query("SELECT * FROM CardDecorator WHERE card_cost LIKE :cost")
  DataSource.Factory<Integer, CardDecorator> getByCost(int cost);

  // selects all cards and order by
  @Query("SELECT * FROM CardDecorator ORDER BY :column DESC")
  DataSource.Factory<Integer, CardDecorator> getByColumnDesc(String column);

  // selects all cards and order by
  @Query("SELECT * FROM CardDecorator ORDER BY :column ASC")
  DataSource.Factory<Integer, CardDecorator> getByColumnAsc(String column);
}
