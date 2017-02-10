package biz.ideus.ideuslibexample.ui.main_screen;


import biz.ideus.ideuslibexample.enums.BoardCommands;
import biz.ideus.ideuslibexample.network.response.entity_model.BoardEntity;


/**
 * Created by blackmamba on 09.02.17.
 */

public class BoardCommandWrapper {
    private BoardEntity boardEntity;
    private BoardCommands boardCommand;

    public BoardEntity getBoardEntity() {
        return boardEntity;
    }

    public BoardCommands getBoardCommand() {
        return boardCommand;
    }

    public BoardCommandWrapper(BoardCommands boardCommand, BoardEntity boardEntity){
        this.boardCommand = boardCommand;
        this.boardEntity = boardEntity;
    }
}
