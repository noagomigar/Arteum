package com.example.apilistapp.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.apilistapp.`data`.local.entity.ArtworkEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ArtworkDao_Impl(
  __db: RoomDatabase,
) : ArtworkDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfArtworkEntity: EntityInsertAdapter<ArtworkEntity>

  private val __deleteAdapterOfArtworkEntity: EntityDeleteOrUpdateAdapter<ArtworkEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfArtworkEntity = object : EntityInsertAdapter<ArtworkEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `favorites` (`id`,`title`,`artistTitle`,`imageUrl`,`dateDisplay`,`placeOfOrigin`,`mediumDisplay`,`description`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ArtworkEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.artistTitle)
        val _tmpImageUrl: String? = entity.imageUrl
        if (_tmpImageUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpImageUrl)
        }
        statement.bindText(5, entity.dateDisplay)
        statement.bindText(6, entity.placeOfOrigin)
        statement.bindText(7, entity.mediumDisplay)
        val _tmpDescription: String? = entity.description
        if (_tmpDescription == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpDescription)
        }
      }
    }
    this.__deleteAdapterOfArtworkEntity = object : EntityDeleteOrUpdateAdapter<ArtworkEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `favorites` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ArtworkEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override suspend fun addArtwork(artwork: ArtworkEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfArtworkEntity.insert(_connection, artwork)
  }

  public override suspend fun deleteArtwork(artwork: ArtworkEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfArtworkEntity.handle(_connection, artwork)
  }

  public override suspend fun getAllArtworks(): MutableList<ArtworkEntity> {
    val _sql: String = "SELECT * FROM favorites"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistTitle: Int = getColumnIndexOrThrow(_stmt, "artistTitle")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfDateDisplay: Int = getColumnIndexOrThrow(_stmt, "dateDisplay")
        val _columnIndexOfPlaceOfOrigin: Int = getColumnIndexOrThrow(_stmt, "placeOfOrigin")
        val _columnIndexOfMediumDisplay: Int = getColumnIndexOrThrow(_stmt, "mediumDisplay")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _result: MutableList<ArtworkEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ArtworkEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistTitle: String
          _tmpArtistTitle = _stmt.getText(_columnIndexOfArtistTitle)
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpDateDisplay: String
          _tmpDateDisplay = _stmt.getText(_columnIndexOfDateDisplay)
          val _tmpPlaceOfOrigin: String
          _tmpPlaceOfOrigin = _stmt.getText(_columnIndexOfPlaceOfOrigin)
          val _tmpMediumDisplay: String
          _tmpMediumDisplay = _stmt.getText(_columnIndexOfMediumDisplay)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          _item = ArtworkEntity(_tmpId,_tmpTitle,_tmpArtistTitle,_tmpImageUrl,_tmpDateDisplay,_tmpPlaceOfOrigin,_tmpMediumDisplay,_tmpDescription)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getArtworkById(artworkId: Int): ArtworkEntity? {
    val _sql: String = "SELECT * FROM favorites WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artworkId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfArtistTitle: Int = getColumnIndexOrThrow(_stmt, "artistTitle")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _columnIndexOfDateDisplay: Int = getColumnIndexOrThrow(_stmt, "dateDisplay")
        val _columnIndexOfPlaceOfOrigin: Int = getColumnIndexOrThrow(_stmt, "placeOfOrigin")
        val _columnIndexOfMediumDisplay: Int = getColumnIndexOrThrow(_stmt, "mediumDisplay")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _result: ArtworkEntity?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpArtistTitle: String
          _tmpArtistTitle = _stmt.getText(_columnIndexOfArtistTitle)
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpDateDisplay: String
          _tmpDateDisplay = _stmt.getText(_columnIndexOfDateDisplay)
          val _tmpPlaceOfOrigin: String
          _tmpPlaceOfOrigin = _stmt.getText(_columnIndexOfPlaceOfOrigin)
          val _tmpMediumDisplay: String
          _tmpMediumDisplay = _stmt.getText(_columnIndexOfMediumDisplay)
          val _tmpDescription: String?
          if (_stmt.isNull(_columnIndexOfDescription)) {
            _tmpDescription = null
          } else {
            _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          }
          _result = ArtworkEntity(_tmpId,_tmpTitle,_tmpArtistTitle,_tmpImageUrl,_tmpDateDisplay,_tmpPlaceOfOrigin,_tmpMediumDisplay,_tmpDescription)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearAllFavorites() {
    val _sql: String = "DELETE FROM favorites"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
