package poker;

import card.Card;
import card.element.CardNumber;
import card.element.CardMark;
import player.hand.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Poker {
    //受け取ったHand型変数を用いて手札にそろっている役を判定する
    static Result handCheck(Hand hand) {
        /*ポーカーの役については下記サイトを参考にしました
         *http://ww6.tiki.ne.jp/~tamatsuo/poker.htm
         */

        //ブタ == 0
        //ワンペア == 1
        //ツーペア == 2
        //スリーカード == 3
        //ストレート == 4
        //フラッシュ == 5
        //フルハウス == 6
        //フォーカード == 7
        //ストレートフラッシュ == 8
        //ロイヤルストレートフラッシュ == 9
        //ファイブカード == 10
        int jokerCounter = 0;

        //手札の最初のカードの数字とマークを保存しておく
        CardNumber firstNumber = hand.get(0).getNumber();
        CardMark firstMark = hand.get(0).getMark();
        //フラッシュがそろっていたらtrueが入る
        boolean flush = true;
        //ストレートがそろっていたらtrueが入る
        boolean straight = true;

        //手札にあるJOKERの枚数を数える
        //数えるついでに手札のJOKERはすべて削除する
        for (Card aHand : hand) {
            if (aHand.getNumber() == CardNumber.JOKER) {
                jokerCounter++;
            }
        }
        final int JOKER_NUMBER = jokerCounter;

        //フラッシュの役がそろっているか判定する
        for (Card card : hand) {
            if (!((card.getMark() == firstMark) || card.getNumber() == CardNumber.JOKER)) {
                flush = false;
                break;
            }
        }
        //ストレートの役がそろっているか判定する
        int num = 0;
        for (int i = 0; i < hand.size() - JOKER_NUMBER; i++) {
            if (hand.get(i).getNumber().ordinal() != (firstNumber.ordinal() + num)) {
                if (jokerCounter > 0) {
                    jokerCounter--;
                    i--;
                } else {
                    straight = false;
                    break;
                }
            }
            num++;
        }
        //ストレートフラッシュとロイヤルストレートフラッシュを判定する
        if (flush && straight) {
            if (firstNumber.ordinal() == 8) {
                //ロイヤルストレートフラッシュ判定
                return new Result(9, JOKER_NUMBER, firstMark);
            }
            //ストレートフラッシュ判定
            return new Result(8, firstNumber, JOKER_NUMBER, firstMark);
        }
        if (straight) {
            //ストレート判定
            return new Result(4, firstNumber, hand.get(hand.size() - 1).getMark());
        }


        /*手札の中のカードで何枚同じカードがあるか判定する
         *判定した枚数は手札一枚ごとに記録しておく
         *全体的にコードが冗長なので後で調整すること
         */
        int counter = 0;
        List<Integer> matchNumber = new ArrayList<>();
        for (int i = 0; i < hand.size() - JOKER_NUMBER; i++) {
            for (int j = 0; j < hand.size() - JOKER_NUMBER; j++) {
                if (hand.get(i).getNumber() == hand.get(j).getNumber()) {
                    counter++;
                }
            }
            matchNumber.add(counter);
            counter = 0;
        }
        //ここでmatchNumber(手札ごとに判定した同じ数字の数)の中の最大値の値を調べる
        int max = 0;
        for (Integer i : matchNumber) {
            max = Math.max(max, i);
        }
        //調べ終わったらその最大値の格納されているindexを調べる
        //secondStrongはツーペア判定のときのみ使われる変数
        int index = matchNumber.lastIndexOf(max);
        CardNumber firstStrong = hand.get(index).getNumber();
        index = matchNumber.indexOf(max);
        CardNumber secondStrong = hand.get(index).getNumber();

        Collections.sort(matchNumber);
        //JOKERを一番同じ数字の多かったカード扱いにする
        if (matchNumber.get(matchNumber.size() - 1) == 1) {
            //ジョーカーを除く手札がブタ状態であればワンペア判定にするために最後の要素だけJOKERをたす
            int i = matchNumber.get(matchNumber.size() - 1) + JOKER_NUMBER;
            matchNumber.set(matchNumber.size() - 1, i);
        } else {
            int i = 1;
            while (i <= max) {
                int number = matchNumber.get(matchNumber.size() - i) + JOKER_NUMBER;
                matchNumber.set(matchNumber.size() - i, number);
                i++;
            }
        }
        //一番重複の多かったカードの枚数を取得する
        int firstMany = matchNumber.get(matchNumber.size() - 1);

        switch (firstMany) {
            case 1:
                if (flush) {
                    //フラッシュ判定
                    return new Result(5, hand.get(hand.size() - 1).getNumber(), JOKER_NUMBER, firstMark);
                }
                //ブタ(ハイカード)判定
                return new Result(0, firstStrong, firstMark);
            case 2:
                if (flush) {
                    //フラッシュ判定
                    return new Result(5, hand.get(hand.size() - 1).getNumber(), JOKER_NUMBER, firstMark);
                }
                //ツーペアがそろっているかを判定
                if (matchNumber.get(matchNumber.size() - 3) == 2) {
                    //ツーペア判定
                    return new Result(2, firstStrong, secondStrong, JOKER_NUMBER, firstMark);
                }
                //ワンペア判定
                return new Result(1, firstStrong, JOKER_NUMBER, firstMark);
            case 3:
                //フルハウスがそろっているか判定
                if (matchNumber.contains(2)) {
                    System.out.println("DEBUG: フルハウス判定が読まれています");
                    //フルハウス判定
                    return new Result(6, firstStrong, JOKER_NUMBER, firstMark);
                }
                if (flush) {
                    //フラッシュ判定
                    return new Result(5, hand.get(hand.size() - 1).getNumber(), JOKER_NUMBER, firstMark);
                }
                //スリーカード判定
                return new Result(3, firstStrong, JOKER_NUMBER, firstMark);
            case 4:
                //フォーカード判定
                return new Result(7, firstStrong, JOKER_NUMBER, firstMark);
            case 5:
                //ファイブカード判定
                return new Result(10, firstStrong, JOKER_NUMBER, firstMark);
        }
        return null;
    }
}
