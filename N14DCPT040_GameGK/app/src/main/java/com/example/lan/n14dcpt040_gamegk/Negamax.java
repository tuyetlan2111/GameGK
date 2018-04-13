package com.example.lan.n14dcpt040_gamegk;

/**
 * Created by Lan on 4/13/2018.
 */

public class Negamax {

    public Record Negamax(Chesboard chesboard, int player, int maxDepth, int currentDepth, Move bestmove, int alpha, int beta) {
        bestmove = null;
        int bestScore;


        if (chesboard.isgameover() || currentDepth == maxDepth) {
            return new Record(null, chesboard.Evaluate(player));
        }

        if(chesboard.getPlayer()==player)
            bestScore =Integer.MIN_VALUE;

        else
            bestScore =Integer.MAX_VALUE;
        for (Move move : chesboard.getMove()) {

            Chesboard newChesboard = new Chesboard(chesboard.getContext(),
                    chesboard.getBitmapWidth(), chesboard.getBitmapHeight(),
                    chesboard.getColQty(), chesboard.getRowQty());
            newChesboard.setPlayer(chesboard.getPlayer());
            newChesboard.setBoard(chesboard.getBoard());
            newChesboard.makemove(move);

            int recursedScore;
            Move currentMove = null;
            int cd = currentDepth + 1;
            int max = Math.max(alpha,bestScore);

            Record record = Negamax(newChesboard,player,maxDepth,cd,currentMove,-beta,max);

            int currentScore = -record.getScore();
            if(currentScore > bestScore){
                bestScore = currentScore;
                bestmove = move;

                if(bestScore >= beta){
                    new Record(bestmove,bestScore);
                }
            }

        }
        return new Record(bestmove,bestScore);
    }


}
