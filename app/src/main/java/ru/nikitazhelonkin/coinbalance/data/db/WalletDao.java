package ru.nikitazhelonkin.coinbalance.data.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.Wallet;

@Dao
public interface WalletDao {

    @Query("SELECT * FROM wallet")
    Single<List<Wallet>> getAll();

    @Query("SELECT * FROM wallet WHERE mId=:walletId")
    Single<Wallet> getById(int walletId);

    @Insert
    void insert(Wallet wallet);

    @Update
    void update(Wallet wallet);

    @Delete
    void delete(Wallet wallet);
}
