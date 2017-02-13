package biz.ideus.ideuslibexample.data.local;


import java.util.List;

import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import io.requery.Persistable;
import io.requery.rx.SingleEntityStore;
import rx.Observable;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;

/**
 * Created by blackmamba on 10.02.17.
 */

public class BoardRequeryApi implements IBoardRequeryApi {
    public static BoardRequeryApi instance;
    SingleEntityStore<Persistable> data = requeryApi.getData();

    public static BoardRequeryApi getInstance() {
        if (instance == null) {
            instance = new BoardRequeryApi();
            return instance;
        } else {
            return instance;
        }
    }

    @Override
    public Observable<BoardEntity> storeBoard(BoardEntity boardEntity) {
        return data.upsert(boardEntity).toObservable();
    }

    @Override
    public Observable<Iterable<BoardEntity>> storeBoardList(Iterable<BoardEntity> boardEntityList) {
        return data.upsert(boardEntityList).toObservable();
    }

    @Override
    public Observable<BoardEntity> getBoardById(String boardId) {
        return data.select(BoardEntity.class).where(BoardEntity.IDENT.eq(boardId)).get().toObservable();
    }

    @Override
    public List<BoardEntity> getBoardList() {
        return data.select(BoardEntity.class).get().toList();
    }
}
