package biz.ideus.ideuslibexample.ui.main_screen;


import biz.ideus.ideuslibexample.enums.BoardCommands;


/**
 * Created by blackmamba on 09.02.17.
 */

public class BoardCommandWrapper {
    private String boardName;
    private String ident;

    private BoardCommands boardCommand;

    public String getIdent() {
        return ident;
    }

    public String getBoardName() {
        return boardName;
    }

    public BoardCommands getBoardCommand() {
        return boardCommand;
    }

    public BoardCommandWrapper(BoardCommands boardCommand, String boardName){
        this.boardCommand = boardCommand;
        this.boardName = boardName;
    }

    public BoardCommandWrapper(BoardCommands boardCommand, String boardName, String ident){
        this.boardCommand = boardCommand;
        this.boardName = boardName;
        this.ident = ident;
    }
}
