/*
 * Copyright (c) 2023 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.experiments.impl.store

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    exportSchema = true,
    version = 2,
    entities = [
        ExperimentVariantEntity::class,
    ],
)

@TypeConverters(
    StringListConverter::class,
)

abstract class ExperimentsDatabase : RoomDatabase() {
    abstract fun experimentVariantsDao(): ExperimentVariantDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE `experiment_variants` ADD COLUMN `androidVersionFilter` TEXT NOT NULL DEFAULT '[]'")
    }
}

val ALL_MIGRATIONS = arrayOf(MIGRATION_1_2)