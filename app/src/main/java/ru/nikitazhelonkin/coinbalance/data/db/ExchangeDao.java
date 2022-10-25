package ru.nikitazhelonkin.coinbalance.data.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.Exchange;

@Dao
public interface ExchangeDao {

    @Query("SELECT * FROM exchange")
    Single<List<Exchange>> getAll();

    @Query("SELECT * FROM exchange WHERE mId=:exchangeId")
    Single<Exchange> getById(int exchangeId);

    @Query("DELETE FROM exchange")
    void deleteAll();

    @Insert
    void insert(Exchange exchange);

    @Update
    void update(Exchange exchange);

    @Delete
    void delete(Exchange exchange);

}
