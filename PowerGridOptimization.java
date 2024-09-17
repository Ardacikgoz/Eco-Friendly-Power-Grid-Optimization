import java.util.ArrayList;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;

    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }
    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalPowerGridSolution
     */
    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP(){

        OptimalPowerGridSolution opt = new OptimalPowerGridSolution(0,new ArrayList<>());
        int N = amountOfEnergyDemandsArrivingPerHour.size();
        int[] E = new int[N];
        for(int i =0 ; i < N ; i++)
        {
            E[i] = (i+1)*(i+1);
        }
        int[] SOL = new int[N + 1];
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>();

        SOL[0] = 0;
        HOURS.add(new ArrayList<>());


        for (int j = 1; j <= N; j++) {
            int maxVal = Integer.MIN_VALUE;
            int index = -1;

            for (int i = 0; i < j; i++) {
                int val = SOL[i] + Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), E[j - 1 - i]);
                if (val > maxVal) {
                    maxVal = val;
                    index = i;
                }
            }

            SOL[j] = maxVal;

            ArrayList<Integer> hours = new ArrayList<>(HOURS.get(index));
            hours.add(j);
            HOURS.add(hours);
        }

        opt.hoursToDischargeBatteriesForMaxEfficiency = HOURS.get(HOURS.size() - 1);
        opt.maxNumberOfSatisfiedDemands = SOL[SOL.length - 1];


        return opt;

    }
}
