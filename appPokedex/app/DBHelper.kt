
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "estadisticas.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "pantallas"
        private const val COLUMN_ID = "id"
        private const val COLUMN_SCREEN_NAME = "screen_name"
        private const val COLUMN_VISIT_COUNT = "visit_count"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_SCREEN_NAME TEXT UNIQUE, " +
                "$COLUMN_VISIT_COUNT INTEGER DEFAULT 0)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun registrarVisita(screenName: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_SCREEN_NAME, screenName)

        // Intenta insertar, si ya existe, incrementa el contador
        values.put(COLUMN_VISIT_COUNT, 1)
        val result = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE)
        if (result == -1L) {
            db.execSQL("UPDATE $TABLE_NAME SET $COLUMN_VISIT_COUNT = $COLUMN_VISIT_COUNT + 1 WHERE $COLUMN_SCREEN_NAME = ?", arrayOf(screenName))
        }
        db.close()
    }

    fun obtenerEstadisticas(): Map<String, Int> {
        val stats = mutableMapOf<String, Int>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT $COLUMN_SCREEN_NAME, $COLUMN_VISIT_COUNT FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val screenName = cursor.getString(cursor.getColumnIndex(COLUMN_SCREEN_NAME))
                val visitCount = cursor.getInt(cursor.getColumnIndex(COLUMN_VISIT_COUNT))
                stats[screenName] = visitCount
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return stats
    }
}