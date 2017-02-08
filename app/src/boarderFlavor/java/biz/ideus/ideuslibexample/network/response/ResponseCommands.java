package biz.ideus.ideuslibexample.network.response;

import biz.ideus.ideuslibexample.network.response.data.GetBoardsListData;

public enum ResponseCommands {

    BOARDS_LIST ("boards_list", GetBoardsListData.class);

    private String command;
    private Class responseClass;

    ResponseCommands(String command, Class responseClass) {
        this.command = command;
        this.responseClass = responseClass;
    }
}
