package game.player;

import game.Manager;
import game.Player;
import game.Poker;
import java.util.List;
import java.util.Collections;
import java.util.concurrent.Callable;

public class WzyPlayer implements Player{
    @Override
    public String getName(){return "wzy";}
    @Override
    public String getStuNum(){return "2019212751";}
    @Override
    public void onGameStart(Manager manager,int totalPlayer){

        }

    @Override
    public int bet(int time, int round, int lastPerson, int moneyOnDesk, int moneyYouNeedToPayLeast, List<Poker> pokers) {
            Collections.sort(pokers);
            if (lastPerson(lastPerson)<=0||round>1500){
                return moneyYouNeedToPayLeast;}
            if (isSameColor(pokers) )
                return 3*moneyYouNeedToPayLeast;
            if ( (isSameColorStraight(pokers) || isSamePoint(pokers))  )
                return 3*moneyYouNeedToPayLeast;
            if (isPair(pokers))
                return 0;
            if (isStraight(pokers))
                return (int)2.5*moneyYouNeedToPayLeast;
            for (Poker p : pokers){
                if ( p.getPoint().getNum() >=13)
                    return moneyYouNeedToPayLeast;
        }
          return 0;
        }
    public void onResult(int time, boolean isWin, List<game.Poker> pokers) {

    }
    private boolean isSameColor(List<Poker> handCards) {
        return handCards.get(0).getSuit() == handCards.get(1).getSuit() &&
                handCards.get(1).getSuit() == handCards.get(2).getSuit();
    }

    private boolean isPair(List<Poker> handCards) {
        return handCards.get(0).getPoint().getNum() == handCards.get(1).getPoint().getNum()
                || handCards.get(1).getPoint().getNum() == handCards.get(2).getPoint().getNum()
                || handCards.get(0).getPoint().getNum() == handCards.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> handCards) {
        Collections.sort(handCards);
        return Math.abs(handCards.get(0).getPoint().getNum() - handCards.get(1).getPoint().getNum()) == 1
                && Math.abs(handCards.get(1).getPoint().getNum() - handCards.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }
    private int lastPerson(int lastPerson){
        return lastPerson;
    }

}
