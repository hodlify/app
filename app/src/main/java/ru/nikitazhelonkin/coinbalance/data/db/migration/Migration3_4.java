package ru.nikitazhelonkin.coinbalance.data.db.migration;


import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.migration.Migration;
import androidx.annotation.NonNull;

public class Migration3_4 extends Migration {

    public Migration3_4() {
        super(3, 4);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase _db) {
        _db.execSQL("ALTER TABLE `Exchange` ADD `error_message` TEXT;");
    }
}
