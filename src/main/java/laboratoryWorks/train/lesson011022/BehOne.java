package laboratoryWorks.train.lesson011022;

import jade.core.behaviours.Behaviour;

public class BehOne extends Behaviour {

    private boolean finish = false;

    @Override
    public void action() {

        int i = 0;
        while (i < 10) {
            System.out.println(i);
            if (i == 9) finish = true;
            i++;
        }
    }

    @Override
    public boolean done() { return finish; }
}
