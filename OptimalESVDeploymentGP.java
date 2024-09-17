import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class accomplishes Mission Eco-Maintenance
 */

public class OptimalESVDeploymentGP
{
    private ArrayList<Integer> maintenanceTaskEnergyDemands;

    /*
     * Should include tasks assigned to ESVs.
     * For the sample input:
     * 8 100
     * 20 50 40 70 10 30 80 100 10
     * 
     * The list should look like this:
     * [[100], [80, 20], [70, 30], [50, 40, 10], [10]]
     * 
     * It is expected to be filled after getMinNumESVsToDeploy() is called.
     */
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<Integer> getMaintenanceTaskEnergyDemands() {
        return maintenanceTaskEnergyDemands;
    }

    /**
     *
     * @param maxNumberOfAvailableESVs the maximum number of available ESVs to be deployed
     * @param maxESVCapacity the maximum capacity of ESVs
     * @return the minimum number of ESVs required using first fit approach over reversely sorted items.
     * Must return -1 if all tasks can't be satisfied by the available ESVs
     */

    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs,int maxESVCapacity)
    {
        int sum = sum(maintenanceTaskEnergyDemands);
        if(sum > maxNumberOfAvailableESVs*maxESVCapacity)
        {
            return -1;
        }

        for(int i = 0; i < maintenanceTaskEnergyDemands.size(); i++)
        {
            if(maintenanceTaskEnergyDemands.get(i) > maxESVCapacity)
            {
                return -1;
            }
        }

        Collections.sort(maintenanceTaskEnergyDemands, Collections.reverseOrder());

        ArrayList<ArrayList<Integer>> assignedLoads = new ArrayList<>();
        for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
            assignedLoads.add(new ArrayList<>());
        }
        int[] space = new int[maxNumberOfAvailableESVs];
        for(int i = 0; i < maxNumberOfAvailableESVs; i++)
        {
            space[i] = 100;
        }

        for (int yuk : maintenanceTaskEnergyDemands) {
            boolean loaded = false;
            for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
                ArrayList<Integer> esv = assignedLoads.get(i);
                if (yuk <= space[i]) {
                    esv.add(yuk);
                    space[i] -= yuk;
                    loaded = true;
                    break;
                }
            }
            if (!loaded) {
                return -1; // Tüm görevler yerleştirilemiyor
            }
        }

        assignedLoads.removeIf(ArrayList::isEmpty);
        maintenanceTasksAssignedToESVs = assignedLoads;
        return assignedLoads.size();
    }

    private static int sum(ArrayList<Integer> list) {
        int sum = 0;
        for (Integer num : list) {
            sum += num;
        }
        return sum;
    }


}
