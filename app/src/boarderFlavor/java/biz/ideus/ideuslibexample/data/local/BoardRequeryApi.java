package biz.ideus.ideuslibexample.data.local;


import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;
import io.requery.Persistable;
import io.requery.query.Result;
import io.requery.rx.SingleEntityStore;
import rx.Observable;

import static biz.ideus.ideuslibexample.SampleApplication.requeryApi;

/**
 * Created by blackmamba on 10.02.17.
 */

public class BoardRequeryApi implements IBoardRequeryApi {

    SingleEntityStore<Persistable> data = requeryApi.getData();


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
    public Observable<Result<BoardEntity>> getBoardList() {
        return data.select(BoardEntity.class).get().toSelfObservable();
    }
}
