package ru.nikitazhelonkin.coinbalance.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;
import ru.nikitazhelonkin.coinbalance.data.entity.Token;

@Dao
public interface TokenDao {

    @Query("SELECT * FROM token")
    Single<List<Token>> getTokens();

    @Query("SELECT * FROM token WHERE wallet_address=:wallet_address")
    Single<List<Token>> getTokens(String wallet_address);

    @Insert
    void insert(Iterable<Token> tokens);

    @Query("DELETE FROM token WHERE wallet_address=:wallet_address")
    void delete(String wallet_address);
}
