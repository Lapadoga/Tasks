package com.example.tasks.database.data.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE flowers ADD COLUMN country TEXT NOT NULL DEFAULT ''")
        db.execSQL("ALTER TABLE bouquets ADD COLUMN decor TEXT NOT NULL DEFAULT ''")
    }
}