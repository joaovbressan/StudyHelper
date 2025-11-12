package com.example.studyhelper.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.studyhelper.data.local.entities.Tarefa;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TarefaDao_Impl implements TarefaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Tarefa> __insertionAdapterOfTarefa;

  private final EntityDeletionOrUpdateAdapter<Tarefa> __updateAdapterOfTarefa;

  public TarefaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTarefa = new EntityInsertionAdapter<Tarefa>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tarefas` (`id`,`disciplinaId`,`titulo`,`dataEntrega`,`concluida`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Tarefa entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDisciplinaId());
        statement.bindString(3, entity.getTitulo());
        statement.bindLong(4, entity.getDataEntrega());
        final int _tmp = entity.getConcluida() ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
    this.__updateAdapterOfTarefa = new EntityDeletionOrUpdateAdapter<Tarefa>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tarefas` SET `id` = ?,`disciplinaId` = ?,`titulo` = ?,`dataEntrega` = ?,`concluida` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Tarefa entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDisciplinaId());
        statement.bindString(3, entity.getTitulo());
        statement.bindLong(4, entity.getDataEntrega());
        final int _tmp = entity.getConcluida() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object insertTarefa(final Tarefa tarefa, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTarefa.insert(tarefa);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTarefa(final Tarefa tarefa, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTarefa.handle(tarefa);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Tarefa>> getTarefasPendentes() {
    final String _sql = "SELECT * FROM tarefas WHERE concluida = 0 ORDER BY dataEntrega ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tarefas"}, new Callable<List<Tarefa>>() {
      @Override
      @NonNull
      public List<Tarefa> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDisciplinaId = CursorUtil.getColumnIndexOrThrow(_cursor, "disciplinaId");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDataEntrega = CursorUtil.getColumnIndexOrThrow(_cursor, "dataEntrega");
          final int _cursorIndexOfConcluida = CursorUtil.getColumnIndexOrThrow(_cursor, "concluida");
          final List<Tarefa> _result = new ArrayList<Tarefa>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tarefa _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpDisciplinaId;
            _tmpDisciplinaId = _cursor.getInt(_cursorIndexOfDisciplinaId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final long _tmpDataEntrega;
            _tmpDataEntrega = _cursor.getLong(_cursorIndexOfDataEntrega);
            final boolean _tmpConcluida;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfConcluida);
            _tmpConcluida = _tmp != 0;
            _item = new Tarefa(_tmpId,_tmpDisciplinaId,_tmpTitulo,_tmpDataEntrega,_tmpConcluida);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Tarefa>> getAllTarefas() {
    final String _sql = "SELECT * FROM tarefas ORDER BY dataEntrega DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tarefas"}, new Callable<List<Tarefa>>() {
      @Override
      @NonNull
      public List<Tarefa> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDisciplinaId = CursorUtil.getColumnIndexOrThrow(_cursor, "disciplinaId");
          final int _cursorIndexOfTitulo = CursorUtil.getColumnIndexOrThrow(_cursor, "titulo");
          final int _cursorIndexOfDataEntrega = CursorUtil.getColumnIndexOrThrow(_cursor, "dataEntrega");
          final int _cursorIndexOfConcluida = CursorUtil.getColumnIndexOrThrow(_cursor, "concluida");
          final List<Tarefa> _result = new ArrayList<Tarefa>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Tarefa _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpDisciplinaId;
            _tmpDisciplinaId = _cursor.getInt(_cursorIndexOfDisciplinaId);
            final String _tmpTitulo;
            _tmpTitulo = _cursor.getString(_cursorIndexOfTitulo);
            final long _tmpDataEntrega;
            _tmpDataEntrega = _cursor.getLong(_cursorIndexOfDataEntrega);
            final boolean _tmpConcluida;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfConcluida);
            _tmpConcluida = _tmp != 0;
            _item = new Tarefa(_tmpId,_tmpDisciplinaId,_tmpTitulo,_tmpDataEntrega,_tmpConcluida);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getCountTotalTarefas() {
    final String _sql = "SELECT COUNT(*) FROM tarefas";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tarefas"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getCountTarefasConcluidas() {
    final String _sql = "SELECT COUNT(*) FROM tarefas WHERE concluida = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tarefas"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
