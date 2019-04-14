package com.lyl.buygame.network.parse;

import com.lyl.buygame.network.entity.Comment;
import com.lyl.buygame.network.entity.Game;
import com.lyl.buygame.utils.DateUtils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: lyl
 * Date Created : 2019/4/13.
 */
public class ParseImp {

    /**
     * 获取评论列表
     */
    public void getGameList(final NetWorkCallBack<List<Game>> workCallBack) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Game");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null) {
                    if (objects.size() > 0) {
                        List<Game> gameList = new ArrayList<>();
                        Game game;
                        for (ParseObject obj : objects) {
                            String objectId = obj.getObjectId();
                            String name = obj.getString("name");
                            String image = obj.getString("image");
                            long price = obj.getLong("price");
                            long gameId = obj.getLong("gameId");
                            String createdAt = DateUtils.formatDate(obj.getCreatedAt());
                            String updatedAt = DateUtils.formatDate(obj.getUpdatedAt());

                            game = new Game(objectId, name, image, price, gameId, createdAt, updatedAt);
                            gameList.add(game);
                        }
                        workCallBack.onSuccess(gameList);

                    } else {
                        workCallBack.onFail(400, "游戏数据为空");
                    }

                } else {
                    workCallBack.onFail(500, "游戏列表获取错误");
                }
            }
        });
    }

    /**
     * 根据 gameId 获取评论
     */
    public void getGameComments(long gameId, final NetWorkCallBack<List<Comment>> workCallBack) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Comments");
        query.whereEqualTo("gameId", gameId);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null) {
                    if (objects.size() > 0) {
                        List<Comment> commentList = new ArrayList<>();
                        Comment comment;
                        for (ParseObject obj : objects) {
                            String objectId = obj.getObjectId();
                            String name = obj.getString("name");
                            String avatar = obj.getString("avatar");
                            String commentText = obj.getString("comment");
                            long gameId = obj.getLong("gameId");
                            String createdAt = DateUtils.formatDate(obj.getCreatedAt());
                            String updatedAt = DateUtils.formatDate(obj.getUpdatedAt());

                            comment = new Comment(objectId, name, avatar, commentText, gameId, createdAt, updatedAt);
                            commentList.add(comment);
                        }
                        workCallBack.onSuccess(commentList);

                    } else {
                        workCallBack.onFail(400, "评论数据为空");
                    }

                } else {
                    workCallBack.onFail(500, "游戏的评论列表获取错误");
                }
            }
        });
    }

    public interface NetWorkCallBack<T> {
        void onSuccess(T obj);

        void onFail(int code, String msg);
    }

}
